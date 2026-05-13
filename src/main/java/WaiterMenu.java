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

		// Viser hovedskærmen

		// Kalder navigate
		// navigate();
		System.out.println(tables);

	}

	@Override
	public void hide() {

	}

	@Override
	public void navigate() {
		//
	}

	public Table getAvailableTable(int seatsNeeded) {
		for(Table table : tables){
			if(table.getSeats() >= seatsNeeded){ // Tjekker om bordet har nok plads
				return table;
			}
		}
		return null;
	}

	public void addOrderToTable(int tableId, Order order) {
		for(Table table : tables){
			if(table.getId() == tableId){
				table.addOrder(order);
				break;
			}
		}
	}

	public void removeOrderFromTable(int tableId, Order order) {
		for(Table table : tables){
			if(table.getId() == tableId){
				table.removeOrder();
				break;
			}
		}
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


}




