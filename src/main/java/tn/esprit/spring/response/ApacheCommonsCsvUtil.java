package tn.esprit.spring.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.models.Client;

public class ApacheCommonsCsvUtil {
	private static String csvExtension = "csv";
	
	public static void customersToCsv(Writer writer, List<Client> customers) throws IOException {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("id", "email", "password", "username", "adress", "cin", "date_of_birth", "firstname", "lastname"));) {
			for (Client customer : customers) {
				List<String> data = Arrays.asList(String.valueOf(customer.getId()), customer.getEmail(),
						customer.getPassword(), customer.getUsername(),customer.getAdress(), customer.getCin(), String.valueOf(customer.getDate_of_birth()), customer.getFirst_name(),customer.getFirst_name() );

				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
		} catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}

	public static List<Client> parseCsvFile(InputStream is) {
		BufferedReader fileReader = null;
		CSVParser csvParser = null;

		List<Client> customers = new ArrayList<Client>();

		try {
			fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			csvParser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
		
			for (CSVRecord csvRecord : csvRecords) {

				Client customer = new Client();
	               System.out.println("Welcome");

				customer.setId(Long.parseLong(csvRecord.get("id")));
	               System.out.println("Welcome");

	               customer.setEmail(csvRecord.get("email"));
	               System.out.println("Welcome");

	               customer.setPassword(csvRecord.get("password"));
	               customer.setUsername(csvRecord.get("username"));
	              System.out.println("Welcome");

	              customer.setAdress(csvRecord.get("adress"));
	              customer.setCin(csvRecord.get("cin"));
	              System.out.println("cin");
	              
	             // Date date_of_birth=new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get("date_of_birth"));  
	             // System.out.println("date");

	            ///  customer.setDate_of_birth(date_of_birth);

	              customer.setFirst_name(csvRecord.get("firstname"));
	              System.out.println("first");

	              customer.setLast_name(csvRecord.get("lastname"));
	              System.out.println("last");


				customers.add(customer);
	              System.out.println("hh");

			}

		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvParser.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}

		return customers;
	}
	
	public static boolean isCSVFile(MultipartFile file) {
		String extension = file.getOriginalFilename().split("\\.")[1];
		
		if(!extension.equals(csvExtension)) {
			return false;
		}

		return true;
	}

}
