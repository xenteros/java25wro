package com.application.jedzonko.SavingOrdersToPDF;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


public class PdfCreator {

    public static void createPDF(String barcode) throws Exception {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.COURIER, 11);
        contentStream.newLineAtOffset(100,600);
        contentStream.showText("Order number: "+ barcode);
        contentStream.endText();


        byte[] pngBytes = QRCodeGenerator.generateQRCodePNG(barcode);

        PDImageXObject image =  PDImageXObject.createFromByteArray(document, pngBytes, "QRcode.png");
        contentStream.drawImage(image, 0, 0);
        contentStream.close();

        document.save("newPdfDoc.pdf");
        document.close();
    }

}
