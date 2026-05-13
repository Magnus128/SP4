import util.TextUI;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class WaiterMenu extends Menu{

	ArrayList<Table> tables;

	public WaiterMenu(ArrayList<Table> tables) {
		super();
		this.tables = tables;
	}

	@Override
	public void show() {
		super.isVisible = true;

		// Viser all tables
		for(Table table : tables){
			System.out.println(table);
		}
		int input = TextUI.promptNumeric("Vælg table :");

		if (input == 0 || input > tables.size()) {
			show();
		} else {
			Table table = tables.get(input - 1);
			showDetails(table);
		}


	}

	private void showDetails(Table table) {

		//generereOrderNo();

		int input = TextUI.promptNumeric("1.Add Items \n2.Remove Items \n3.View Order \n4.Add reservation \n5.Get Invoice \n6.Back");

		switch (input) {
			case 1:
				addOrderToTable(table);
				break;
			case 2:
				removeOrderFromTable(table);
				break;
			case 3:
				viewOrder(table);
				showDetails(table);
				break;
			case 4:
				doReservation(table);
				break;
			case 5:
				//getInvoice(table);
				break;
			case 6:
				show();
				break;
			default:
				showDetails(table);
				break;
		}
	}




	public void addOrderToTable(Table table) {

		int input = TextUI.promptNumeric("1.Food\n2.Drink\n3.Dessert\n4.Back");
		MenuCard aktiveMenuCard = new MenuCard();

		switch (input) {
			case 1:
				Restaurant.foodMenu.printMenuItem();
				aktiveMenuCard = Restaurant.foodMenu;
				break;
			case 2:
				Restaurant.drinksMenu.printMenuItem();
				aktiveMenuCard = Restaurant.drinksMenu;
				break;
			case 3:
				Restaurant.dessertMenu.printMenuItem();
				aktiveMenuCard = Restaurant.dessertMenu;
				break;
			case 4:
				Restaurant.userMenu.show();
				break;
			default:
				addOrderToTable(table);
				break;
		}

		int menuID = TextUI.promptNumeric("Menu ID");
		Item item = aktiveMenuCard.getMenuItems().get(menuID);

		table.addOrder(new Order(1, table.getId(), item.getCategory(), item.getId(), item.getPrice(), OrderStatus.ACTIVE));
		table.setStatus(TableStatus.BUSY);
		table.setOpeningTime(LocalDateTime.now());

		System.out.println("Varer er tilføjet");
		System.out.println("=============================");

		showDetails(table);


	/*	for(Table table : tables){
			if(table.getId() == tableId){
				table.addOrder(order);
				break;
			}
		}*/
	}

	public void removeOrderFromTable(Table table) {


		viewOrder(table);

		int input = TextUI.promptNumeric("Order ID :");
		Order order = table.getOrders().get(input);
		table.getOrders().remove(order);

		System.out.println("Varer er fjernet");
		System.out.println("=============================");

		showDetails(table);


		/*for(Table table : tables){
			if(table.getId() == tableId){
				table.removeOrder();
				break;
			}
		}*/
	}

	private void doReservation(Table table) {
		if (table.getStatus().equals(TableStatus.AVAILABLE)) {
			table.setStatus(TableStatus.RESERVED);
			TextUI.displayMsg(table.getId() + ".table reserved");
			TextUI.displayMsg("=============================");
		} else {
			System.out.println(table.getId() + ".table is not suitable");
		}

		Restaurant.userMenu.show();
	}

	public void viewOrder(Table table) {

		int i = 0;
		for (Order order : table.getOrders()) {

			String itemName = "";

			if (order.getCategory().equals("Drink")) {

				for (Item item : Restaurant.drinksMenu.getMenuItems()) {
					if (item.getId() == order.getMenuID()) {
						itemName = item.getName();
					}
				}

			} else if (order.getCategory().equals("Food")) {

				for (Item item : Restaurant.foodMenu.getMenuItems()) {
					if (item.getId() == order.getMenuID()) {
						itemName = item.getName();
					}
				}

			} else if (order.getCategory().equals("Dessert")) {

				for (Item item : Restaurant.dessertMenu.getMenuItems()) {
					if (item.getId() == order.getMenuID()) {
						itemName = item.getName();
					}
				}
			}

			System.out.println(i + "."+ order.getCategory() + " - " + itemName + " - " + order.getPrice() + " - " + order.getOrderstatus());
			i++;

		}
		System.out.println("=============================");

	}


	public Table getAvailableTable(int seatsNeeded) {
		for(Table table : tables){
			if(table.getSeats() >= seatsNeeded){ // Tjekker om bordet har nok plads
				return table;
			}
		}
		return null;
	}

	public void getInvoice(int tableId) {
		for(Table table : tables){
			if(table.getId() == tableId){
				Invoice invoice = table.generateInvoice();
				if(invoice != null){
					invoice.printInvoice();
				} else {
					System.out.println("Ingen faktura kunne genereres for bord " + tableId);
				}
				return;
			}
		}
		System.out.println("Bord ikke fundet");
	}

	public void showReservation(int tableId) {
		// Viser reservation for specifik bord
		for(Table table : tables){
			if(table.getId() == tableId){
				table.printReservation();
				return;
			}
		}
		System.out.println("Bord ikke fundet");
	}

	public void showAllReservations(){ // Viser alle reservationer
		System.out.println("=== Alle Reservationer ===");
		for(Table table : tables){
			if(table.getReservation() != null){
				table.printReservation();
			}
		}
	}




	@Override
	public void navigate() {
		//
	}


	@Override
	public void hide() {

	}


}




