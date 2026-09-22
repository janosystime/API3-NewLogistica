package com.fleetops.backend.statusMotorista.dto;

import com.fleetops.backend.statusMotorista.domain.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * DTO de entrada para atualização do status do motorista.
 * Request esperado (SCRUM-46):
 * - motoristaId: ID do motorista
 * - status: Disponivel, Em_Rota, Indisponivel, Indesejado
 */
@Getter
public class StatusDTO {
    @NotNull(message = "O ID do motorista é obrigatório")
    @Schema(description = "ID do motorista vinculado à avaliação", example = "42", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long motoristaId;

    @NotNull(message = "O Status é obrigatório")
    @Schema(description = "Status do Motorista (Disponivel, Em_Rota, Indisponivel, Indesejado)", example = "Disponivel", requiredMode = Schema.RequiredMode.REQUIRED)
    private Status status;
}
