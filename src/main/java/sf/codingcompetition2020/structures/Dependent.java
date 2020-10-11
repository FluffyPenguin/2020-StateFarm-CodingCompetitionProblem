package sf.codingcompetition2020.structures;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;

public class Dependent {
	private String firstName;
	private String lastName;

	public Dependent () {

	}
	public Dependent(String str) {
		this.firstName = str;
		this.lastName = str;
		System.out.println(str);
	}
//		CsvMapper mapper = new CsvMapper();
//		CsvSchema schema = mapper.schemaFor(Dependent.class);
//		MappingIterator<Dependent> it = mapper.readerFor(Dependent.class)
//				.with(schema)
//				.readValues(csv);
//		Dependent d = it.next();
//		this.firstName = d.firstName;
//		this.lastName = d.lastName;
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}
