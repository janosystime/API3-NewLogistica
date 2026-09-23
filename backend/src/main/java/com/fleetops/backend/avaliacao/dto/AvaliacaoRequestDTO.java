package com.fleetops.backend.avaliacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de entrada para criação de avaliação de frete.
 * Request esperado (SCRUM-48):
 * - motoristaId: ID do motorista
 * - nota: inteiro de 1 a 5
 * - feedback: texto obrigatório
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para registrar uma avaliação de frete")
public class AvaliacaoRequestDTO {

    @NotNull(message = "O ID do motorista é obrigatório")
    @Schema(
            description = "ID do motorista vinculado à avaliação",
            example = "42",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Long motoristaId;

    @NotNull(message = "A nota é obrigatória")
    @Min(value = 1, message = "A nota deve ser no mínimo 1 (Péssimo)")
    @Max(value = 5, message = "A nota deve ser no máximo 5 (Excelente)")
    @Schema(
            description = "Nota da avaliação (1=Péssimo, 2=Ruim, 3=Regular, 4=Bom, 5=Excelente)",
            example = "4",
            minimum = "1",
            maximum = "5",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer nota;

    @NotBlank(message = "O feedback é obrigatório")
    @Schema(
            description = "Texto de feedback da avaliação",
            example = "Motorista pontual e cuidadoso com a carga.",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String feedback;
}
