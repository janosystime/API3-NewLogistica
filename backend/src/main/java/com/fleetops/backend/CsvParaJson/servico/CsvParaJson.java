package com.fleetops.backend.CsvParaJson.servico;

import com.fleetops.backend.CsvParaJson.dto.response.ResultadoLinhasDoCsv;
import com.fleetops.backend.CsvParaJson.excessoes.tipos.ErrodeExtensao;
import com.fleetops.backend.CsvParaJson.excessoes.tipos.NaoFoiPossivelAcharEncoding;
import com.fleetops.backend.CsvParaJson.excessoes.tipos.NaoFoiPossivelLerArquivo;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
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

    @Autowired
    private VerificadorDeExtensao verificadorDeExtensao;

    public ResultadoLinhasDoCsv<T> RetornarJson(MultipartFile arquivo, Class<T> tipo) {

        List<T> objetos = this.LerCsv(arquivo, tipo);

        ResultadoLinhasDoCsv<T> resultado = validador.Validar(objetos);

        return resultado;
    }

    private List<T> LerCsv(MultipartFile arquivo, Class<T> tipo) {
        BeanListProcessor<T> processador = new BeanListProcessor<>(tipo);
        CsvParser parser = criadorDeParser.getParser(processador);

        if (!verificadorDeExtensao.ECsv(arquivo.getOriginalFilename())) {
            throw new ErrodeExtensao("Esse arquivo não possui a extensão correta", "Só permitimos arquivos .csv");
        }

        /*  try {
            if (!detectoEncoding.detectarEncoding(arquivo.getBytes())) {
                throw new ErroDeEncoding(
                        "O arquivo " + arquivo.getOriginalFilename() + " não possui encoding UTF-8",
                        "Passe esse arquivo para o o Encoding UTF-8");
            }

        } catch (IOException e) {
            throw new NaoFoiPossivelAcharEncoding(
                    "A leitura desse arquivo falhou", "Não foi possivel encontrar o encoding");
        }*/
        String encoding;
        try {
            encoding = detectoEncoding.detectarEncoding(arquivo.getBytes());
        } catch (IOException e) {
            throw new NaoFoiPossivelAcharEncoding(
                    "A leitura desse arquivo falhou", "Não foi possivel encontrar o encoding");
        }
        ;
        System.out.println(encoding);
        try (Reader reader = new InputStreamReader(arquivo.getInputStream(), Charset.forName(encoding))) {
            parser.parse(reader);
        } catch (IOException e) {
            throw new NaoFoiPossivelLerArquivo(
                    "O arquivo " + arquivo.getOriginalFilename() + " não foi lido", "Problema ao ler esse arquivo");
        }

        List<T> veiculos = processador.getBeans();

        return veiculos;
    }
}
