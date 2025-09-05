package com.projeto.geradorBoleto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class BoletoResponseDto {

    @Schema(description = "Arquivo PDF convertido para Base64")
    private String pdfBase64;

    public BoletoResponseDto(String pdfBase64) {
        this.pdfBase64 = pdfBase64;
    }

    public String getPdfBase64() {
        return pdfBase64;
    }

    public void setPdfBase64(String pdfBase64) {
        this.pdfBase64 = pdfBase64;
    }
}
