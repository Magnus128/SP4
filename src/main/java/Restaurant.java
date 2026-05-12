import java.util.ArrayList;

public class Restaurant {
	private ArrayList<User> users;
	private ArrayList<Table> tables;
	private MenuCard foodMenu;
	private MenuCard dessertMenu;
	private MenuCard drinksMenu;
	private Menu userMenu;

	public Restaurant(ArrayList<User> users, ArrayList<Table> tables, MenuCard foodMenu, MenuCard dessertMenu, MenuCard drinksMenu, Menu userMenu) {
		this.users = users;
		this.tables = tables;
		this.foodMenu = foodMenu;
		this.dessertMenu = dessertMenu;
		this.drinksMenu = drinksMenu;
		this.userMenu = userMenu;
	}

	public Menu getUserMenu() {
		return userMenu;
	}
}
