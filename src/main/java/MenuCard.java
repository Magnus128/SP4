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

	public void printMenuItem() {
		int i = 0;
		for (Item item : menuItems) {
			System.out.println(i + "." + item);
			i++;
		}
	}

}
