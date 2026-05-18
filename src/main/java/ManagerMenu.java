import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerMenu extends Menu {


	public ManagerMenu() {
		super();
	}

	@Override
	public void show() {
		super.isVisible = true;
		// Viser hovedskærmen

		// Kalder navigate
		navigate();
	}

	@Override
	public void hide(){

	}

	@Override
	public void navigate(){
		System.out.println("""
				1. Rediger menu
				2. Rapporter
				3. Inventar
				4. Ansatte
				0. Afslut""");

		int input = TextUI.promptNumeric("Indtast et nummer for at vælge: ");
		switch (input) {
			case 1:
				menuCardMenu();
				navigate();
				break;
			case 2:
				reportsMenu();
				navigate();
				break;
			case 3:
				inventoryMenu();
				navigate();
				break;
			case 4:
				staffMenu();
				navigate();
				break;
			case 0:
				System.out.println("Afslutter...");
				System.exit(0);
			default:
				System.out.println("Prøv igen:");
				navigate();
		}
	}

	private void staffMenu() {



		System.out.println("""
				1. Add employee
				2. Delete employee
				3. Edit Employee
				4. List Employees
				0. Afslut""");
		int input = TextUI.promptNumeric("Indtast et nummer for at vælge: ");

			switch (input) {
				case 1:
					addEmployeeDialog();
					break;
				case 2:
					removeEmployeeDialog();
					break;
				case 3:
					editEmployeeDialog();
					break;
				case 4:
					listEmployees();
					staffMenu();
					break;
				default:
					System.out.println("Prøv igen:");
			}



	}

	private void inventoryMenu() {
		System.out.println("Denne del blev ikke færdig.");
	}

	private void reportsMenu() {
		DBConnector dbConnector = new DBConnector();
		dbConnector.connect("jdbc:sqlite:restaurantData.sqlite");

		System.out.println("""
			1. Vis omsætning for en dag
			2. Vis omsætning for en måned
			3. Vis bedst sælgende menugenstand
			0. Gå tilbage""");
		int input = TextUI.promptNumeric("Indtast et nummer for at vælge: ");
		switch (input) {
			case 1:
				double dailyRevenue = showDailyRevenue(dbConnector);
				if (dailyRevenue == 0) System.out.println("Ingen omsætning fundet på din valgte dato.");
				else System.out.println("Den samlede omsætning for din valgte dato er: " + dailyRevenue);
				reportsMenu();
				break;
			case 2:
				double monthlyRevenue = showMonthlyRevenue(dbConnector);
				if (monthlyRevenue == 0) System.out.println("Ingen omsætning fundet i din valgte måned.");
				else System.out.println("Den samlede omsætning for din valgte måned er: " + monthlyRevenue);
				reportsMenu();
				break;
			case 3:
				int[] itemInfo = getBestSeller(dbConnector);
				int itemID = itemInfo[0];
				int amountSold = itemInfo[1];
				String itemName = Restaurant.menuCard.getMenuItems().get(itemID).getName();

				System.out.println("Den bedst sælgende menugenstand er: " + itemName + "\n" +
						"Antal solgt: " + amountSold);
				reportsMenu();
				break;
			case 0:
				System.out.println("Går tilbage...");
				break;
			default:
				System.out.println("Venligst indtast et validt nummer:");
			}
	}

	private void menuCardMenu() {

		ArrayList<Item> aktiveItems = new ArrayList<>();
		Item item;
		int menuID = 0;
		double price = 0;
		int inputCategory = 0;

		TextUI.displayMsg("=======================================");
		int input = TextUI.promptNumeric("1. Add Nye MenuCard \n2. Remove Menucard \n3. Edit Price \n0. Gå tilbage ");

		switch (input) {
			case 1:
				TextUI.displayMsg("=======================================");
				String menucard = TextUI.promptText("Nye MenuCard Navn :");
				String category = TextUI.promptCategory("Category :");

				price = TextUI.promptDouble("Price :");

				Restaurant.dbConnector.insertMenuCard(menucard,category,price);

				menuCardMenu();
				break;
			case 2:
				inputCategory = TextUI.promptNumeric("1.Food\n2.Drink\n3.Dessert\n4.Back");
				if (inputCategory == 1) {
					aktiveItems = Restaurant.dbConnector.selectMenuCard().getByCategory("Food");
				} else if (inputCategory == 2) {
					aktiveItems = Restaurant.dbConnector.selectMenuCard().getByCategory("Drink");
				} else if (inputCategory == 3) {
					aktiveItems = Restaurant.dbConnector.selectMenuCard().getByCategory("Dessert");
				} else {
					menuCardMenu();
				}


				menuID = TextUI.promptNumeric("Menu ID");

				try {
					item = aktiveItems.get(menuID);
					Restaurant.dbConnector.deleteMenuCard(item.getId());
				} catch (IndexOutOfBoundsException e) {
					System.out.println("menuID findes ikke i listen");
				} catch (Exception e) {
					e.printStackTrace();
				}


				menuCardMenu();

				break;

			case 3:
				inputCategory = TextUI.promptNumeric("1.Food\n2.Drink\n3.Dessert\n4.Back");
				if (inputCategory == 1) {
					aktiveItems = Restaurant.dbConnector.selectMenuCard().getByCategory("Food");
				} else if (inputCategory == 2) {
					aktiveItems = Restaurant.dbConnector.selectMenuCard().getByCategory("Drink");
				} else if (inputCategory == 3) {
					aktiveItems = Restaurant.dbConnector.selectMenuCard().getByCategory("Dessert");
				}

				menuID = TextUI.promptNumeric("Menu ID");

				try {
					item = aktiveItems.get(menuID);
					price = TextUI.promptDouble("Nye Pris :");
					Restaurant.dbConnector.updateMenuCard(item.getId(), price);

				} catch (IndexOutOfBoundsException e) {
					System.out.println("menuID findes ikke i listen");
				} catch (Exception e) {
					e.printStackTrace();
				}


				menuCardMenu();

				break;

			case 0:
				System.out.println("Går tilbage...");
				TextUI.displayMsg("=======================================");
				break;
			default:
				menuCardMenu();
				break;
		}


	}

	private double showDailyRevenue(DBConnector dbConnector) {
		double revenue = 0;
		try {
			String date = TextUI.promptDate("Skriv en dato i formatet (YYYY-MM-DD) (Indtast 0 for at annulere):");
			if (date.equalsIgnoreCase("0")) return 0;
			revenue = dbConnector.selectDailyRevenue(date);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return showDailyRevenue(dbConnector);
		} catch (IllegalArgumentException e) {
			System.out.println("Input ikke genkendt. Prøv igen.");
			return showDailyRevenue(dbConnector);
		}
		return revenue;
	}

	private double showMonthlyRevenue(DBConnector dbConnector) {
		double revenue = 0;
		try {
			String date = TextUI.promptMonth("Skriv en måned i formatet (YYYY-MM) (Indtast 0 for at annulere):");
			if (date.equalsIgnoreCase("0")) return 0;
			revenue = dbConnector.selectMonthlyRevenue(date);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return showMonthlyRevenue(dbConnector);
		} catch (IllegalArgumentException e) {
			System.out.println("Input ikke genkendt. Prøv igen.");
			return showMonthlyRevenue(dbConnector);
		}
		return revenue;
	}

	private int[] getBestSeller(DBConnector dbConnector) {
		int[] itemInfo = null;
		try {
			// itemInfo is {menuItemID, amountSold}
			itemInfo = dbConnector.selectBestSeller();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return itemInfo;
	}

    public void addEmployeeDialog(){

        String name = TextUI.promptText("Enter employee name");

        int i = 0;
        for(Job c : Job.values()) {
            System.out.println(i+". "+c);
            i++;
        }

        int jobValue = TextUI.promptNumeric("Select value of employee position");
        Job[] jobs = Job.values();

        if (jobValue < 0 || jobValue >= jobs.length) {
            throw new IllegalStateException("Unexpected value: " + jobValue);
        }

        Job selectedJob = jobs[jobValue];

        int salary = TextUI.promptNumeric("Enter salary without decimals.");




        Employee.employeesList.add(new Employee(selectedJob,salary,name));
		System.out.println("Tilføjet");

    }
	public void removeEmployeeDialog(){


		listEmployees();
		int value = TextUI.promptNumeric("Which employee do you wish to remove?");

		if (value > 0 && value <= Employee.employeesList.size()) {
			System.out.println("Removed "+ Employee.employeesList.get(value));
			Employee.employeesList.remove(value);
		} else {
			System.out.println("invalid input");
			staffMenu();
		}


	}

	public void listEmployees(){
		for(int i=0; i< Employee.employeesList.size();i++){
			System.out.println(i+". "+Employee.employeesList.get(i));
		}
		TextUI.displayMsg("=======================================");

	}

	public void editEmployeeDialog(){
		listEmployees();
		int value = TextUI.promptNumeric("Which employee do you wish to edit?");

		System.out.println("""
				1. Edit salary
				2. Edit position
				3. Edit name
				0. Gå tilbage
				""");


		int choice = TextUI.promptNumeric("Which value do you wish to edit?");

		if(choice == 1){

			int salary = TextUI.promptNumeric("Enter new salary.");

			String name = Employee.employeesList.get(value).name;

			Job selectedJob = Employee.employeesList.get(value).getJob();

			Employee.employeesList.set(value,new Employee(selectedJob,salary,name));

			staffMenu();

		} else if (choice==2) {

			int i = 0;
			for(Job c : Job.values()) {
				System.out.println(i+". "+c);
				i++;
			}

			int jobValue = TextUI.promptNumeric("Select value of employee position");
			Job[] jobs = Job.values();

			if (jobValue < 0 || jobValue >= jobs.length) {
				throw new IllegalStateException("Unexpected value: " + jobValue);
			}

			Job selectedJob = jobs[jobValue];

			String name = Employee.employeesList.get(value).name;

			double salary = Employee.employeesList.get(value).salary;


			Employee.employeesList.set(value,new Employee(selectedJob,salary,name));

			staffMenu();

		} else if (choice == 3){

			String name = TextUI.promptText("Enter employees new name");

			double salary = Employee.employeesList.get(value).salary;

			Job selectedJob = Employee.employeesList.get(value).getJob();

			Employee.employeesList.set(value,new Employee(selectedJob,salary,name));

			staffMenu();
		} else {
			staffMenu();
		}
	}


}
