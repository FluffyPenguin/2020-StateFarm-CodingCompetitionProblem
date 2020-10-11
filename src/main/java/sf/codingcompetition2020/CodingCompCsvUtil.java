package sf.codingcompetition2020;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.Collections;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import sf.codingcompetition2020.structures.Agent;
import sf.codingcompetition2020.structures.Claim;
import sf.codingcompetition2020.structures.Customer;
import sf.codingcompetition2020.structures.Dependent;
import sf.codingcompetition2020.structures.Vendor;

import static java.util.stream.Collectors.toList;

public class CodingCompCsvUtil {
  private final String agentListKey = "agentList";
  private final String claimListKey = "claimList";
  private final String customerListKey = "customerList";

	/* #1
	 * readCsvFile() -- Read in a CSV File and return a list of entries in that file.
	 * @param filePath -- Path to file being read in.
	 * @param classType -- Class of entries being read in.
	 * @return -- List of entries being returned.
	 */
	public <T> List<T> readCsvFile(String filePath, Class<T> classType)  {
		try {
			//URL url = this.getClass().getResource(filePath);
			//URI uri = new URI(url.toString());
			//File file = new File(uri.getPath());
			File file = new File(filePath);

			CsvMapper mapper = new CsvMapper();
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			//CsvSchema schema = mapper.schemaFor(classType); // schema from 'Pojo' definition
			CsvSchema schema = CsvSchema.emptySchema().withHeader();
			TypeFactory typeFactory = mapper.getTypeFactory();

			MappingIterator<T> it = mapper.readerFor(classType)
					.with(schema)
					.readValues(file);
			List<T> ans = it.readAll();
			System.out.println(ans);
			System.out.println(ans.size());
			System.out.println(ans.get(0));
			return ans;
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("Yeeto");
		}



//	  URL url = this.getClass().getResource(filePath);
//		URI uri = new URI(url.toString());
//		File file = new File(uri.getPath());
//		FileInputStream fis = new FileInputStream(file);
//		byte[] data = new byte[(int) file.length()];
//		try {
//			fis.read(data);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (fis != null) {
//				fis.close();
//			}
//		}
//		String[] contents = new String(data, "UTF-8").split("\\r?\\n");
//
//		List<T> entries = new ArrayList<>();
//		for(String entry : contents) {
//			entries.add(new T(entry.split(",")));
//		}
//		return entries;
	}


	/* #2
	 * getAgentCountInArea() -- Return the number of agents in a given area.
	 * @param filePath -- Path to file being read in.
	 * @param area -- The area from which the agents should be counted.
	 * @return -- The number of agents in a given area
	 */
	public int getAgentCountInArea(String filePath, String area) {
    return readCsvFile(filePath, Agent.class)
        .stream()
        .filter(a -> a.getArea().equals(area))
        .collect(toList()).size();
	}


	/** #3
	 * getAgentsInAreaThatSpeakLanguage() -- Return a list of agents from a given area, that speak a certain language.
	 * @param filePath -- Path to file being read in.
	 * @param area -- The area from which the agents should be counted.
	 * @param language -- The language spoken by the agent(s).
	 * @return -- The number of agents in a given area
	 */
	public List<Agent> getAgentsInAreaThatSpeakLanguage(String filePath, String area, String language) {
    return readCsvFile(filePath, Agent.class)
        .stream()
        .filter(a -> a.getArea().equals(area))
        .filter(a -> a.getLanguage().equals(language))
        .collect(toList());
	}


	/** #4
	 * countCustomersFromAreaThatUseAgent() -- Return the number of individuals from an area that use a certain agent.
	 * @param csvFilePaths -- Path to file being read in.
	 * @param customerArea -- The area from which the customers should be counted.
	 * @param agentFirstName -- First name of agent.
	 * @param agentLastName -- Last name of agent.
	 * @return -- The number of customers that use a certain agent in a given area.
	 * @throws NoSuchElementException if the specified agent is not found in the given agent csv file.
	 */
	public short countCustomersFromAreaThatUseAgent(Map<String,String> csvFilePaths, String customerArea, String agentFirstName, String agentLastName) {
    List<Agent> agents = readCsvFile(csvFilePaths.get(agentListKey), Agent.class);

    // find the agent in question
    Agent agent = null;
    for (Agent a : agents) {
      if (a.getFirstName().equals(agentFirstName) && a.getLastName().equals(agentLastName)) {
        agent = a;
        break;
      }
    }
    if (agent == null) { // this agent does not exist in the agents file
      throw new NoSuchElementException("The specified agent: " + agentFirstName + " " + agentLastName + " was not found in the given agent csv file.");
    }

		final int agentId = agent.getAgentId();
		List<Customer> customers = readCsvFile(csvFilePaths.get(customerListKey), Customer.class)
        .stream()
        .filter(customer -> customer.getArea().equals(customerArea) && customer.getAgentId() == (agentId))
        .collect(toList());

    return (short) customers.size();
	}


	/* #5
	 * getCustomersRetainedForYearsByPlcyCostAsc() -- Return a list of customers retained for a given number of years, in ascending order of their policy cost.
	 * @param filePath -- Path to file being read in.
	 * @param yearsOfService -- Number of years the person has been a customer.
	 * @return -- List of customers retained for a given number of years, in ascending order of policy cost.
	 */
	public List<Customer> getCustomersRetainedForYearsByPlcyCostAsc(String customerFilePath, short yearsOfService) {
		List<Customer> filteredCustomers = readCsvFile(customerFilePath, Customer.class)
				.stream()
				.filter(c -> c.getYearsOfService() == yearsOfService)
				.collect(toList());
		Collections.sort(filteredCustomers);
		return filteredCustomers;
	}


