package com.fleetops.backend.CsvParaJson.excessoes.tipos;

public class ErrodeExtensao  extends RuntimeException {
      private String mensagem;

    public ErrodeExtensao(String titulo, String mensagem) {
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
