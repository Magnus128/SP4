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

	public void getAvailableTable() {
//		for(Table table : tables){
//			if(table.getId() == table.getId()){
//				table.addOrder(order);
//			}
//		}
	}

	public void addOrderToTable() {

	}

	public void removeOrderFromTable() {
		for(Table table : tables){

		}
	}

	public void showReservation() {
	}

	public void getInvoice() {
	}


}
