
package org.example.agence.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.agence.model.Reservation;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

@Service
public class PdfService {

    public String genererPdfReservation(Reservation reservation) throws Exception {

        String path = System.getProperty("user.home") + "/TP_GraphQl/reservation_"
                + (reservation.getReference() == null || reservation.getReference().isEmpty() ? "temp" : reservation.getReference())
                + ".pdf";

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLUE);
        Paragraph title = new Paragraph("Confirmation de Réservation", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" "));


        PdfPTable tableClient = new PdfPTable(2);
        tableClient.setWidthPercentage(100);
        tableClient.setSpacingBefore(10f);
        tableClient.setSpacingAfter(10f);

        addCell(tableClient, "Nom", true);
        addCell(tableClient, reservation.getClient().getNom(), false);
        addCell(tableClient, "Prénom", true);
        addCell(tableClient, reservation.getClient().getPrenom(), false);
        addCell(tableClient, "Email", true);
        addCell(tableClient, reservation.getClient().getEmail(), false);
        addCell(tableClient, "Référence", true);
        addCell(tableClient, reservation.getReference(), false);

        document.add(tableClient);


        PdfPTable tableChambre = new PdfPTable(2);
        tableChambre.setWidthPercentage(100);
        tableChambre.setSpacingBefore(10f);
        tableChambre.setSpacingAfter(10f);

        addCell(tableChambre, "Type chambre", true);
        addCell(tableChambre, reservation.getChambre().getType(), false);
        addCell(tableChambre, "Nombre de lits", true);
        addCell(tableChambre, String.valueOf(reservation.getChambre().getNombreLits()), false);

        double prixParNuitReduit = reservation.getPrixTotal() / reservation.getNombreNuits();
        addCell(tableChambre, "Prix par nuit (réduit)", true);
        addCell(tableChambre, String.format("%.2f €", prixParNuitReduit), false);

        document.add(tableChambre);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        PdfPTable tableDates = new PdfPTable(2);
        tableDates.setWidthPercentage(50);
        tableDates.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tableDates.setSpacingBefore(10f);
        tableDates.setSpacingAfter(10f);

        addCell(tableDates, "Date début", true);
        addCell(tableDates, sdf.format(reservation.getDateDebut()), false);
        addCell(tableDates, "Date fin", true);
        addCell(tableDates, sdf.format(reservation.getDateFin()), false);
        addCell(tableDates, "Nombre de nuits", true);
        addCell(tableDates, String.valueOf(reservation.getNombreNuits()), false);
        addCell(tableDates, "Prix total", true);
        addCell(tableDates, String.format("%.2f €", reservation.getPrixTotal()), false);

        document.add(tableDates);


        Paragraph footer = new Paragraph("Merci pour votre réservation !\nNous vous souhaitons un agréable séjour.",
                FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY));
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setSpacingBefore(20f);
        document.add(footer);

        document.close();
        return path;
    }


    private void addCell(PdfPTable table, String text, boolean isHeader) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, isHeader ? 12 : 11, isHeader ? Font.BOLD : Font.NORMAL)));
        if (isHeader) {
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        cell.setPadding(5f);
        table.addCell(cell);
    }
}
