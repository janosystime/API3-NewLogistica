package com.fleetops.backend.statusMotorista.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de saida para enviar uma mensagem de erro.
 * - mensagem: Mensagem de erro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseErrorDTO {
    private String mensagem;
}
