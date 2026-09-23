package com.fleetops.backend.controles;

import com.fleetops.backend.entidades.TipoVeiculos;
import com.fleetops.backend.entidades.Veiculo;
import com.fleetops.backend.repositorios.VeiculoRepositorio;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoControles {

    private final VeiculoRepositorio repositorio;

    public VeiculoControles(VeiculoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<Veiculo> listarTodos() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
        return repositorio
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Veiculo> criar(@RequestBody Veiculo novoVeiculo) {
        Veiculo salvo = repositorio.save(novoVeiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Veiculo> listarPorTipo(@PathVariable TipoVeiculos tipo) {
        return repositorio.findByTipoVeiculo(tipo);
    }
}
