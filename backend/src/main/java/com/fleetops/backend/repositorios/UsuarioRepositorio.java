package com.fleetops.backend.repositorios;

import com.fleetops.backend.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {}
