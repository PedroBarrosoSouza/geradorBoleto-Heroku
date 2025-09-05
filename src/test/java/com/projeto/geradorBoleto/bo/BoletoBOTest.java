package com.projeto.geradorBoleto.bo;

import com.projeto.geradorBoleto.dto.BoletoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de teste de unidade para a lógica de negócio de geração de boleto.
 */
@ExtendWith(MockitoExtension.class)
public class BoletoBOTest {

    private BoletoBO boletoBO;

    @BeforeEach
    public void setUp() {
        this.boletoBO = new BoletoBO();
    }

    @Test
    void testGerarPdfBoletoDeveRetornarBytesValidos() {
        // Dados de teste
        BoletoDto dto = new BoletoDto();
        dto.setNome("João da Silva");
        dto.setCpf("123.456.789-00");
        dto.setEndereco("Rua Teste, 123");
        dto.setEmail("joao.silva@teste.com");
        dto.setValor(9999);

        byte[] pdfBytes = boletoBO.gerarPdfBoleto(dto);

        assertNotNull(pdfBytes);
        assertTrue(pdfBytes.length > 0);
    }
}
