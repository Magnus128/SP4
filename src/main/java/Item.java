import java.util.ArrayList;

public class Item {

	private int id;
	private String name;
	private String category;
	private double price;
	private ArrayList<Ingredient> ingredients;
	// private int calories;


	public Item(int id, String name, String category, double price) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public double getPrice() {
		return price;
	}

	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	@Override
	public String toString() {
		return String.format(
				"%-20s %-15s %-10.2f",
				name,
				category,
				price
		);
	}
}