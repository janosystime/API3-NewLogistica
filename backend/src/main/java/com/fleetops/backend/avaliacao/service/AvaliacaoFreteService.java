package com.fleetops.backend.avaliacao.service;

import com.fleetops.backend.avaliacao.domain.AvaliacaoFrete;
import com.fleetops.backend.avaliacao.domain.EscalaNota;
import com.fleetops.backend.avaliacao.dto.AvaliacaoRequestDTO;
import com.fleetops.backend.avaliacao.dto.AvaliacaoResponseDTO;
import com.fleetops.backend.avaliacao.repository.AvaliacaoFreteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço de avaliações de frete.
 * Contém a lógica de negócio e permanece isolado no pacote avaliacao
 * para facilitar futura migração para microsserviço.
 */
@Service
@RequiredArgsConstructor
public class AvaliacaoFreteService {

    private final AvaliacaoFreteRepository repository;

    @Transactional
    public AvaliacaoResponseDTO registrar(AvaliacaoRequestDTO request) {
        // Validação adicional da escala (já coberta pelo @Min/@Max, mas reforça regra de negócio)
        EscalaNota escala = EscalaNota.fromValor(request.getNota());

        AvaliacaoFrete avaliacao = AvaliacaoFrete.builder()
                .nota(request.getNota())
                .feedback(request.getFeedback().trim())
                .motoristaId(request.getMotoristaId())
                .build();

        AvaliacaoFrete salva = repository.save(avaliacao);

        return AvaliacaoResponseDTO.builder()
                .mensagem("Avaliação registrada com sucesso")
                .id(salva.getId())
                .motoristaId(salva.getMotoristaId())
                .nota(salva.getNota())
                .descricaoNota(escala.getDescricao())
                .feedback(salva.getFeedback())
                .dataRegistro(salva.getDataRegistro())
                .build();
    }
}
