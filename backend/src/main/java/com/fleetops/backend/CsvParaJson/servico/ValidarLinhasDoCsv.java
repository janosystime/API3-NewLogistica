package com.fleetops.backend.CsvParaJson.servico;

import com.fleetops.backend.CsvParaJson.dto.response.ErroLinhasDoCsv;
import com.fleetops.backend.CsvParaJson.dto.response.ResultadoLinhasDoCsv;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ValidarLinhasDoCsv<T> {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public ResultadoLinhasDoCsv<T> Validar(List<T> lista) {
        ResultadoLinhasDoCsv<T> resultado = new ResultadoLinhasDoCsv<T>(new ArrayList<>(), new ArrayList<>());
        Long nLinha = 2L;

        for (T objeto : lista) {
            Set<ConstraintViolation<T>> eValido = validator.validate(objeto);

            if (eValido.isEmpty()) {
                resultado.addValidos(objeto);
            } else {
                String msg =
                        eValido.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
                resultado.addErros(new ErroLinhasDoCsv(nLinha, msg));
            }
            nLinha += 1;
        }
        return resultado;
    }
}
