package com.fleetops.backend.entidades;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public enum SubtipoVeiculo {

    // === URBANOS EXPRESS ===
    MOTOCICLETAS("Motocicletas", TipoVeiculo.URBANOS_EXPRESS),
    AUTOMOVEIS("Automóveis", TipoVeiculo.URBANOS_EXPRESS),
    FIORINO("Fiorino", TipoVeiculo.URBANOS_EXPRESS),
    VAN("Van", TipoVeiculo.URBANOS_EXPRESS),

    // === URBANOS DE CARGA ===
    VUC("VUC", TipoVeiculo.URBANOS_DE_CARGA),
    TRES_QUARTOS("3/4", TipoVeiculo.URBANOS_DE_CARGA),

    // === PESADOS ===
    TOCO("Toco ou Semi-pesado", TipoVeiculo.PESADOS),
    TRUCK("Truck ou Pesado", TipoVeiculo.PESADOS),
    BI_TRUCK("Bi-truck ou Pesado", TipoVeiculo.PESADOS),

    // === ARTICULADOS ===
    CAVALO_MECANICO("Cavalo Mecânico", TipoVeiculo.ARTICULADOS),
    CAVALO_MECANICO_TRUCADO("Cavalo Mecânico Trucado", TipoVeiculo.ARTICULADOS),
    SEMI_REBOQUE_2_EIXOS("Semi-reboque com 02 Eixos", TipoVeiculo.ARTICULADOS),
    SEMI_REBOQUE_3_EIXOS("Semi-reboque com 03 Eixos", TipoVeiculo.ARTICULADOS),
    SEMI_REBOQUE_4_EIXOS("Semi-reboque com 04 Eixos", TipoVeiculo.ARTICULADOS);

    private final String descricao;
    private final TipoVeiculo tipo;

    SubtipoVeiculo(String descricao, TipoVeiculo tipo) {
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public static List<SubtipoVeiculo> listarPorTipo(TipoVeiculo tipo) {
        return Arrays.stream(values())
                .filter(subtipo -> subtipo.getTipo() == tipo)
                .collect(Collectors.toList());
    }
}
