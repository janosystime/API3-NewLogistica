package com.fleetops.backend.CsvParaJson.dto.response;

import java.util.List;

public class ResultadoLinhasDoCsv<T> {
    private List<T> validos;
    private List<ErroLinhasDoCsv> invalidos;

    public ResultadoLinhasDoCsv(List<T> veiculos, List<ErroLinhasDoCsv> erros) {

        this.validos = veiculos;
        this.invalidos = erros;
    }

    public List<ErroLinhasDoCsv> getInvalidos() {
        return invalidos;
    }

    public void setInvalidos(List<ErroLinhasDoCsv> Invalidos) {
        this.invalidos = Invalidos;
    }

    public List<T> getValidos() {
        return validos;
    }

    public void setValidos(List<T> Validos) {
        this.validos = Validos;
    }

    public void addValidos(T veiculo) {
        this.validos.add(veiculo);
    }

    public void addErros(ErroLinhasDoCsv erro) {
        this.invalidos.add(erro);
    }
}
