package tn.esprit.spring.response;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import tn.esprit.spring.models.Client;

public class OpenCsvUtil {

	public static List<Client> parseCsvFile(InputStream is) {
		String[] CSV_HEADER = { "id", "email ", "password", "username", "adress", "cin", "date_of_birth", "firstname",
				"lastname" };
		Reader fileReader = null;
		CsvToBean<Client> csvToBean = null;

		List<Client> customers = new ArrayList<Client>();

		try {
			fileReader = new InputStreamReader(is);

			ColumnPositionMappingStrategy<Client> mappingStrategy = new ColumnPositionMappingStrategy<Client>();

			mappingStrategy.setType(Client.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);

			csvToBean = new CsvToBeanBuilder<Client>(fileReader).withMappingStrategy(mappingStrategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).build();

			customers = csvToBean.parse();

			return customers;
		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}

		return customers;
	}

	public static void customersToCsv(Writer writer, List<Client> customers) {
		String[] CSV_HEADER = { "id", "email", "password", "username", "adress", "cin", "date_of_birth", "firstname",
				"lastname" };

		StatefulBeanToCsv<Client> beanToCsv = null;

		try {
			// write List of Objects
			ColumnPositionMappingStrategy<Client> mappingStrategy = new ColumnPositionMappingStrategy<Client>();

			mappingStrategy.setType(Client.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);

			beanToCsv = new StatefulBeanToCsvBuilder<Client>(writer).withMappingStrategy(mappingStrategy)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			beanToCsv.write(customers);
		} catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}
}