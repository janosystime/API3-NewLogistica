package com.fleetops.backend.CsvParaJson.excessoes.tipos;

public class NaoFoiPossivelAcharEncoding extends RuntimeException {
    private String mensagem;

    public NaoFoiPossivelAcharEncoding(String titulo, String mensagem) {
        super(titulo);
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
