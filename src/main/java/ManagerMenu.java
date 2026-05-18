import java.sql.SQLException;
import java.util.ArrayList;

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
		switch (input) {
			case 1:
				menuCardMenu();
				navigate();
				break;
			case 2:
				reportsMenu();
				navigate();
				break;
			case 3:
				inventoryMenu();
				navigate();
				break;
			case 4:
				staffMenu();
				navigate();
				break;
			case 0:
				System.out.println("Afslutter...");
				System.exit(0);
			default:
				System.out.println("Prøv igen:");
				navigate();
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
			0. Gå tilbage""");
		int input = TextUI.promptNumeric("Indtast et nummer for at vælge: ");
		switch (input) {
			case 1:
				double dailyRevenue = showDailyRevenue(dbConnector);
				if (dailyRevenue == 0) System.out.println("Ingen omsætning fundet på din valgte dato.");
				else System.out.println("Den samlede omsætning for din valgte dato er: " + dailyRevenue);
				reportsMenu();
				break;
			case 2:
				// System.out.println("Ikke tilgængelig endnu");
				double monthlyRevenue = showMonthlyRevenue(dbConnector);
				if (monthlyRevenue == 0) System.out.println("Ingen omsætning fundet i din valgte måned.");
				else System.out.println("Den samlede omsætning for din valgte måned er: " + monthlyRevenue);
				reportsMenu();
				break;
			case 3:
				System.out.println("Ikke tilgængelig endnu");
				reportsMenu();
				break;
			case 0:
				System.out.println("Går tilbage...");
				break;
			default:
				System.out.println("Venligst indtast et validt nummer:");
			}
	}

	private void menuCardMenu() {
	}

	private double showDailyRevenue(DBConnector dbConnector) {
		double revenue = 0;
		try {
			String date = TextUI.promptDate("Skriv en dato i formatet (YYYY-MM-DD) (Indtast 0 for at annulere):");
			if (date.equalsIgnoreCase("0")) return 0;
			revenue = dbConnector.selectDailyRevenue(date);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return showDailyRevenue(dbConnector);
		} catch (IllegalArgumentException e) {
			System.out.println("Input ikke genkendt. Prøv igen.");
			return showDailyRevenue(dbConnector);
		}
		return revenue;
	}

	private double showMonthlyRevenue(DBConnector dbConnector) {
		double revenue = 0;
		try {
			String date = TextUI.promptMonth("Skriv en måned i formatet (YYYY-MM) (Indtast 0 for at annulere):");
			if (date.equalsIgnoreCase("0")) return 0;
			revenue = dbConnector.selectMonthlyRevenue(date);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return showMonthlyRevenue(dbConnector);
		} catch (IllegalArgumentException e) {
			System.out.println("Input ikke genkendt. Prøv igen.");
			return showMonthlyRevenue(dbConnector);
		}
		return revenue;
	}


}
