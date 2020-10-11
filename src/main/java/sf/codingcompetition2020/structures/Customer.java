package sf.codingcompetition2020.structures;

import java.util.ArrayList;
import java.util.List;
import java.lang.Comparable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Customer extends Comparable {
	private int customerId;
	private String firstName;
	private String lastName;
	private int age;
	private String area;
	private int agentId;
	private short agentRating;
	private String primaryLanguage;
	private List<Dependent> dependents;
	private boolean homePolicy;
	private boolean autoPolicy;
	private boolean rentersPolicy;
	private String totalMonthlyPremium;
	private short yearsOfService;
	private Integer vehiclesInsured;


	public int getCustomerId() {
		return customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getArea() {
		return area;
	}

	public int getAgentId() {
		return agentId;
	}

	public short getAgentRating() {
		return agentRating;
	}

	public String getPrimaryLanguage() {
		return primaryLanguage;
	}

	public List<Dependent> getDependents() {
		return dependents;
	}

	public boolean isHomePolicy() {
		return homePolicy;
	}

	public boolean isAutoPolicy() {
		return autoPolicy;
	}

	public boolean isRentersPolicy() {
		return rentersPolicy;
	}

	public String getTotalMonthlyPremium() {
		return totalMonthlyPremium;
	}

	public short getYearsOfService() {
		return yearsOfService;
	}

	public Integer getVehiclesInsured() {
		return vehiclesInsured;
	}

	@Override
	public int compareTo(Customer customer) {
		if (customer.getTotalMonthlyPremium() < getTotalMonthlyPremium()) {
			return 1;
		} else if (customer.getTotalMonthlyPremium() > getTotalMonthlyPremium()) {
			return -1;
		} else {
			return 0;
		}
	}

}
