package com.fleetops.backend.entidades;

import lombok.Getter;

@Getter
public enum TipoVeiculo {
    URBANOS_EXPRESS("Urbanos Express"),
    URBANOS_DE_CARGA("Urbanos de Carga"),
    PESADOS("Pesados"),
    ARTICULADOS("Articulados");

    private final String descricao;

    TipoVeiculo(String descricao) {
        this.descricao = descricao;
    }
}
