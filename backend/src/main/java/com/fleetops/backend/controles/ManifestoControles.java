package com.fleetops.backend.controles;

import com.fleetops.backend.entidades.Manifesto;
import com.fleetops.backend.repositorios.ManifestoRepositorio;
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
@RequestMapping("/api/manifestos")
@CrossOrigin(origins = "*")
public class ManifestoControles {

    private final ManifestoRepositorio repositorio;

    public ManifestoControles(ManifestoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<Manifesto> listarTodos() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manifesto> buscarPorId(@PathVariable Long id) {
        return repositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Manifesto> criar(@RequestBody Manifesto novoManifesto) {
        Manifesto salvo = repositorio.save(novoManifesto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}

