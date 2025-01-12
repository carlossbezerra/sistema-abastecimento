package com.sistema_controle_abastecimento.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbastecimentoRequestDTO {
    @NotNull(message = "A data e hora são obrigatórios")
    @Future(message = "A data e hora não podem ser no futuro")
    private LocalDateTime dataHora;
    @NotBlank(message = "A placa do veículo é obrigatória")
    private String placaVeiculo;
    @NotNull(message = "A quilometragem é obrigatória")
    @Positive(message = "A quilometragem deve ser maior que zero")
    private Integer quilometragem;
    @NotNull(message = "O valor total do abastecimento é obrigatório")
    @Positive(message = "O valor total do abastecimento deve ser maior que zero")
    private Double valorTotal;
}