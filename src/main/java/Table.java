import java.time.LocalDateTime;
import java.util.ArrayList;

public class Table {
	private Reservation reservation;
	private int id;
	private TableStatus status;
	private int seats;
	private LocalDateTime openingTime;
	private LocalDateTime closingTime;
	private ArrayList<Order> orders;

	public Table(int id, TableStatus status, int seats) {
		this.id = id;
		this.status = status;
		this.seats = seats;
		orders = new ArrayList<>();
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public void removeOrder() {

	}

	public void printOrder() {

	}

	public Invoice generateInvoice() {
		return null;
	}

	public void printReservation() {

	}

	public int getId(){return id;}

	public int getSeats(){return seats;}

	public ArrayList<Order> getOrders(){return orders;}

	public void setReservation(Reservation reservation){this.reservation = reservation;}

	public Reservation getReservation(){return reservation;}

	@Override
	public String toString() {
		return "Table{" +
				"id=" + id +
				", status=" + status +
				", seats=" + seats +
				'}';
	}
}
