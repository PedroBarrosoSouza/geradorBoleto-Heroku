package com.projeto.geradorBoleto.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceImplTest {

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MimeMessage mimeMessage;

    @InjectMocks
    private EmailServiceImpl emailService;

    @BeforeEach
    public void setup() {
        // Simula o comportamento do JavaMailSender.
        // Toda vez que ele tenta criar uma MimeMessage, ele retorna nosso mock.
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    @Test
    public void testSendBoletoEmail() throws MessagingException, IOException {
        // Dados de teste
        String to = "pagador@email.com";
        String subject = "Boleto para Pagamento";
        String body = "Segue em anexo o seu boleto.";
        byte[] pdfBytes = "conteúdo do pdf".getBytes(); // Array de bytes simulado

        // Chama o metodo que sera testado
        emailService.sendBoletoEmail(to, subject, body, pdfBytes);

        // Verifica se o metodo de envio foi chamado
        verify(javaMailSender, times(1)).send(mimeMessage);
    }
}
