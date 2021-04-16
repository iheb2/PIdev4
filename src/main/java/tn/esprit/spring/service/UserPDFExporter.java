package tn.esprit.spring.service;


import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import tn.esprit.spring.model.Transaction;



 
 
public class UserPDFExporter {
    private List<Transaction> listTransactions;
     
    public UserPDFExporter(List<Transaction> listTransactions) {
        this.listTransactions = listTransactions;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(4);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Transaction ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("amount", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Type", font));
        table.addCell(cell);
         
          
    }
     
    private void writeTableData(PdfPTable table) {
        for (Transaction transaction : listTransactions) {
            table.addCell(String.valueOf(transaction.getId_t()));
            table.addCell(String.valueOf(transaction.getAmount()));
            table.addCell(String.valueOf(transaction.getDateT()));
            table.addCell(transaction.getType().toString());
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Relevé de compte", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
