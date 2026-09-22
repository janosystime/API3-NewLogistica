package com.fleetops.backend.statusMotorista.repository;

import com.fleetops.backend.statusMotorista.domain.Motorista;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    Optional<Motorista> findByIdMotorista(Long idMotorista);
}
