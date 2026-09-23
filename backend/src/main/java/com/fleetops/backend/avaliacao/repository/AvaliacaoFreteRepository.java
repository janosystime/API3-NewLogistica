package com.fleetops.backend.avaliacao.repository;

import com.fleetops.backend.avaliacao.domain.AvaliacaoFrete;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de avaliações de frete.
 * Isolado no pacote avaliacao para futura extração em microsserviço.
 */
@Repository
public interface AvaliacaoFreteRepository extends JpaRepository<AvaliacaoFrete, Long> {

    List<AvaliacaoFrete> findByMotoristaIdOrderByDataRegistroDesc(Long motoristaId);
}
