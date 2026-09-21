package com.fleetops.backend.controles;

import com.fleetops.backend.entidades.Avaliacao;
import com.fleetops.backend.repositorios.AvaliacaoRepositorio;
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
@RequestMapping("/api/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoControles {

    private final AvaliacaoRepositorio repositorio;

    public AvaliacaoControles(AvaliacaoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<Avaliacao> listarTodos() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Long id) {
        return repositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criar(@RequestBody Avaliacao novaAvaliacao) {
        Avaliacao salva = repositorio.save(novaAvaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }
}

