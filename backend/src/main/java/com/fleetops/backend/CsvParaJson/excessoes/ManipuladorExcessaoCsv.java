package com.fleetops.backend.CsvParaJson.excessoes;

import com.fleetops.backend.CsvParaJson.dto.response.ErroDTO;
import com.fleetops.backend.CsvParaJson.excessoes.tipos.ErroDeEncoding;
import com.fleetops.backend.CsvParaJson.excessoes.tipos.ErrodeExtensao;
import com.fleetops.backend.CsvParaJson.excessoes.tipos.NaoFoiPossivelAcharEncoding;
import com.fleetops.backend.CsvParaJson.excessoes.tipos.NaoFoiPossivelLerArquivo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManipuladorExcessaoCsv {
    @ExceptionHandler(ErroDeEncoding.class)
    public ErroDTO manipularErroDeEncoding(ErroDeEncoding erro) {
        ErroDTO resposta = new ErroDTO(erro.getMessage(), erro.getMensagem());
        return resposta;
    }

    @ExceptionHandler(NaoFoiPossivelAcharEncoding.class)
    public ErroDTO manipularNaoAcharEncoding(NaoFoiPossivelAcharEncoding erro) {
        ErroDTO resposta = new ErroDTO(erro.getMessage(), erro.getMensagem());
        return resposta;
    }

    @ExceptionHandler(NaoFoiPossivelLerArquivo.class)
    public ErroDTO manipularNãolerArquivo(NaoFoiPossivelLerArquivo erro) {
        ErroDTO resposta = new ErroDTO(erro.getMessage(), erro.getMensagem());
        return resposta;
    }

    @ExceptionHandler(ErrodeExtensao.class)
    public ErroDTO manipularErrodeExtensao(ErrodeExtensao erro) {
        ErroDTO resposta = new ErroDTO(erro.getMessage(), erro.getMensagem());
        return resposta;
    }
}
