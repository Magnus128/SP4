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
		if(!orders.isEmpty()){
			orders.remove(orders.size()-1);
			System.out.println("Sidste ordre fjernet fra bord " + id);
		} else {
			System.out.println("Ingen ordrer at fjernen på bord " + id);
		}
	}

	public void printOrder() {
		System.out.println("=== Odrer for bord " + id + " ===");
		if(orders.isEmpty()){
			System.out.println("Ingen ordrer");
		} else {
			for (int i = 0; i < orders.size(); i++){
				System.out.println((i+1) + ". " + orders.get(i).toString());
			}
		}
	}

	public Invoice generateInvoice() {
		if(orders.isEmpty()){
			return null;
		}
		double total = 0.0;
		for(Order order : orders){
			total += order.getPrice();
		}
		Invoice invoice = new Invoice(orders, total);
		orders.clear();
		status = TableStatus.AVAILABLE;
		return invoice;
	}

	public void printReservation() {
		if(reservation != null){
			System.out.println("Reservation for bord " + id + ":");
			System.out.println("Kunde: " + reservation.getCustomerName());
			System.out.println("Tidespunkt: " + reservation.getTime());
			System.out.println("Antal personer: " + reservation.getPersonCount());
		} else {
			System.out.println("Ingen reservation for bord " + id);
		}
	}

	public int getId(){return id;}

	public int getSeats(){return seats;}

	public ArrayList<Order> getOrders(){return orders;}

	public void setReservation(Reservation reservation){this.reservation = reservation;}

	public Reservation getReservation(){return reservation;}


}
