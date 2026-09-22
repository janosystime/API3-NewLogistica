package com.fleetops.backend.statusMotorista.service;

import com.fleetops.backend.statusMotorista.domain.Motorista;
import com.fleetops.backend.statusMotorista.domain.Status;
import com.fleetops.backend.statusMotorista.dto.StatusDTO;
import com.fleetops.backend.statusMotorista.exceptions.FluxoStatusNaoEsperadoException;
import com.fleetops.backend.statusMotorista.exceptions.MotoristaNaoEncontradoException;
import com.fleetops.backend.statusMotorista.repository.MotoristaRepository;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StatusMotoristasService {
    private final MotoristaRepository motoristaRepository;

    StatusMotoristasService(MotoristaRepository repository) {
        this.motoristaRepository = repository;
    }

    public ResponseEntity<String> atualizaStatusMotorista(StatusDTO statusDTO) {
        Optional<Motorista> motoristaOpt = this.motoristaRepository.findById(statusDTO.getMotoristaId());
        Motorista motorista =
                motoristaOpt.orElseThrow(() -> new MotoristaNaoEncontradoException(statusDTO.getMotoristaId()));

        if (motorista.getStatus() == Status.Disponível
                && (statusDTO.getStatus() != Status.Em_Rota || statusDTO.getStatus() != Status.Indisponível))
            throw new FluxoStatusNaoEsperadoException(statusDTO.getStatus());

        motorista.atualizaStatus(statusDTO.getStatus());

        return new ResponseEntity<String>("Status alterado com sucesso", HttpStatus.NO_CONTENT);
    }
}
