package com.sistema_controle_abastecimento.application.service;

import com.sistema_controle_abastecimento.application.dto.AbastecimentoDTO;
import com.sistema_controle_abastecimento.application.dto.AbastecimentoRequestDTO;
import com.sistema_controle_abastecimento.domain.entity.Abastecimento;
import com.sistema_controle_abastecimento.domain.exception.BusinessException;
import com.sistema_controle_abastecimento.infrastructure.repository.AbastecimentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbastecimentoServiceTest {

    @Mock
    private AbastecimentoRepository abastecimentoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AbastecimentoService abastecimentoService;

    @Test
    void getAllWithPlaca() {
        String placa = "ABC-1234";
        Pageable pageable = Pageable.ofSize(5);
        List<Abastecimento> abastecimentos = Arrays.asList(
                new Abastecimento(LocalDateTime.now(), placa, 1000, 50.0),
                new Abastecimento(LocalDateTime.now(), placa, 2000, 100.0)
        );
        Page<Abastecimento> abastecimentoPage = new PageImpl<>(abastecimentos, pageable, abastecimentos.size());
        when(abastecimentoRepository.findAll(pageable, placa)).thenReturn(abastecimentoPage);
        when(modelMapper.map(any(), eq(AbastecimentoDTO.class))).thenReturn(new AbastecimentoDTO());
        Page<AbastecimentoDTO> abastecimentoDTOS = abastecimentoService.getAll(pageable, placa);

        assertFalse(abastecimentoDTOS.isEmpty());
        verify(abastecimentoRepository, times(1)).findAll(pageable, placa);
    }


    @Test
    void getAllWithoutPlaca() {
        Pageable pageable = Pageable.ofSize(5);
        List<Abastecimento> abastecimentos = Arrays.asList(
                new Abastecimento(LocalDateTime.now(), "ABC-1234", 1000, 50.0),
                new Abastecimento(LocalDateTime.now(), "DEF-5678", 2000, 100.0)
        );
        Page<Abastecimento> abastecimentoPage = new PageImpl<>(abastecimentos, pageable, abastecimentos.size());
        when(abastecimentoRepository.findAll(pageable)).thenReturn(abastecimentoPage);
        when(modelMapper.map(any(), eq(AbastecimentoDTO.class))).thenReturn(new AbastecimentoDTO());
        Page<AbastecimentoDTO> abastecimentoDTOS = abastecimentoService.getAll(pageable, null);
        assertFalse(abastecimentoDTOS.isEmpty());
        verify(abastecimentoRepository, times(1)).findAll(pageable);
    }


    @Test
    void addValidAbastecimento() {
        AbastecimentoRequestDTO requestDTO = new AbastecimentoRequestDTO(LocalDateTime.now(), "ABC-1234", 1000, 50.0);
        Abastecimento abastecimento = new Abastecimento(LocalDateTime.now(), "ABC-1234", 1000, 50.0);
        AbastecimentoDTO abastecimentoDTO = new AbastecimentoDTO(1L, LocalDateTime.now(), "ABC-1234", 1000, 50.0);

        when(modelMapper.map(requestDTO, Abastecimento.class)).thenReturn(abastecimento);
        when(abastecimentoRepository.save(abastecimento)).thenReturn(abastecimento);
        when(modelMapper.map(abastecimento, AbastecimentoDTO.class)).thenReturn(abastecimentoDTO);
        AbastecimentoDTO result = abastecimentoService.add(requestDTO);

        assertEquals(abastecimentoDTO, result);
        verify(abastecimentoRepository, times(1)).save(abastecimento);
    }

    @Test
    void addInvalidPlacaAbastecimento() {
        AbastecimentoRequestDTO requestDTO = new AbastecimentoRequestDTO(LocalDateTime.now(), "ABC1234", 1000, 50.0);
        when(abastecimentoRepository.findFirstByPlacaVeiculoOrderByQuilometragemDesc(anyString())).thenReturn(null);
        BusinessException businessException =  assertThrows(BusinessException.class, () -> abastecimentoService.add(requestDTO));
        assertEquals("A placa informada é inválida", businessException.getMessage());
        verify(abastecimentoRepository, times(0)).save(any());
    }

    @Test
    void addInvalidQuilometragemAbastecimento() {
        AbastecimentoRequestDTO requestDTO = new AbastecimentoRequestDTO(LocalDateTime.now(), "ABC-1234", 1000, 50.0);
        Abastecimento ultimoAbastecimento = new Abastecimento(LocalDateTime.now().minusDays(1), "ABC-1234", 2000, 100.0);
        when(abastecimentoRepository.findFirstByPlacaVeiculoOrderByQuilometragemDesc(anyString())).thenReturn(ultimoAbastecimento);
        BusinessException businessException = assertThrows(BusinessException.class, () -> abastecimentoService.add(requestDTO));
        assertEquals("A quilometragem deve ser maior que a do último abastecimento para este veículo", businessException.getMessage());
        verify(abastecimentoRepository, times(0)).save(any());
    }


    @Test
    void delete() {
        Long id = 1L;
        abastecimentoService.delete(id);
        verify(abastecimentoRepository, times(1)).deleteById(id);
    }
}