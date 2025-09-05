package com.projeto.geradorBoleto.controller;

import com.projeto.geradorBoleto.dto.BoletoDto;
import com.projeto.geradorBoleto.dto.BoletoResponseDto;
import com.projeto.geradorBoleto.service.IBoletoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/boletos")
@Tag(name = "Boletos", description = "Endpoints para geração de boletos")
public class BoletoController {

    @Autowired
    private IBoletoService boletoService;

    public BoletoController(IBoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @PostMapping("/gerar")
    @Operation(summary = "Gerar boleto", description = "Recebe dados do usuário e gera boleto com PDF em base64")
    public BoletoResponseDto gerarBoleto(@RequestBody BoletoDto boletoDto) {
        return boletoService.gerarPdfBoleto(boletoDto);
    }
}
