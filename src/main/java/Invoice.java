import java.time.LocalDateTime;
import java.util.ArrayList;

public class Invoice {
	private static int nextId = 1;
	private int id;
	private LocalDateTime time;
	private ArrayList<Order> orders;
	private double totalAmount;

	public Invoice(ArrayList<Order> orders, double totalAmount) {
		this.id = nextId++;  // Auto-generer id
		this.orders = orders;
		this.totalAmount = totalAmount;
		time = LocalDateTime.now();
	}

	public void printInvoice() {

	}

	@Override
	public String toString() {
		return "Invoice{" +
				"id=" + id +
				", time=" + time +
				", orders=" + orders +
				", totalAmount=" + totalAmount +
				'}';
	}
}
