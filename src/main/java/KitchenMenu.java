import java.util.ArrayList;

public class KitchenMenu extends  Menu{
	TextUI textUI = new TextUI();

	public KitchenMenu(){
		super();
	}

	@Override
	public void show() {
		super.isVisible = true;
		System.out.println("=== Køkken Menu ===");
		System.out.println("Velkommen til køkkenstyring!");
		navigate();
	}

	@Override
	public void hide(){
		super.isVisible = false;
		System.out.println("Køkken menu lukket");
	}

	@Override
	public  void navigate(){
		boolean running = true;

		while(running && super.isVisible){
			// Definer muligheder
			ArrayList<String> options = new ArrayList<>();
			options.add("Vis alle aktive ordrer");
			options.add("Marker ordrer som klar");


		}
	}

}
