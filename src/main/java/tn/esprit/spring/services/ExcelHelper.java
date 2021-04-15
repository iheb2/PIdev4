package tn.esprit.spring.services;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.models.Client;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	@Autowired
	PasswordEncoder encoder;
	 public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "id", "email", "password", "username", "adress", "cin", "Dob", "first_name","last_name" };
	  static String SHEET = "Clients";

	  public static boolean hasExcelFormat(MultipartFile file) {

		    if (!TYPE.equals(file.getContentType())) {
		      return false;
		    }

		    return true;
		  }
	  
	  public static ByteArrayInputStream tutorialsToExcel(List<Client> tutorials) {

	    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	      Sheet sheet = workbook.createSheet(SHEET);

	      // Header
	      Row headerRow = sheet.createRow(0);

	      for (int col = 0; col < HEADERs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(HEADERs[col]);
	      }

	      int rowIdx = 1;
	      for (Client tutorial : tutorials) {
	        Row row = sheet.createRow(rowIdx++);
	        row.createCell(0).setCellValue(tutorial.getId());
	        row.createCell(1).setCellValue(tutorial.getEmail());
	        row.createCell(2).setCellValue(tutorial.getPassword());	        
	        row.createCell(3).setCellValue(tutorial.getUsername());
	        row.createCell(4).setCellValue(tutorial.getAdress());
	        row.createCell(5).setCellValue(tutorial.getCin());

	        row.createCell(6).setCellValue(tutorial.getDate_of_birth());	        
	        row.createCell(7).setCellValue(tutorial.getFirst_name());
	        row.createCell(8).setCellValue(tutorial.getLast_name());

	      }

	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
	    }
	  }
	  
	  public static List<Client> excelToTutorials(InputStream is) {
		    try {
		      Workbook workbook = new XSSFWorkbook(is);

		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();

		      List<Client> tutorials = new ArrayList<Client>();

		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();

		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }

		        Iterator<Cell> cellsInRow = currentRow.iterator();

		        Client tutorial = new Client();

		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();

		          switch (cellIdx) {
		        case 0:
			            tutorial.setId((long) currentCell.getNumericCellValue());
			            break;

			          case 1:  
			        	   tutorial.setEmail(currentCell.getStringCellValue());
				            break;
			            

			          case 2:

				            tutorial.setPassword(currentCell.getStringCellValue());

			            break;

			          case 3:
				            tutorial.setUsername(currentCell.getStringCellValue());

			            break;
			        case 4:
			            tutorial.setAdress(currentCell.getStringCellValue());
				            break;
			          case 5:
				            tutorial.setCin(currentCell.getStringCellValue());

				            break;
			          case 6:
					       tutorial.setDate_of_birth(currentCell.getDateCellValue());

				            break;
			        case 7:
			            tutorial.setFirst_name(currentCell.getStringCellValue());
				            break;
			        case 8:
			            tutorial.setLast_name(currentCell.getStringCellValue());
				            break;
		
		          }

		          cellIdx++;
		        }

		        tutorials.add(tutorial);
		      }

		      workbook.close();

		      return tutorials;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
		  }
	  
	
}
