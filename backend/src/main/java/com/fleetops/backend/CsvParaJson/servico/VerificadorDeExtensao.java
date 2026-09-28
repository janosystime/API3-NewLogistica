package com.fleetops.backend.CsvParaJson.servico;

import org.springframework.stereotype.Component;

@Component
public class VerificadorDeExtensao {
    public Boolean ECsv(String nomeDoArquivo) {
        String[] splitDoNome = nomeDoArquivo.split("\\.");
        String extensao = splitDoNome[splitDoNome.length - 1];
        return extensao.equals("csv");
    }
}
