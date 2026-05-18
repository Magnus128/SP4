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

		ArrayList<User> users = Restaurant.dbConnector.selectUser();

		for (User u : users) {
			System.out.println(u);
		}
		System.out.println("=======================================");


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

		ArrayList<Item> aktiveItems = new ArrayList<>();
		Item item;
		int menuID = 0;
		double price = 0;
		int inputCategory = 0;

		TextUI.displayMsg("=======================================");
		int input = TextUI.promptNumeric("1. Add Nye MenuCard \n2. Remove Menucard \n3. Edit Price \n0. Gå tilbage ");

		switch (input) {
			case 1:
				TextUI.displayMsg("=======================================");
				String menucard = TextUI.promptText("Nye MenuCard Navn :");
				String category = TextUI.promptCategory("Category :");

				price = TextUI.promptDouble("Price :");

				Restaurant.dbConnector.insertMenuCard(menucard,category,price);

				menuCardMenu();
				break;
			case 2:
				inputCategory = TextUI.promptNumeric("1.Food\n2.Drink\n3.Dessert\n4.Back");
				if (inputCategory == 1) {
					aktiveItems = Restaurant.dbConnector.selectMenuCard().getByCategory("Food");
				} else if (inputCategory == 2) {
					aktiveItems = Restaurant.dbConnector.selectMenuCard().getByCategory("Drink");
				} else if (inputCategory == 3) {
					aktiveItems = Restaurant.dbConnector.selectMenuCard().getByCategory("Dessert");
				} else {
					menuCardMenu();
				}


				menuID = TextUI.promptNumeric("Menu ID");

				try {
					item = aktiveItems.get(menuID);
					Restaurant.dbConnector.deleteMenuCard(item.getId());
				} catch (IndexOutOfBoundsException e) {
					System.out.println("menuID findes ikke i listen");
				} catch (Exception e) {
					e.printStackTrace();
				}


				menuCardMenu();

				break;

			case 3:
				inputCategory = TextUI.promptNumeric("1.Food\n2.Drink\n3.Dessert\n4.Back");
				if (inputCategory == 1) {
					aktiveItems = Restaurant.dbConnector.selectMenuCard().getByCategory("Food");
				} else if (inputCategory == 2) {
					aktiveItems = Restaurant.dbConnector.selectMenuCard().getByCategory("Drink");
				} else if (inputCategory == 3) {
					aktiveItems = Restaurant.dbConnector.selectMenuCard().getByCategory("Dessert");
				}

				menuID = TextUI.promptNumeric("Menu ID");

				try {
					item = aktiveItems.get(menuID);
					price = TextUI.promptNumeric("Nye Pris :");
					Restaurant.dbConnector.updateMenuCard(item.getId(), price);

				} catch (IndexOutOfBoundsException e) {
					System.out.println("menuID findes ikke i listen");
				} catch (Exception e) {
					e.printStackTrace();
				}


				menuCardMenu();

				break;

			case 0:
				System.out.println("Går tilbage...");
				TextUI.displayMsg("=======================================");
				break;
			default:
				menuCardMenu();
				break;
		}


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