	/* #6
	 * getLeadsForInsurance() -- Return a list of individuals who’ve made an inquiry for a policy but have not signed up.
	 * *HINT* -- Look for customers that currently have no policies with the insurance company.
	 * @param filePath -- Path to file being read in.
	 * @return -- List of customers who’ve made an inquiry for a policy but have not signed up.
	 */
	public List<Customer> getLeadsForInsurance(String filePath) {
    List<Customer> customers = readCsvFile(filePath, Customer.class);
    List<Customer> customerList = new ArrayList<>();

    for (Customer customer: customers) {
      if(!customer.isHomePolicy() && !customer.isAutoPolicy() && !customer.isRentersPolicy()) {
        customerList.add(customer);
      }
    }
    return customerList;
	}


	/* #7
	 * getVendorsWithGivenRatingThatAreInScope() -- Return a list of vendors within an area and include options to narrow it down by:
			a.	Vendor rating
			b.	Whether that vendor is in scope of the insurance (if inScope == false, return all vendors in OR out of scope, if inScope == true, return ONLY vendors in scope)
	 * @param filePath -- Path to file being read in.
	 * @param area -- Area of the vendor.
	 * @param inScope -- Whether or not the vendor is in scope of the insurance.
	 * @param vendorRating -- The rating of the vendor.
	 * @return -- List of vendors within a given area, filtered by scope and vendor rating.
	 */
	public List<Vendor> getVendorsWithGivenRatingThatAreInScope(String filePath, String area, boolean inScope, int vendorRating) {
		return readCsvFile(filePath, Vendor.class)
        .stream()
				.filter(v -> v.getArea().equals(area))
        .filter(v -> v.getVendorRating() >= vendorRating)
        .filter(v -> (!inScope) || (v.isInScope()))
        .collect(toList());
	}


	/* #8
	 * getUndisclosedDrivers() -- Return a list of customers between the age of 40 and 50 years (inclusive), who have:
			a.	More than X cars
			b.	less than or equal to X number of dependents.
	 * @param filePath -- Path to file being read in.
	 * @param vehiclesInsured -- The number of vehicles insured.
	 * @param dependents -- The number of dependents on the insurance policy.
	 * @return -- List of customers filtered by age, number of vehicles insured and the number of dependents.
	 */
	public List<Customer> getUndisclosedDrivers(String filePath, int vehiclesInsured, int dependents) {
    return readCsvFile(filePath, Customer.class)
        .stream()
        .filter(c -> c.getVehiclesInsured() > vehiclesInsured)
        .filter(c -> c.getDependents().size() <= dependents)
        .filter(c -> (c.getAge() >= 40 && c.getAge() <= 50))
        .collect(toList());
	}


	/* #9
	 * getAgentIdGivenRank() -- Return the agent with the given rank based on average customer satisfaction rating.
	 * *HINT* -- Rating is calculated by taking all the agent rating by customers (1-5 scale) and dividing by the total number
	 * of reviews for the agent.
	 * @param filePath -- Path to file being read in.
	 * @param agentRank -- The rank of the agent being requested.
	 * @return -- Agent ID of agent with the given rank.
	 */
	public int getAgentIdGivenRank(String filePath, int agentRank) {
		List<Customer> customers = readCsvFile(filePath, Customer.class);
    Map<Integer, Integer> agentCount = new HashMap<>();
    Map<Integer, Integer> ratingSum = new HashMap<>();
    for (Customer customer : customers) {
      int agentId = customer.getAgentId();
      agentCount.put(agentId, agentCount.getOrDefault(agentId, 0) + 1);
      ratingSum.put(agentId, ratingSum.getOrDefault(agentId, 0) + customer.getAgentRating());
    }

		Map<Integer, Double> agentRatings = new HashMap<>();

    for (int agentId : ratingSum.keySet()) {
      double agentRating = ratingSum.get(agentId) * 1.0 / agentCount.get(agentId);
      agentRatings.put(agentId, agentRating);
    }

    LinkedHashMap<Integer, Double> sortedAgentRating = new LinkedHashMap<>();

    agentRatings.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> sortedAgentRating.put(x.getKey(), x.getValue()));

    Iterator<Entry<Integer, Double>> iterator = sortedAgentRating.entrySet().iterator();
    System.out.println(sortedAgentRating);
    for(int i = 1; i < agentRank; i++) {
    	iterator.next();
		}
    return iterator.next().getKey();
	}


	/* #10
	 * getCustomersWithClaims() -- Return a list of customers who’ve filed a claim within the last <numberOfMonths> (inclusive).
	 * @param filePath -- Path to file being read in.
	 * @param monthsOpen -- Number of months a policy has been open.
	 * @return -- List of customers who’ve filed a claim within the last <numberOfMonths>.
	 */
	public List<Customer> getCustomersWithClaims(Map<String,String> csvFilePaths, short monthsOpen) {
    List<Claim> claims = readCsvFile(csvFilePaths.get(claimListKey), Claim.class)
      .stream()
      .filter(c -> c.getMonthsOpen() <= monthsOpen)
      .collect(toList());

    Map<Integer, Customer> idToCustomer = new HashMap<>();

    List<Customer> customers = readCsvFile(csvFilePaths.get(customerListKey), Customer.class);
    for (Customer customer: customers) {
      idToCustomer.put(customer.getCustomerId(), customer);
    }

    Set<Customer> customerSet = new HashSet<>();

    for (Claim claim: claims) {

      customerSet.add(idToCustomer.get(claim.getCustomerId()));
    }



    return new ArrayList<>(customerSet);
	}

}
