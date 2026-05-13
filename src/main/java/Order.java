public class Order {

	private int orderID;
	private int tableID;
	private String category;
	private int menuID;
	private double price;
	private OrderStatus orderstatus;

	public Order(int orderID,int tableID,String category,int menuID,double price,OrderStatus orderstatus) {
		this.orderID = orderID;
		this.tableID = tableID;
		this.menuID = menuID;
		this.price = price;
		this.orderstatus = orderstatus;
		this.category = category;
	}


	public int getOrderID(){return orderID;}

	public int getTableID(){return tableID;}

	public int getMenuID(){return menuID;}

	public double getPrice(){return price;}

	public String getCategory() {return category;  }

	public OrderStatus getOrderstatus() {
		return orderstatus;
	}
}
