package com.projeto.geradorBoleto.bo;

import com.projeto.geradorBoleto.dto.BoletoDto;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import com.google.zxing.oned.Code128Writer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class BoletoBO {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public byte[] gerarPdfBoleto(BoletoDto dto) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);

            NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            BigDecimal valorDecimal = BigDecimal.valueOf(dto.getValor(), 2);
            document.open();

            document.add(new Paragraph("BOLETO PARA PAGAMENTO"));
            document.add(new Paragraph("--------------------------------------------------"));

            document.add(new Paragraph("Nome: " + dto.getNome()));
            document.add(new Paragraph("CPF: " + dto.getCpf()));
            document.add(new Paragraph("Endereço: " + dto.getEndereco()));
            document.add(new Paragraph("Valor: R$ " + nf.format(valorDecimal)));
            document.add(new Paragraph("Data de emissão: " + LocalDate.now().format(FORMATTER)));
            document.add(new Paragraph("Data de vencimento: " + LocalDate.now().plusDays(10).format(FORMATTER)));
            document.add(new Paragraph("--------------------------------------------------"));

            String codigoBarras = "33564.59602 51063.710083 90010.110009 9 90030000455000";
            Image imgCodigoBarras = gerarCodigoBarras(codigoBarras);
            document.add(new Paragraph("Linha Digitável: " + codigoBarras));
            document.add(imgCodigoBarras);

            String payloadPix = "00020126580014BR.GOV.BCB.PIX0136pix@teste.com520400005303986540550.005802BR5920Joao Alberto Teste6009Brasilia62070503***6304ABCD";
            Image qrCode = gerarQrCode(payloadPix, 200, 200);
            document.add(new Paragraph("Pix QR Code:"));
            document.add(qrCode);

            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar boleto", e);
        }
    }

    private Image gerarQrCode(String text, int width, int height) throws WriterException, IOException, BadElementException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutput = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutput);

        return Image.getInstance(pngOutput.toByteArray());
    }

    private Image gerarCodigoBarras(String text) throws WriterException, IOException, BadElementException {
        Code128Writer barcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(text, BarcodeFormat.CODE_128, 300, 100);

        ByteArrayOutputStream pngOutput = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutput);

        return Image.getInstance(pngOutput.toByteArray());
    }
}
