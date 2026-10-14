package com.fleetops.backend.CsvParaJson.servico;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.stereotype.Component;

@Component
public class CriadorDeParse<T> {

    public CsvParser getParser(BeanListProcessor<T> processor) {
        CsvParserSettings csvSettings = new CsvParserSettings();
        csvSettings.setHeaderExtractionEnabled(true);
        csvSettings.detectFormatAutomatically();
        csvSettings.setIgnoreLeadingWhitespaces(true);
        csvSettings.setIgnoreTrailingWhitespaces(true);
        csvSettings.setNullValue("");
        csvSettings.setProcessor(processor);
        return new CsvParser(csvSettings);
    }
}
