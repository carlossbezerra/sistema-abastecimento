package com.sistema_controle_abastecimento.application.service;

import com.sistema_controle_abastecimento.application.dto.AbastecimentoDTO;
import com.sistema_controle_abastecimento.application.dto.AbastecimentoRequestDTO;
import com.sistema_controle_abastecimento.domain.entity.Abastecimento;
import com.sistema_controle_abastecimento.domain.exception.BusinessException;
import com.sistema_controle_abastecimento.infrastructure.repository.AbastecimentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AbastecimentoService {

    private final AbastecimentoRepository abastecimentoRepository;
    private final ModelMapper modelMapper;
    public AbastecimentoService(AbastecimentoRepository abastecimentoRepository, ModelMapper modelMapper){
        this.abastecimentoRepository = abastecimentoRepository;
        this.modelMapper = modelMapper;
    }

    public Page<AbastecimentoDTO> getAll(Pageable pageable, String placa) {
        Page<Abastecimento> abastecimentos;

         if(Objects.nonNull(placa) && !placa.isBlank()){
            abastecimentos = abastecimentoRepository.findAll(pageable, placa);
        } else{
            abastecimentos = abastecimentoRepository.findAll(pageable);
        }

        return abastecimentos.map(abastecimento -> modelMapper.map(abastecimento, AbastecimentoDTO.class));
    }

     public AbastecimentoDTO add(AbastecimentoRequestDTO abastecimentoRequestDTO){
        validateAbastecimento(abastecimentoRequestDTO);
         Abastecimento abastecimento = modelMapper.map(abastecimentoRequestDTO, Abastecimento.class);
        Abastecimento abastecimentoSaved =  abastecimentoRepository.save(abastecimento);
        return modelMapper.map(abastecimentoSaved, AbastecimentoDTO.class);
     }

    public void delete(Long id){
        abastecimentoRepository.deleteById(id);
    }

    private void validateAbastecimento(AbastecimentoRequestDTO abastecimentoRequestDTO) {
        Abastecimento ultimoAbastecimento = abastecimentoRepository.findFirstByPlacaVeiculoOrderByQuilometragemDesc(abastecimentoRequestDTO.getPlacaVeiculo());
        if(Objects.nonNull(ultimoAbastecimento) && abastecimentoRequestDTO.getQuilometragem() <= ultimoAbastecimento.getQuilometragem()){
              throw new BusinessException("A quilometragem deve ser maior que a do último abastecimento para este veículo");
        }
        String placa = abastecimentoRequestDTO.getPlacaVeiculo();
       if(!placa.matches("[A-Z]{3}-\\d{4}|[A-Z]{3}\\d[A-Z]\\d{2}")){
            throw new BusinessException("A placa informada é inválida");
       }
    }
}