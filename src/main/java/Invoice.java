import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Invoice {
	private static int nextId = 1;
	private int id;
	private LocalDate date;
	private LocalDateTime time;
	private ArrayList<Order> orders;
	private double totalAmount;
	private long orderID;

	public Invoice(ArrayList<Order> orders, double totalAmount) {
		this.orders = orders;
		this.totalAmount = totalAmount;
		date = LocalDate.now();
		time = LocalDateTime.now();
		orderID = 0;
		this.id = nextId++;  // Auto-generer id
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public LocalDate getDate() {
		return date;
	}

	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public void printInvoice() {
		System.out.println("=============================");
		System.out.println("Table : " + orders.getFirst().getTableID() + " - InvoiceNo : " + orderID);
		System.out.println("=============================");
		int i = 0;
		for (Order order : orders) {
			System.out.println(i + "." + order);
			i++;
		}
		System.out.println("Total Amount : " + totalAmount);
		System.out.println("=============================");
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
