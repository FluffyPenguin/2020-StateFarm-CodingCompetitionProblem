package sf.codingcompetition2020.structures;

public class Claim {
	private int claimId;
	private int customerId;
	private boolean closed;
	private int monthsOpen;

	public int getClaimId() {
		return claimId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public boolean isClosed() {
		return closed;
	}

	public int getMonthsOpen() {
		return monthsOpen;
	}

}
