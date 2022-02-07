package com.example.seraphine.model;

import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Service for PDF.
 */
@Service
public class PDFDownloader {
    private HttpServletResponse response;
    public PDFDownloader(){}

    public void setHttpResponse() {
        this.response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        this.response.setHeader(headerKey, headerValue);
    }

    public void export(String title,
                       String body) throws IOException {
        this.setHttpResponse();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        //Set the size of the text for the title
        fontTitle.setSize(18);

        Paragraph para1 = new Paragraph(title, fontTitle);
        //Set the title to be in the middle of the text
        para1.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        //Set the size of the text for the body
        fontParagraph.setSize(12);

        Paragraph para2 = new Paragraph(body, fontParagraph);
        para2.setAlignment(Paragraph.ALIGN_LEFT);

        //put all the components into one pdf file
        document.add(para1);
        document.add(para2);
        document.close();
    }
}

