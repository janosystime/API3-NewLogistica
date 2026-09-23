package com.fleetops.backend.statusMotorista.controller;

import com.fleetops.backend.statusMotorista.dto.ResponseErrorDTO;
import com.fleetops.backend.statusMotorista.exceptions.FluxoStatusNaoEsperadoException;
import com.fleetops.backend.statusMotorista.exceptions.MotoristaNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handler global de exceções para retornar mensagens claras de validação.
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(FluxoStatusNaoEsperadoException.class)
    public ResponseEntity<ResponseErrorDTO> handleClienteInvalidoException(FluxoStatusNaoEsperadoException ex) {
        ResponseErrorDTO erroResposta = new ResponseErrorDTO(ex.getMessage());
        return new ResponseEntity<ResponseErrorDTO>(erroResposta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MotoristaNaoEncontradoException.class)
    public ResponseEntity<ResponseErrorDTO> handleMotoristaNaoEncontradoException(MotoristaNaoEncontradoException ex) {
        ResponseErrorDTO erroResposta = new ResponseErrorDTO(ex.getMessage());
        return new ResponseEntity<ResponseErrorDTO>(erroResposta, HttpStatus.NOT_FOUND);
    }
}
