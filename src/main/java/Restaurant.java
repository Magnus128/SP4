import util.TextUI;

import java.util.ArrayList;

public class Restaurant {
	public static ArrayList<User> users;
	public static ArrayList<Table> tables;
	public static MenuCard menuCard;
	//public static MenuCard dessertMenu;
	//public static MenuCard drinksMenu;
	public static Menu userMenu;
	public static DBConnector dbConnector;

	public Restaurant() {
		dbConnector = new DBConnector();
	}



	public void initialize() {
		// Henter data fra databasen
		dbConnector.connect("jdbc:sqlite:restaurantData.sqlite");
		users = dbConnector.selectUser();
		tables = dbConnector.selectTable();
		menuCard = dbConnector.selectMenuCard();
		// dessertMenu = dbConnector.selectdessertMenu();
		// drinksMenu = dbConnector.selectdrinksMenu();
		userMenu = new WaiterMenu(tables);
	}

	public Menu getUserMenu() {
		return userMenu;
	}

	public Menu ChooseMenu() {

		int input = TextUI.promptNumeric("1.Waiter\n2.Manager\n3.Kitchen Staff");

		if (input == 1) {
			return new WaiterMenu(tables);
		} else if (input == 2) {
			return new ManagerMenu();
		} else if (input == 3) {
			return new KitchenMenu();
		} else {
			return ChooseMenu();
		}

	}



}
