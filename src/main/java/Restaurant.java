import java.util.ArrayList;

public class Restaurant {
	public static ArrayList<User> users;
	public static ArrayList<Table> tables;
	public static MenuCard foodMenu;
	public static MenuCard dessertMenu;
	public static MenuCard drinksMenu;
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
		foodMenu = dbConnector.selectFoodMenu();
		dessertMenu = null;
		drinksMenu = null;
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




	@Override
	public String toString() {
		return "Restaurant{" +
				"users=" + users +
				", tables=" + tables +
				", foodMenu=" + foodMenu +
				", dessertMenu=" + dessertMenu +
				", drinksMenu=" + drinksMenu +
				", userMenu=" + userMenu +
				", dbConnector=" + dbConnector +
				'}';
	}
}
