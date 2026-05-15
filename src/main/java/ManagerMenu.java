import java.util.ArrayList;
import util.*;

public class ManagerMenu extends Menu {


	public ManagerMenu() {
		super();
	}

	@Override
	public void show() {
		super.isVisible = true;
		// Viser hovedskærmen
		System.out.println("""
				1. Rediger menu
				2. Rapporter
				3. Inventar
				4. Ansatte
				0. Afslut""");
		// Kalder navigate
		navigate();
	}

	@Override
	public void hide(){

	}

	@Override
	public void navigate(){
		int input = TextUI.promptNumeric("Indtast et nummer for at vælge: ");
		while (input != 0) {
			switch (input) {
				case 1:
					menuCardMenu();
					break;
				case 2:
					reportsMenu();
					break;
				case 3:
					inventoryMenu();
					break;
				case 4:
					staffMenu();
					break;
				default:
					System.out.println("Prøv igen:");
			}
		}
	}

	private void staffMenu() {
	}

	private void inventoryMenu() {
	}

	private void reportsMenu() {
		DBConnector dbConnector = new DBConnector();
		dbConnector.connect("jdbc:sqlite:restaurantData.sqlite");
		System.out.println("""
				1. Vis omsætning for en dag
				2. Vis omsætning for en måned
				3. Vis bedst sælgende menugenstand
				0. Gå tilbage
				""");

		int input = TextUI.promptNumeric("Indtast et nummer for at vælge: ");
		while (input != 0) {
			switch (input) {
				case 1:
					//String output = dbConnector.
				case 2:
				case 3:
				default:
			}
		}

	}

	private void menuCardMenu() {
	}

}
