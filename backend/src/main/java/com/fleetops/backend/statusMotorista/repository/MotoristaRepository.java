package com.fleetops.backend.statusMotorista.repository;

import com.fleetops.backend.statusMotorista.domain.Motorista;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de motoristas.
 * Isolado no pacote statusMotorista para futura extração em microsserviço.
 */
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    Optional<Motorista> findByIdMotorista(Long idMotorista);
}
