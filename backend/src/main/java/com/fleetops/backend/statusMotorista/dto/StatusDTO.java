package com.fleetops.backend.statusMotorista.dto;

import com.fleetops.backend.statusMotorista.domain.Status;
import lombok.Getter;

@Getter
public class StatusDTO {
    private Long motoristaId;
    private Status status;
}
