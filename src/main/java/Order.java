public class Order {

	private int orderID;
	private int tableID;
	private int menuID;
	private double price;
	private OrderStatus orderstatus;

	public Order(int orderID,int tableID,int menuID,double price,OrderStatus orderstatus) {
		this.orderID = orderID;
		this.tableID = tableID;
		this.menuID = menuID;
		this.price = price;
		this.orderstatus = orderstatus;

	}


	public int getOrderID(){return orderID;}

	public int getTableID(){return tableID;}

	public int getMenuID(){return menuID;}

	public double getPrice(){return price;}

	public OrderStatus getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(OrderStatus orderstatus){this.orderstatus = orderstatus;}

	@Override
	public String toString() {
		return "Order{" +
				"orderID=" + orderID +
				", tableID=" + tableID +
				", menuID=" + menuID +
				", price=" + price +
				", orderstatus=" + orderstatus +
				'}';
	}
}
