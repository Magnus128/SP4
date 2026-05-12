import java.time.LocalDateTime;
import java.util.ArrayList;

public class Invoice {
	private int id;
	private LocalDateTime time;
	private ArrayList<Order> orders;
	private double totalAmount;

	public Invoice(int id, ArrayList<Order> orders, double totalAmount) {
		this.id = id;
		this.orders = orders;
		this.totalAmount = totalAmount;
		time = LocalDateTime.now();
	}

	public void printInvoice() {

	}
}
