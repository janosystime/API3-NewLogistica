package com.fleetops.backend.statusMotorista.exceptions;

public class MotoristaNaoEncontradoException extends RuntimeException {
    public MotoristaNaoEncontradoException(Long motoristaId) {
        super("Não é possível encontrar o motorista " + motoristaId);
    }
}
