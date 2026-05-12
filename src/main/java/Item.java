import java.util.ArrayList;

public class Item {

	private String name;
	private String description;
	private double price;
	private ArrayList<Ingredient> ingredients;
	private int calories;


	public Item(String name,String description,double price,int calories) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.calories = calories;
	}




}
