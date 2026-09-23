package com.fleetops.backend.repositorios;

import com.fleetops.backend.entidades.TipoVeiculos;
import com.fleetops.backend.entidades.Veiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByTipoVeiculo(TipoVeiculos tipoVeiculo);
}
