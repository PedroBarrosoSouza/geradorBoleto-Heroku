package com.projeto.geradorBoleto.service.impl;

import com.projeto.geradorBoleto.bo.BoletoBO;
import com.projeto.geradorBoleto.dto.BoletoDto;
import com.projeto.geradorBoleto.dto.BoletoResponseDto;
import com.projeto.geradorBoleto.service.IBoletoService;
import com.projeto.geradorBoleto.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class BoletoServiceImpl implements IBoletoService {

    private final BoletoBO boletoBO;
    private final IEmailService emailService;

    @Autowired
    public BoletoServiceImpl(BoletoBO boletoBO, IEmailService emailService) {
        this.boletoBO = boletoBO;
        this.emailService = emailService;
    }

    @Override
    public BoletoResponseDto gerarPdfBoleto(BoletoDto boletoDto) {
        byte[] pdfByte = boletoBO.gerarPdfBoleto(boletoDto);

        if (boletoDto.getEmail() != null && !boletoDto.getEmail().isEmpty()){
            emailService.sendBoletoEmail(
                    boletoDto.getEmail(),
                    "Seu Boleto para Pagamento",
                    "Olá, " + boletoDto.getNome() + "! Segue em anexo o seu boleto para pagamento.",
                    pdfByte
            );
        }

        return new BoletoResponseDto(Base64.getEncoder().encodeToString(pdfByte));
    }
}
