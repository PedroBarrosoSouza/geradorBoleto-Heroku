package com.projeto.geradorBoleto.service.impl;

import com.projeto.geradorBoleto.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Envia um e-mail com o bolepara em PDF como anexo.
     *
     * @param para E-mail do destinatário.
     * @param assunto assunto do e-mail.
     * @param corpoTexto Texto do corpo da mensagem.
     * @param pdfBytes Conteúdo do PDF em bytes.
     */
    @Override
    public void sendBoletoEmail(String para, String assunto, String corpoTexto, byte[] pdfBytes) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(para);
            helper.setSubject(assunto);
            helper.setText(corpoTexto);

            // Adiciona o PDF como anexo
            helper.addAttachment("boleto.pdf", new ByteArrayResource(pdfBytes));

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar o e-mail.", e);
        }
    }
}
