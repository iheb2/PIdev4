package tn.esprit.spring.services;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.*;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.models.Client;



public class CSVHelper {

	
	
	   public static String TYPE = "text/csv";
		  static String[] HEADERs = { "id", "email ", "password", "username", "adress", "cin", "date_of_birth", "firstname", "lastname" };

	   public static boolean hasCSVFormat(MultipartFile file) {

	     if (!TYPE.equals(file.getContentType())) {
	       return false;
	     }

	     return true;
	   }

	   public static List<Client> csvToTutorials(InputStream is) {
	     try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	         CSVParser csvParser = new CSVParser(fileReader,
	             CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	       List<Client> tutorials = new ArrayList<Client>();

	       Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	        System.out.println("Welcome");
Long l=(long) 12;
	       for (CSVRecord csvRecord : csvRecords) {
	    	   Client tutorial = new Client();
	               tutorial.setId(l);
	               System.out.println("Welcome");

	               tutorial.setEmail(csvRecord.get("email"));
	               System.out.println("Welcome");

	        	   tutorial.setPassword(csvRecord.get("password"));
	        	   tutorial.setUsername(csvRecord.get("username"));
	              System.out.println("Welcome");

	        	   tutorial.setAdress(csvRecord.get("adress"));
	        	   tutorial.setCin(csvRecord.get("cin"));
	        	   // SimpleDateFormat formatter1=new SimpleDateFormat("yyyy/MM/dd");  
	        	    //Date date1=formatter1.parse(csvRecord.get("date_of_birth"));  
	        	  // tutorial.setDate_of_birth(date1);
	        	   tutorial.setFirst_name(csvRecord.get("first_name"));
	        	   tutorial.setLast_name(csvRecord.get("last_name"));
	         
	         tutorials.add(tutorial);
	       }

	       return tutorials;
	     } catch (IOException e) {
	       throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	     }
	   }
	
	
	
	
	
	

  public static ByteArrayInputStream tutorialsToCSV(List<Client> tutorials) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Client tutorial : tutorials) {
        List<String> data = Arrays.asList(
                String.valueOf(tutorial.getId()),
                tutorial.getEmail(),
                tutorial.getPassword(),
                tutorial.getUsername(),
                tutorial.getAdress(),
        		tutorial.getCin(),
                String.valueOf(tutorial.getDate_of_birth()),
              tutorial.getFirst_name(),
              tutorial.getLast_name()
            );

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }
}
