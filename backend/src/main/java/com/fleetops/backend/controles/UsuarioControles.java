package com.fleetops.backend.controles;

import com.fleetops.backend.entidades.Usuario;
import com.fleetops.backend.repositorios.UsuarioRepositorio;
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
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioControles {

    private final UsuarioRepositorio repositorio;

    public UsuarioControles(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return repositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario novoUsuario) {
        Usuario salvo = repositorio.save(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}

