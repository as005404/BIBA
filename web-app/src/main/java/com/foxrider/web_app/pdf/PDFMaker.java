package com.foxrider.web_app.pdf;

import com.foxrider.entity.ValueOfSensors;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

@Component
public class PDFMaker {


    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);


    public static boolean createPDF(Iterable<ValueOfSensors> iterable, String Filename) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(Filename));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document, iterable);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            document.close();
        }
    }

    private static void addMetaData(Document document) {
        document.addTitle("Values of sensors");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Anton Isachenka");
        document.addCreator("Anton Isachenka");
    }

    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Values of sensors of washing machines", catFont));
        addEmptyLine(preface, 1);
        document.add(preface);
    }

    private static void addContent(Document document, Iterable<ValueOfSensors> iterable) throws DocumentException {
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Table of values of washing machines", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph(" "));

        // add a table
        createTable(subCatPart, iterable);

        // now add all this to the document
        document.add(catPart);

    }

    private static void createTable(Section subCatPart, Iterable<ValueOfSensors> it)
            throws BadElementException {
        PdfPTable table = new PdfPTable(5);

        PdfPCell c1 = new PdfPCell(new Phrase("User"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Shift"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Sensor"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Value"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date and Time"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (ValueOfSensors v : it) {
            table.addCell(v.getPerson().getUserEmail());
            table.addCell(v.getShift().getShiftName());
            table.addCell(v.getSensor().getSensorName());
            table.addCell(v.getValue().toString());
            table.addCell(v.getDateTime().toString());
        }
        subCatPart.add(table);

    }


    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}

