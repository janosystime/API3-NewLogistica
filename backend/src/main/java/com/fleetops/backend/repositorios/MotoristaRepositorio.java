package com.fleetops.backend.repositorios;

import com.fleetops.backend.entidades.Motorista;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoristaRepositorio extends JpaRepository<Motorista, Long> {

    List<Motorista> findAll();

    List<Motorista> findByStatus(String status);
}
