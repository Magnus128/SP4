import java.time.LocalDateTime;

public class Reservation {
	private LocalDateTime time;
	private int personCount;
	private String customerName;

	public Reservation(LocalDateTime time, int personCount, String customerName) {
		this.time = time;
		this.personCount = personCount;
		this.customerName = customerName;
	}

	public LocalDateTime getTime(){return time;}

	public int getPersonCount(){return personCount;}

	public String getCustomerName(){return customerName;}

	public void setTime(LocalDateTime time){this.time = time;}

	public void setPersonCount(int personCount){this.personCount = personCount;}

	public void setCustomerName(String customerName){this.customerName = customerName;}


	public void printReservation(){
		System.out.println("Reservation for: " + getCustomerName());
		System.out.println("Tidespunkt: " + time);
		System.out.println("Antal personer: " + personCount);
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"time=" + time +
				", personCount=" + personCount +
				", customerName='" + customerName + '\'' +
				'}';
	}
}
