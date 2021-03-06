package com.ensa.blockchainApp.Business;

import com.ensa.blockchainApp.Core.Block;
import com.itextpdf.text.*;
import lombok.Data;

import java.util.Date;

@Data
public class PDFGeneration {
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    /*

    */

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    public static void addMetaData(Document document) {
        document.addTitle("Reçu de réclamation");
        document.addAuthor("Bureau d ordre App");
    }

    public static void addTitlePage(Block addedBlock, Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Reçu de Réclamation Id: "+addedBlock.getData ().getOrderNumber (), catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph( "Réclamation ajoutée à "+ addedBlock.getData ().getDate ().toInstant ().toString ().substring ( 0,10 ), smallBold));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph(
                "Détail sur la réclamation :  ",
                smallBold));

        preface.add(new Paragraph(
                "Plaignant :  "+ addedBlock.getData ().getComplainer ().getLastName ()+ "   "+addedBlock.getData ().getComplainer ().getFirstName (), smallBold));

        preface.add(new Paragraph(
                "Objet :   "+ addedBlock.getData ().getObject (), smallBold));

        preface.add(new Paragraph(
                "Respondant :  "+ addedBlock.getData ().getComplainee ().getLastName () +"   "+ addedBlock.getData ().getComplainee ().getFirstName (), smallBold));


        addEmptyLine(preface, 8);

        preface.add(new Paragraph(
                "Ce document est réservé au "+addedBlock.getData ().getComplainer ().getLastName ()+"  "+addedBlock.getData ().getComplainer ().getFirstName ()+" en cas de besoin, comme accusé de l'ajout de sa réclamation",
                redFont));

        document.add(preface);
        // Start a new page
        document.newPage();
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
