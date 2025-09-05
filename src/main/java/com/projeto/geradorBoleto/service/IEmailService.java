package com.projeto.geradorBoleto.service;

public interface IEmailService {
    void sendBoletoEmail(String para, String assunto, String corpoTexto, byte[] pdfBytes);
}
