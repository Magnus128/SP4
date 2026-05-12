import java.awt.*;
import java.util.ArrayList;

public class MenuCard {

	private ArrayList<Item> menuItems;
	private ArrayList<Item> discountedItems;
	Image frontpage;

	public MenuCard() {
		menuItems = new ArrayList<>();
		discountedItems = new ArrayList<>();
	}

	public ArrayList<Item> getMenuItems() {
		return menuItems;
	}
}
