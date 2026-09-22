package com.fleetops.backend.statusMotorista.controller;

import com.fleetops.backend.statusMotorista.dto.StatusDTO;
import com.fleetops.backend.statusMotorista.service.StatusMotoristasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller REST para registro dos statos do motorista.
 * Rota criada conforme SCRUM-25 e documentada com SpringDoc (SCRUM-46).
 *
 * Endpoint isolado no contexto /status/updateStatus para facilitar
 * futura extração como microsserviço independente.
 */
@RestController
@RequestMapping("/status")
@Tag(name = "Status do Motorista", description = "Endpoints para registro e atualização do status do motorista")
public class StatusMotoristasController {
  private final StatusMotoristasService motoristaService;

  StatusMotoristasController(StatusMotoristasService service) {
    this.motoristaService = service;
  }

  @PutMapping("/updateStatus")
  @Operation(summary = "Atualizar status do motorista", description = """
      Recebe status (Disponivel, Em_Rota, Indisponivel, Indesejado) e mostoristaId obrigatórios.
      Fluxo:
        Motorista Com Status Disponível → Operador Realiza Ligação →  Motorista aceita o Frete → Status Muda para “Em Rota”
        Motorista Com Status Disponível → Operador Realiza Ligação → Motorista Recusa → Status muda para Indisponível (Status dura 15 dias ou até o operador alterar)
      """)
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "Status atualizado com sucesso", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
      @ApiResponse(responseCode = "400", description = "Fluxo de Status incorreto, Erro interno do servidor"),
      @ApiResponse(responseCode = "404", description = "Motorista não encontrado")
  })
  public ResponseEntity<String> atualizaStatusMotorista(@RequestBody StatusDTO statusDTO) {
    return motoristaService.atualizaStatusMotorista(statusDTO);
  }
}
