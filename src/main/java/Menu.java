import java.util.ArrayList;

public abstract class Menu {

	protected boolean isVisible;
	protected ArrayList<Order> activeOrders;

	public Menu(){
		isVisible = false;
		activeOrders = new ArrayList<>();
	}

	public abstract void show();

	public abstract void hide();

	public abstract void navigate();

	public void setActiveOrders(ArrayList<Order> activeOrders) {
		this.activeOrders = activeOrders;
	}
}
