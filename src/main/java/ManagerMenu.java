import java.sql.SQLException;
import java.util.ArrayList;

import org.w3c.dom.Text;
import util.*;

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
		while (input != 0) {
			switch (input) {
				case 1:
					menuCardMenu();
					break;
				case 2:
					reportsMenu();
					break;
				case 3:
					inventoryMenu();
					break;
				case 4:
					staffMenu();
					break;
				default:
					System.out.println("Prøv igen:");
			}
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
		while (input != 0) {
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

	}


	private void inventoryMenu() {
	}

	private void reportsMenu() {
		DBConnector dbConnector = new DBConnector();
		dbConnector.connect("jdbc:sqlite:restaurantData.sqlite");


		int input = 1;
		while (input != 0) {
			try {
				System.out.println("""
				1. Vis omsætning for en dag
				2. Vis omsætning for en måned
				3. Vis bedst sælgende menugenstand
				0. Gå tilbage
				""");
				input = TextUI.promptNumeric("Indtast et nummer for at vælge: ");
			} catch (NumberFormatException e) {
				System.out.println("Prøv igen, indtast venligst et nummer:");
			}
			switch (input) {
				case 1:
					double revenue = showDailyRevenue(dbConnector);
					System.out.println("Den samlede omsætning for din valgte dato er: " + revenue);
					break;
				case 2:
				case 3:
				default:
					System.out.println("Venligst indtast et validt nummer:");
			}
		}

	}

	private void menuCardMenu() {
	}

	private double showDailyRevenue(DBConnector dbConnector) {
		double revenue = 0;
		try {
			String date = TextUI.promptText("Skriv en dato i formatet (YYYY-MM-DD):");
			revenue = dbConnector.selectDailyRevenue(date);
		} catch (SQLException e) {
			System.out.println("Prøv igen. Indtast en valid dato i formatet (YYYY-MM-DD):");
			return showDailyRevenue(dbConnector);
		}
		return revenue;
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

			int salary = Employee.employeesList.get(value).salary;


			Employee.employeesList.set(value,new Employee(selectedJob,salary,name));

			staffMenu();

		} else if (choice == 3){

			String name = TextUI.promptText("Enter employees new name");

			int salary = Employee.employeesList.get(value).salary;

			Job selectedJob = Employee.employeesList.get(value).getJob();

			Employee.employeesList.set(value,new Employee(selectedJob,salary,name));

			staffMenu();
		} else {
			staffMenu();
		}
	}


}
