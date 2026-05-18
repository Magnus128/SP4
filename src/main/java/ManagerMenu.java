import java.sql.SQLException;
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

		// Kalder navigate
		navigate();
	}

	@Override
	public void hide(){

	}

	@Override
	public void navigate(){
		System.out.println("""
				1. Rediger menu
				2. Rapporter
				3. Inventar
				4. Ansatte
				0. Afslut""");
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


		int input = 1;
		while (input != 0) {
			try {
				System.out.println("""
				1. Vis omsætning for en dag
				2. Vis omsætning for en måned
				3. Vis bedst sælgende menugenstand
				0. Gå tilbage
				""");
				input = TextUI.promptNumeric("Indtast et nummer for at vælge: ");
			} catch (NumberFormatException e) {
				System.out.println("Prøv igen, indtast venligst et nummer:");
			}
			switch (input) {
				case 1:
					double revenue = showDailyRevenue(dbConnector);
					System.out.println("Den samlede omsætning for din valgte dato er: " + revenue);
					break;
				case 2:
				case 3:
				default:
					System.out.println("Venligst indtast et validt nummer:");
			}
		}

	}

	private void menuCardMenu() {
	}

	private double showDailyRevenue(DBConnector dbConnector) {
		double revenue = 0;
		try {
			String date = TextUI.promptText("Skriv en dato i formatet (YYYY-MM-DD):");
			revenue = dbConnector.selectDailyRevenue(date);
		} catch (SQLException e) {
			System.out.println("Prøv igen. Indtast en valid dato i formatet (YYYY-MM-DD):");
			return showDailyRevenue(dbConnector);
		}
		return revenue;
	}

}
