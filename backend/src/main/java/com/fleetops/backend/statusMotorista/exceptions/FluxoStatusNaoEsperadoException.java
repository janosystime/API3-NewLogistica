package com.fleetops.backend.statusMotorista.exceptions;

import com.fleetops.backend.statusMotorista.domain.Status;

public class FluxoStatusNaoEsperadoException extends RuntimeException {
  public FluxoStatusNaoEsperadoException(Status status) {
    super("Não é possível alterar diretamente o status atual para " + status);
  }
}
