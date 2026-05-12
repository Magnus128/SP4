import java.util.ArrayList;

public class Main {
	static void main(String[] args) {
		Restaurant restaurant = initializeRestaurant();

		restaurant.getUserMenu().show();
	}

	private static Restaurant initializeRestaurant() {
		// Henter data fra databasen
		ArrayList<User> users = new ArrayList<>();
		ArrayList<Table> tables = new ArrayList<>();
		MenuCard foodMenu = null;
		MenuCard dessertMenu = null;
		MenuCard drinksMenu = null;
		Menu userMenu = new WaiterMenu(1, tables);

		// Initialiserer et Restaurant-objekt med de data
		return new Restaurant(users, tables, foodMenu, dessertMenu, drinksMenu, userMenu);
	}
}
