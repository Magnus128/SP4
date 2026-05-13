import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {

	private long orderID;
	private int tableID;
	private String category;
	private int menuID;
	private double price;
	private OrderStatus orderstatus;
	private LocalDate orderDate;
	private LocalDateTime orderTime;

	public Order(long orderID,int tableID,String category,int menuID,double price,OrderStatus orderstatus) {
		this.orderID = orderID;
		this.tableID = tableID;
		this.menuID = menuID;
		this.price = price;
		this.orderstatus = orderstatus;
		this.category = category;
		orderDate = null;
		orderTime = null;
	}


	public long getOrderID(){return orderID;}

	public int getTableID(){return tableID;}

	public int getMenuID(){return menuID;}

	public double getPrice(){return price;}

	public String getCategory() {return category;  }

	public OrderStatus getOrderstatus() {
		return orderstatus;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getMenuName() {
		for (Item item : Restaurant.menuCard.getMenuItems()) {
			if (item.getId() == menuID) {
				return item.getName();
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return String.format(
				"%-15s %-20s %-10.2f %-15s %-10s",
				category,
				getMenuName(),
				price,
				orderDate,
				orderTime.getHour() + ":" + orderTime.getMinute()
		);
	}

}
