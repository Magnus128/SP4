import java.util.ArrayList;

public class Main {
	private static DBConnector dbConnector = new DBConnector();

	static void main(String[] args) {

		dbConnector.connect("jdbc:sqlite:restaurantData.sqlite");
		Restaurant restaurant = initializeRestaurant();

		restaurant.getUserMenu().show();
	}

	private static Restaurant initializeRestaurant() {
		// Henter data fra databasen
		ArrayList<User> users = dbConnector.selectUser();
		ArrayList<Table> tables = dbConnector.selectTable();
		MenuCard foodMenu = dbConnector.selectFoodMenu();
		MenuCard dessertMenu = null;
		MenuCard drinksMenu = null;
		Menu userMenu = new WaiterMenu(tables);

		// Initialiserer et Restaurant-objekt med de data
		return new Restaurant(users, tables, foodMenu, dessertMenu, drinksMenu, userMenu);
	}
}
