

import java.util.ArrayList;

public class KitchenMenu extends  Menu{
	TextUI textUI = new TextUI();

	public KitchenMenu(){
		super();
	}

	@Override
	public void show() {
		super.isVisible = true;
		System.out.println("=== Køkken Menu ===");
		System.out.println("Velkommen til køkkenstyring!");
		navigate();
	}

	@Override
	public void hide(){
		super.isVisible = false;
		System.out.println("Køkken menu lukket");
	}

	@Override
	public void navigate(){
		boolean running = true;

		while (running && super.isVisible){
			// Viser menu
			System.out.println("\n=== Køkken Menu ===");
			System.out.println("1. Vis ordrer");
			System.out.println("2. Marker ordrer som klar");
			System.out.println("3. Afslut");

			// Få valg fra bruger
			int choice = textUI.promptNumeric("Vælg (1-3):");

			if(choice == 1){
				// Viser ordrer
				System.out.println("\n=== Aktive Ordrer ===");
				for(Order order : activeOrders){
					System.out.println(order.toString());
				}
			} else if (choice == 2){
				// Marker ordre som klar
				int orderId = textUI.promptNumeric("Indtast ordre-ID");
				for(Order order : activeOrders){
					if(order.getMenuID() == orderId){
						order.setOrderstatus(OrderStatus.READY);
						System.out.println("Ordre er nu klar!");
						break;
					}
				}
			} else if(choice == 3){
				running = false;
				hide();
			}
			else {
				System.out.println("Ugyldigt valg");
			}


		}
	}

}
