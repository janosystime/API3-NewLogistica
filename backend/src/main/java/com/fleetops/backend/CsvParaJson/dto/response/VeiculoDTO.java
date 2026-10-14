package com.fleetops.backend.CsvParaJson.dto.response;

import com.univocity.parsers.annotations.Parsed;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class VeiculoDTO {
    @Parsed(field = "Placa (id)")
    @NotBlank(message = "O Veiculo Precisa ter uma Placa")
    @NotNull(message = "Placa Precisa ter Valor")
    private String Placa;

    @Parsed(field = "Agregado")
    @NotBlank(message = "O Veiculo Precisa ter um  Agragado")
    @NotNull(message = "Agragado Precisa ter valor")
    @Pattern(regexp = "^[^0-9]*$", message = "O campo Agragado não pode conter números")
    private String Agregado;

    @Parsed(field = "Status do Veículo")
    @NotBlank(message = "O Veiculo Precisa ter um Status")
    @NotNull(message = "Status precisa ter valor")
    @Pattern(regexp = "Ativo|Inativo", message = "Status deve ser Ativo ou Inativo")
    private String Status;

    @Parsed(field = "Motorista Padrão")
    @NotBlank(message = "O Veiculo Precisa ter um Motorista")
    @NotNull(message = "O Motorista precisa ter valor")
    @Pattern(regexp = "^[^0-9]*$", message = "O campo Motorista não pode conter números")
    private String Motorista;

    @Parsed(field = "Tipo Veículo")
    @NotBlank(message = "O Veiculo Precisa ter um Tipo")
    @NotNull(message = "TipoVeiculo Precisa ter valor")
    @Pattern(regexp = "^[^0-9]*$", message = "O campo TipoVeiculo não pode conter números")
    private String TipoVeiculo;

    public VeiculoDTO(String placa, String agregado, String Status, String Motorista, String TipoVeiculo) {
        this.Agregado = agregado;
        this.Motorista = Motorista;
        this.Placa = placa;
        this.Status = Status;
        this.TipoVeiculo = TipoVeiculo;
    }

    public VeiculoDTO() {}

    public String getAgregado() {
        return Agregado;
    }

    public void setAgregado(String agregado) {
        Agregado = agregado;
    }

    public String getMotorista() {
        return Motorista;
    }

    public void setMotorista(String motorista) {
        Motorista = motorista;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTipoVeiculo() {
        return TipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        TipoVeiculo = tipoVeiculo;
    }
}
