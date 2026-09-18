package com.fleetops.backend.CsvParaJson.dto;

public class ErroLinhasDoCsv {
    private Long Linha;
    private String mensagem;

    public ErroLinhasDoCsv(Long Linha, String mensagem) {
        this.Linha = Linha;
        this.mensagem = mensagem;
    }

    public Long getLinha() {
        return Linha;
    }

    public void setLinha(Long linha) {
        Linha = linha;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
