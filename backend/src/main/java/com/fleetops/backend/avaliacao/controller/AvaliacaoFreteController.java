package com.fleetops.backend.avaliacao.controller;

import com.fleetops.backend.avaliacao.dto.AvaliacaoRequestDTO;
import com.fleetops.backend.avaliacao.dto.AvaliacaoResponseDTO;
import com.fleetops.backend.avaliacao.service.AvaliacaoFreteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller REST para registro de avaliações de frete.
 * Rota criada conforme SCRUM-28 e documentada com SpringDoc (SCRUM-48).
 *
 * Endpoint isolado no contexto /api/avaliacoes para facilitar
 * futura extração como microsserviço independente.
 */
@RestController
@RequestMapping("/api/avaliacoes")
@RequiredArgsConstructor
@Tag(name = "Avaliações de Frete", description = "Endpoints para registro e consulta de avaliações de motoristas")
public class AvaliacaoFreteController {

    private final AvaliacaoFreteService service;

    @PostMapping
    @Operation(
            summary = "Registrar avaliação de frete",
            description =
                    """
                    Recebe nota (1 a 5) e feedback obrigatórios, vinculando-os ao motorista informado.
                    Escala: 1=Péssimo, 2=Ruim, 3=Regular, 4=Bom, 5=Excelente.
                    A avaliação fica associada ao motorista e à data de registro para uso futuro no ranking.
                    """)
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Avaliação registrada com sucesso",
                content = @Content(schema = @Schema(implementation = AvaliacaoResponseDTO.class))),
        @ApiResponse(
                responseCode = "400",
                description = "Dados inválidos (nota fora de 1-5, campos obrigatórios ausentes)"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<AvaliacaoResponseDTO> registrar(@Valid @RequestBody AvaliacaoRequestDTO request) {
        AvaliacaoResponseDTO response = service.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
