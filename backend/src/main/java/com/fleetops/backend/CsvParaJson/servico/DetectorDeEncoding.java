package com.fleetops.backend.CsvParaJson.servico;

import java.io.IOException;
import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.stereotype.Component;

@Component
public class DetectorDeEncoding {

    public String detectarEncoding(byte[] arquivoEmBytes) throws IOException {
        UniversalDetector detector = new UniversalDetector(null);
        detector.handleData(arquivoEmBytes, 0, arquivoEmBytes.length);
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        detector.reset();

        /*return encoding != null && encoding.equalsIgnoreCase("UTF-8");*/
        return encoding;
    }
}
