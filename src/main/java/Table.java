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

	public void removeOrder(Order order) {
		orders.remove(order);
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

	public TableStatus getStatus() {
		return status;
	}

	public LocalDateTime getOpeningTime() {
		return openingTime;
	}

	public LocalDateTime getClosingTime() {
		return closingTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStatus(TableStatus status) {
		this.status = status;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public void setOpeningTime(LocalDateTime openingTime) {
		this.openingTime = openingTime;
	}

	public void setClosingTime(LocalDateTime closingTime) {
		this.closingTime = closingTime;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return id +
				".table - " + status +
				" - " + seats;
	}

}
