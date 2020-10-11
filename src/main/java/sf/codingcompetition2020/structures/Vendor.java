package sf.codingcompetition2020.structures;

public class Vendor {
	private int vendorId;
	private String area;
	private int vendorRating;
	private boolean inScope;

	public int getVendorId() {
		return vendorId;
	}

	public String getArea() {
		return area;
	}

	public int getVendorRating() {
		return vendorRating;
	}

	public boolean isInScope() {
		return inScope;
	}

	@Override
	public String toString() {
		return "Vendor{" +
				"vendorId=" + vendorId +
				", area='" + area + '\'' +
				", vendorRating=" + vendorRating +
				", inScope=" + inScope +
				'}';
	}
}
