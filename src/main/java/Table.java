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
	private double totalAmount;
	private long OrderNo;


	public Table(int id, TableStatus status, int seats) {
		this.id = id;
		this.status = status;
		this.seats = seats;
		orders = new ArrayList<>();
		totalAmount = 0;
		OrderNo = 0;
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public void removeOrder(Order order) {
		orders.remove(order);
	}

	public void printTableOrders() {
		int i = 0;
		for (Order order : orders) {

			String itemName = "";
			for (Item item : Restaurant.menuCard.getMenuItems()) {
				if (item.getId() == order.getMenuID()) {
					itemName = item.getName();
				}
			}

			System.out.println(
					String.format(
							"%-5s %-15s %-20s %-10.2f %-15s",
							i + ".",
							order.getCategory(),
							itemName,
							order.getPrice(),
							order.getOrderstatus()
					)
			);
			i++;

		}
	}

	public Invoice generateInvoice() {
		return null;
	}

	public void printReservation() {

	}

	public int getId() {
		return id;
	}

	public int getSeats() {
		return seats;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Reservation getReservation() {
		return reservation;
	}

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

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(long orderNo) {
		OrderNo = orderNo;
	}

	@Override
	public String toString() {
		return id +
				".table - " + status +
				" - " + seats + " seats" + (totalAmount == 0 ? "" : (" - " + getTotalAmount() + " kr"));
	}

}
