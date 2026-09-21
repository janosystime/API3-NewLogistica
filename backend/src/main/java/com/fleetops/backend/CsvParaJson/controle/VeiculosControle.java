package com.fleetops.backend.CsvParaJson.controle;

import com.fleetops.backend.CsvParaJson.dto.ResultadoLinhasDoCsv;
import com.fleetops.backend.CsvParaJson.dto.VeiculoDTO;
import com.fleetops.backend.CsvParaJson.servico.CsvParaJson;

import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class VeiculosControle {
    @Autowired
    CsvParaJson<VeiculoDTO> csv;

    @PostMapping("/arquivo")
    public ResultadoLinhasDoCsv<VeiculoDTO> csvParaJson(@PathParam("arquivo") MultipartFile arquivo) {
        return csv.RetornarJson(arquivo, VeiculoDTO.class);
    }
}
