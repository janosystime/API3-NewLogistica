package com.fleetops.backend.repositorios;

import com.fleetops.backend.entidades.Manifesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManifestoRepositorio extends JpaRepository<Manifesto, Long> {}
