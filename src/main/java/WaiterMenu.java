import util.TextUI;

import java.time.Instant;
import java.time.LocalDate;
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

		if (input == 0 || input > tables.size()) {  // Kontrol
			show();
		} else {
			Table table = tables.get(input - 1);
			showDetails(table);  					// tabel detaljer
		}


	}

	private void showDetails(Table table) {

		genererOrderNo();							// 	Dette blev gjort, så det samme ordre-ID bruges på både fakturaen og ordren.

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
				getInvoice(table);
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
		ArrayList<Item> aktiveItems = new ArrayList<>();

		switch (input) {
			case 1:
				aktiveItems = Restaurant.menuCard.getByCategory("Food");
				break;
			case 2:
				aktiveItems = Restaurant.menuCard.getByCategory("Drink");
				break;
			case 3:
				aktiveItems = Restaurant.menuCard.getByCategory("Dessert");
				break;
			case 4:
				Restaurant.userMenu.show();
				break;
			default:
				addOrderToTable(table);
				break;
		}

		// items findes efter kategori
		int menuID = TextUI.promptNumeric("Menu ID");
		Item item = aktiveItems.get(menuID);

		// Order transaktioner
		Order order = new Order(table.getOrderNo(), table.getId(), item.getCategory(), item.getId(), item.getPrice(), OrderStatus.ACTIVE);
		order.setOrderDate(LocalDate.now());
		order.setOrderTime(LocalDateTime.now());

		// Ordredatabasen er blevet udskrevet.
		Restaurant.dbConnector.insertOrdre(order);

		// Tabel transaktioner
		table.addOrder(order);												// Ordren tilføjes til tabellen.
		table.setStatus(TableStatus.BUSY);									// Tabelstatus er ændret
		table.setOpeningTime(LocalDateTime.now());
		table.setTotalAmount(table.getTotalAmount() + item.getPrice());		// Bordets samlede pris er beregnet

		System.out.println("Produkt tilføjet.");
		System.out.println("=============================");

		showDetails(table);

	}

	public void removeOrderFromTable(Table table) {

		if (table.getOrders().isEmpty()) { 									 // Hvis der ikke er orden ved bordet
			TextUI.displayMsg("No active orders");
		} else {

			viewOrder(table);												 // Vis bordbestillinger
			int input = TextUI.promptNumeric("Vælg det ordre-ID, der skal fjernes :");
			Order order = table.getOrders().get(input);						 // ordrevalg

			table.removeOrder(order);										 // Fjern ordre fra bordet
			table.setTotalAmount(table.getTotalAmount() - order.getPrice()); // Reducere den samlede bordpris

			System.out.println("Produktet er blevet fjernet");
			System.out.println("=============================");
		}


		showDetails(table);

	}

	private void doReservation(Table table) {
		if (table.getStatus().equals(TableStatus.AVAILABLE)) {				// reserverer ledige borde
			table.setStatus(TableStatus.RESERVED);
			TextUI.displayMsg(table.getId() + ".table reserved");
			TextUI.displayMsg("=============================");
		} else {
			System.out.println(table.getId() + ".table is not suitable");	// Det er ikke muligt at reservere aktive borde.
			TextUI.displayMsg("=============================");
		}

		Restaurant.userMenu.show();
	}

	public void viewOrder(Table table) {

		System.out.println("=========== Orders ===========");
		table.printTableOrders();
		System.out.println("==============================");

	}

	public void getInvoice(Table table) {

		if (table.getOrders().isEmpty()) { 									 // Hvis der ikke er orden ved bordet
			TextUI.displayMsg("No active orders");
		} else {

			// Invoice oprettes og ordre-ID'et tilføjes til fakturaen.
			Invoice invoice = new Invoice(table.getOrders(), table.getTotalAmount());
			invoice.setOrderID(table.getOrderNo());

			// Table transaction (Tabellen nulstilles, når fakturaen modtages.)
			table.setStatus(TableStatus.AVAILABLE);
			table.setClosingTime(LocalDateTime.now());
			table.setOrderNo(0);
			table.setTotalAmount(0);

			// Faktura vises
			invoice.printInvoice();

			// Fakturaer og ordrer gemmes i databasen.
			Restaurant.dbConnector.insertInvoice(invoice);

		}
		show();
	}

	private void genererOrderNo() {

		// en unik værdi skabes
		for (Table table : tables) {
			long unix = Instant.now().getEpochSecond();   // for unik
			if (table.getOrderNo() == 0) {
				table.setOrderNo(unix);
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




