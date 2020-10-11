package sf.codingcompetition2020.structures;

import java.util.ArrayList;
import java.util.List;
import java.lang.Comparable;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Customer implements Comparable<Customer> {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Customer)) return false;
		Customer customer = (Customer) o;
		return customerId == customer.customerId &&
				age == customer.age &&
				agentId == customer.agentId &&
				agentRating == customer.agentRating &&
				homePolicy == customer.homePolicy &&
				autoPolicy == customer.autoPolicy &&
				rentersPolicy == customer.rentersPolicy &&
				yearsOfService == customer.yearsOfService &&
				Objects.equals(firstName, customer.firstName) &&
				Objects.equals(lastName, customer.lastName) &&
				Objects.equals(area, customer.area) &&
				Objects.equals(primaryLanguage, customer.primaryLanguage) &&
				Objects.equals(dependents, customer.dependents) &&
				Objects.equals(totalMonthlyPremium, customer.totalMonthlyPremium) &&
				Objects.equals(vehiclesInsured, customer.vehiclesInsured);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId);
	}

	private String totalMonthlyPremium;
	private short yearsOfService;
	private Integer vehiclesInsured;

	@Override
	public String toString() {
		return "Customer{" +
				"customerId=" + customerId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", age=" + age +
				", area='" + area + '\'' +
				", agentId=" + agentId +
				", agentRating=" + agentRating +
				", primaryLanguage='" + primaryLanguage + '\'' +
				", dependents=" + dependents +
				", homePolicy=" + homePolicy +
				", autoPolicy=" + autoPolicy +
				", rentersPolicy=" + rentersPolicy +
				", totalMonthlyPremium='" + totalMonthlyPremium + '\'' +
				", yearsOfService=" + yearsOfService +
				", vehiclesInsured=" + vehiclesInsured +
				'}';
	}

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
		return totalMonthlyPremium.compareTo(customer.getTotalMonthlyPremium());
	}

}
