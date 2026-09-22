package com.fleetops.backend.statusMotorista.controller;

import com.fleetops.backend.statusMotorista.dto.StatusDTO;
import com.fleetops.backend.statusMotorista.service.StatusMotoristasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusMotoristasController {
  private final StatusMotoristasService motoristaService;

  StatusMotoristasController(StatusMotoristasService service) {
    this.motoristaService = service;
  }

  @PutMapping("/updateStatus")
  public ResponseEntity<String> atualizaStatusMotorista(@RequestBody StatusDTO statusDTO) {
    return motoristaService.atualizaStatusMotorista(statusDTO);
  }
}
