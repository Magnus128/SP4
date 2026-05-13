import java.util.ArrayList;

public class Main {
	static void main(String[] args) {

		Restaurant restaurant = start();

		restaurant.getUserMenu().show();
	}

	private static Restaurant start() {

		Restaurant restaurant = new Restaurant();
		restaurant.initialize();
		return restaurant;
	}
}
