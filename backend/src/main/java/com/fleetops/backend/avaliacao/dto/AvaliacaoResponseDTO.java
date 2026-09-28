package com.fleetops.backend.avaliacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de resposta após salvar a avaliação.
 * Response esperado (SCRUM-48):
 * - confirmação do salvamento
 * - id da avaliação
 * - data do registro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Confirmação do registro da avaliação de frete")
public class AvaliacaoResponseDTO {

    @Schema(description = "Mensagem de confirmação", example = "Avaliação registrada com sucesso")
    private String mensagem;

    @Schema(description = "ID gerado da avaliação", example = "1")
    private Long id;

    @Schema(description = "ID do motorista avaliado", example = "42")
    private Long motoristaId;

    @Schema(description = "Nota registrada (1 a 5)", example = "4")
    private Integer nota;

    @Schema(description = "Descrição da escala da nota", example = "Bom")
    private String descricaoNota;

    @Schema(description = "Feedback registrado")
    private String feedback;

    @Schema(description = "Data e hora do registro da avaliação", example = "2026-09-17T13:45:00")
    private LocalDateTime dataRegistro;
}
