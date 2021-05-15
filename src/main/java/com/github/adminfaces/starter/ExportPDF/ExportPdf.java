package com.github.adminfaces.starter.ExportPDF;

import java.io.ByteArrayInputStream;


import java.io.ByteArrayOutputStream;
import java.util.List;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.github.adminfaces.starter.model.Agency;

import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


import com.github.adminfaces.starter.model.AgencyPage;
import com.github.adminfaces.starter.model.Agencysearchcriterea;
import com.github.adminfaces.starter.model.Review;
import com.github.adminfaces.starter.repository.AgencyRepository;
import com.github.adminfaces.starter.service.IAgencyService;


public class ExportPdf {
	public static ByteArrayInputStream AgenciesReport(List<Agency> agencies) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(80);
			table.setWidths(new int[] { 4, 4, 4, 4, 4 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Email", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Address", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("City", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Country", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Nombre Agents", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (Agency agency : agencies) {

				PdfPCell cell;

				cell = new PdfPCell(new Phrase(agency.getEmail()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(agency.getAdress()));
				cell.setPaddingLeft(2);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(agency.getCity()));
				cell.setPaddingLeft(2);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(agency.getCountry()));
				cell.setPaddingLeft(2);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(agency.getNb_agent())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				table.addCell(cell);
			}

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(table);

			document.close();

		} catch (DocumentException ex) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	


	public ResponseEntity<InputStreamResource> employeeReports(HttpServletResponse response) throws IOException {

					List<Agency> allAgencies = agencyservice.retrieveAllAgencies();

					ByteArrayInputStream bis = ExportPdf.AgenciesReport(allAgencies);

					HttpHeaders headers = new HttpHeaders();

					headers.add("Content-Disposition", "attachment;filename=agencies.pdf");

					return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
							.body(new InputStreamResource(bis));
				   }

}
