package com.fleetops.backend.controles;

import com.fleetops.backend.entidades.Agregado;
import com.fleetops.backend.repositorios.AgregadoRepositorio;
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
@RequestMapping("/api/agregados")
@CrossOrigin(origins = "*")
public class AgregadoControles {

    private final AgregadoRepositorio repositorio;

    public AgregadoControles(AgregadoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<Agregado> listarTodos() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agregado> buscarPorId(@PathVariable Long id) {
        return repositorio
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Agregado> criar(@RequestBody Agregado novoAgregado) {
        Agregado salvo = repositorio.save(novoAgregado);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}
