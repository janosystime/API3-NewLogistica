package com.fleetops.backend.CsvParaJson.servico;

import com.fleetops.backend.CsvParaJson.dto.ResultadoLinhasDoCsv;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import jakarta.websocket.server.PathParam;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvParaJson<T> {
    @Autowired
    private CriadorDeParse<T> criadorDeParser;

    @Autowired
    private DetectorDeEncoding detectoEncoding;

    @Autowired
    private ValidarLinhasDoCsv<T> validador;

    public ResultadoLinhasDoCsv<T> RetornarJson(@PathParam("arquivo") MultipartFile arquivo, Class<T> tipo) {

        List<T> objetos = this.LerCsv(arquivo, tipo);

        ResultadoLinhasDoCsv<T> resultado = validador.Validar(objetos);

        return resultado;
    }

    private List<T> LerCsv(MultipartFile arquivo, Class<T> tipo) {
        BeanListProcessor<T> processador = new BeanListProcessor<>(tipo);
        CsvParser parser = criadorDeParser.getParser(processador);
        try {
            if (!detectoEncoding.detectarEncoding(arquivo.getBytes())) {
                throw new Error("lixo");
            }

        } catch (IOException e) {
            throw new Error("lixo2");
        }
        try (Reader reader = new InputStreamReader(arquivo.getInputStream(), StandardCharsets.UTF_8)) {
            parser.parse(reader);
        } catch (IOException e) {
            throw new Error("1");
        }

        List<T> veiculos = processador.getBeans();

        return veiculos;
    }
}
