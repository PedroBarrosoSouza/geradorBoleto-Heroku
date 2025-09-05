package com.projeto.geradorBoleto.controller;

import com.projeto.geradorBoleto.dto.BoletoDto;
import com.projeto.geradorBoleto.dto.BoletoResponseDto;
import com.projeto.geradorBoleto.service.IBoletoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

/**
 * Classe de teste de integração para o BoletoController.
 */
@WebMvcTest(BoletoController.class)
public class BoletoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBoletoService boletoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGerarBoleto_deveRetornarStatus200Ejson() throws Exception {
        // Dados de requisição para o teste
        BoletoDto boletoDto = new BoletoDto();
        boletoDto.setNome("João da Silva Fulano");
        boletoDto.setCpf("12345678900");
        boletoDto.setEndereco("Rua Teste, 123 - Bairro teste");

        // Conteúdo de PDF em Base64 para simulação
        String mockPdfBase64 = "JVBERi0xLjQKJSVFT0YK";
        BoletoResponseDto mockResponseDto = new BoletoResponseDto(mockPdfBase64);

        // Simula a resposta do serviço, retornando o DTO
        when(boletoService.gerarPdfBoleto(any(BoletoDto.class))).thenReturn(mockResponseDto);

        // Executa a requisição POST e valida o resultado
        mockMvc.perform(post("/boletos/gerar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boletoDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.pdfBase64").value(mockPdfBase64));
    }
}
