package com.fleetops.backend.repositorios;

import com.fleetops.backend.entidades.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepositorio extends JpaRepository<Avaliacao, Long> {}
