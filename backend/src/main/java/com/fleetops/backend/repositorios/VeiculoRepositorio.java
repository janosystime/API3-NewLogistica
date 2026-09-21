package com.fleetops.backend.repositorios;

import com.fleetops.backend.entidades.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {}
