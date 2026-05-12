import java.time.LocalDateTime;

public class Reservation {
	private LocalDateTime time;
	private int personCount;
	private int customerName;

	public Reservation(LocalDateTime time, int personCount, int customerName) {
		this.time = time;
		this.personCount = personCount;
		this.customerName = customerName;
	}
}
