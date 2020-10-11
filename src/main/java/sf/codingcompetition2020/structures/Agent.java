package sf.codingcompetition2020.structures;

import java.util.Objects;

public class Agent {

	private int agentId;
	private String area;
	private String language;
	private String firstName;
	private String lastName;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Agent)) return false;
		Agent agent = (Agent) o;
		return agentId == agent.agentId &&
				Objects.equals(area, agent.area) &&
				Objects.equals(language, agent.language) &&
				Objects.equals(firstName, agent.firstName) &&
				Objects.equals(lastName, agent.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(agentId);
	}

	public int getAgentId() {
		return agentId;
	}

	public String getArea() {
		return area;
	}

	public String getLanguage() {
		return language;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "Agent{" +
				"agentId=" + agentId +
				", area='" + area + '\'' +
				", language='" + language + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				'}';
	}
}
