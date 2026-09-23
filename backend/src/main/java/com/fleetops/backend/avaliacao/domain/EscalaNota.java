package com.fleetops.backend.avaliacao.domain;

/**
 * Escala oficial das notas de avaliação de frete.
 * Usada para documentação e validação de negócio.
 */
public enum EscalaNota {
    PESSIMO(1, "Péssimo"),
    RUIM(2, "Ruim"),
    REGULAR(3, "Regular"),
    BOM(4, "Bom"),
    EXCELENTE(5, "Excelente");

    private final int valor;
    private final String descricao;

    EscalaNota(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EscalaNota fromValor(int valor) {
        for (EscalaNota e : values()) {
            if (e.valor == valor) {
                return e;
            }
        }
        throw new IllegalArgumentException("Nota inválida: " + valor + ". Aceitos apenas valores de 1 a 5.");
    }
}
