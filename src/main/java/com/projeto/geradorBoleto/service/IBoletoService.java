package com.projeto.geradorBoleto.service;

import com.projeto.geradorBoleto.dto.BoletoDto;
import com.projeto.geradorBoleto.dto.BoletoResponseDto;

public interface IBoletoService {
    BoletoResponseDto gerarPdfBoleto(BoletoDto request);
}
