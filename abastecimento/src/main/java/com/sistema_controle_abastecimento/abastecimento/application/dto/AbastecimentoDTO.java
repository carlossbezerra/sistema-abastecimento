package com.sistema_controle_abastecimento.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbastecimentoDTO {

    private Long id;
    private LocalDateTime dataHora;
    private String placaVeiculo;
    private Integer quilometragem;
    private Double valorTotal;
}