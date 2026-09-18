package com.fleetops.backend.CsvParaJson.dto;

import java.util.List;

public class ResultadoLinhasDoCsv<T> {
    private List<T> validos;
    private List<ErroLinhasDoCsv> invalidos;

    public ResultadoLinhasDoCsv(List<T> veiculos, List<ErroLinhasDoCsv> erros) {

        this.validos = veiculos;
        this.invalidos = erros;
    }

    public List<ErroLinhasDoCsv> getVeiculosInvalidos() {
        return invalidos;
    }

    public void setVeiculosInvalidos(List<ErroLinhasDoCsv> Invalidos) {
        this.invalidos = Invalidos;
    }

    public List<T> getVeiculosValidos() {
        return validos;
    }

    public void setVeiculosValidos(List<T> veiculosValidos) {
        this.validos = veiculosValidos;
    }

    public void addVeiculosValidos(T veiculo) {
        this.validos.add(veiculo);
    }

    public void addErros(ErroLinhasDoCsv erro) {
        this.invalidos.add(erro);
    }
}
