import java.util.ArrayList;

public class Main {
	private static DBConnector dbConnector = new DBConnector();

	static void main(String[] args) {

		dbConnector.connect("jdbc:sqlite:restaurantData.sqlite");
		Restaurant restaurant = start();

		restaurant.getUserMenu().show();
	}

	private static Restaurant start() {

		Restaurant restaurant = new Restaurant();
		restaurant.initialize();
		return restaurant;
	}
}
