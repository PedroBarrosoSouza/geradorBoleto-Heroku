package com.projeto.geradorBoleto.service.impl;

import com.projeto.geradorBoleto.bo.BoletoBO;
import com.projeto.geradorBoleto.dto.BoletoDto;
import com.projeto.geradorBoleto.dto.BoletoResponseDto;
import com.projeto.geradorBoleto.service.IEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class BoletoServiceImplTest {

    @Mock
    private BoletoBO boletoBO;

    @Mock
    private IEmailService emailService;

    @InjectMocks
    private BoletoServiceImpl boletoService;

    @Test
    void testGerarBoleto() {
        BoletoDto dto = new BoletoDto();
        dto.setNome("João da Silva Teste");
        dto.setCpf("12345678900");
        dto.setEndereco("Rua dos Testes, 123");
        dto.setEmail("teste@email.com");

        // Simula o comportamento do BoletoBO para retornar um PDF em bytes
        byte[] pdfBytes = "conteudo-do-pdf-simulado".getBytes();
        when(boletoBO.gerarPdfBoleto(dto)).thenReturn(pdfBytes);

        // Chama o metodo que sera testado
        BoletoResponseDto responseDto = boletoService.gerarPdfBoleto(dto);

        // 1. Verifica se o metodo de geracao de boleto no BO foi chamado com o DTO correto
        verify(boletoBO, times(1)).gerarPdfBoleto(dto);

        // 2. Verifica se o metodo de envio de email foi chamado com os parametros corretos
        verify(emailService, times(1)).sendBoletoEmail(
                eq(dto.getEmail()),
                eq("Seu Boleto para Pagamento"),
                eq("Olá, " + dto.getNome() + "! Segue em anexo o seu boleto para pagamento."),
                eq(pdfBytes)
        );

        // 3. Verifica se a resposta da API não é nula e é uma string Base64
        assertNotNull(responseDto);
    }
}
