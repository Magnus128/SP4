import java.util.ArrayList;

public class Restaurant {
	private ArrayList<User> users;
	private ArrayList<Table> tables;
	private MenuCard foodMenu;
	private MenuCard dessertMenu;
	private MenuCard drinksMenu;
	private Menu userMenu;
	private DBConnector dbConnector;

	public Restaurant() {
		dbConnector = new DBConnector();
	}

	public Restaurant(ArrayList<User> users, ArrayList<Table> tables, MenuCard foodMenu, MenuCard dessertMenu, MenuCard drinksMenu, Menu userMenu) {
		this.users = users;
		this.tables = tables;
		this.foodMenu = foodMenu;
		this.dessertMenu = dessertMenu;
		this.drinksMenu = drinksMenu;
		this.userMenu = userMenu;
		dbConnector = new DBConnector();
	}

	public void initialize() {
		// Henter data fra databasen
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
}
