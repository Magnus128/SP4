import java.util.ArrayList;

public class Customer {
	private String name;
	private String phoneNumber;
	private ArrayList<String> reviews;

	public Customer(String name, String phoneNumber){
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.reviews = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Customer{" +
				"name='" + name + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", reviews=" + reviews +
				'}';
	}
}
