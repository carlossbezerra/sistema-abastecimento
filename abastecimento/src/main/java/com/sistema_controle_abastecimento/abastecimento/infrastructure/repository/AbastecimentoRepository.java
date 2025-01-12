package com.sistema_controle_abastecimento.infrastructure.repository;

import com.sistema_controle_abastecimento.domain.entity.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {

    @Query("SELECT a FROM Abastecimento a WHERE a.placaVeiculo LIKE %:placa%")
    List<Abastecimento> findByPlacaVeiculo(@Param("placa") String placa);

    @Query("SELECT a FROM Abastecimento a WHERE a.placaVeiculo = :placa ORDER BY a.quilometragem DESC limit 1")
    Abastecimento findFirstByPlacaVeiculoOrderByQuilometragemDesc(@Param("placa") String placa);
}