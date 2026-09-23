package com.fleetops.backend.controles;

import com.fleetops.backend.entidades.Motorista;
import com.fleetops.backend.entidades.StatusMotorista;
import com.fleetops.backend.repositorios.MotoristaRepositorio;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/motoristas")
@CrossOrigin(origins = "*")
public class MotoristaControles {

    private final MotoristaRepositorio repositorio;

    public MotoristaControles(MotoristaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<Motorista> listarTodos() {
        return repositorio.findAll();
    }

    @GetMapping("/disponiveis")
    public List<Motorista> listarDisponiveis() {
        return repositorio.findByStatus(StatusMotorista.DISPONIVEL);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorista> buscarPorId(@PathVariable Long id) {
        return repositorio
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Motorista> criar(@RequestBody Motorista novoMotorista) {
        Motorista salvo = repositorio.save(novoMotorista);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Motorista> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        return repositorio
                .findById(id)
                .map(motorista -> {
                    if (payload.containsKey("status")) {
                        // Converte o texto recebido no JSON para o Enum correspondente:
                        StatusMotorista novoStatus =
                                StatusMotorista.valueOf(payload.get("status").toUpperCase());
                        motorista.setStatus(novoStatus);
                    }
                    Motorista atualizado = repositorio.save(motorista);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
