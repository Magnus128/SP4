import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.*;

public class DBConnector {

	Connection conn;

	public void connect(String url) {
		try {
			conn = DriverManager.getConnection(url);

			System.out.println("Forbindelse til databasen er oprettet");
		} catch (SQLException e) {
			System.out.println("Kunne ikke oprette forbindelse til databasen");
		}
	}

	public ArrayList<Table> selectTable() {
		ArrayList<Table> tables = new ArrayList<>();

		String query = "select * from Tables";

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				TableStatus tableStatus = TableStatus.valueOf(rs.getString("tableStatus"));
				int seatAmount = rs.getInt("seatAmount");

				tables.add(new Table(id, tableStatus, seatAmount));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return tables;
	}

	public ArrayList<User> selectUser() {
		ArrayList<User> users = new ArrayList<>();

		String query = "select * from users";

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				int userID = rs.getInt("id");
				String userName = rs.getString("firstName");
				userName += " " + rs.getString("lastName");
				Position position = Position.valueOf(rs.getString("position").toUpperCase().replace(" ", ""));
				double salary = rs.getDouble("salary");

				users.add(new User(userID, userName, salary, position));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return users;
	}

	public MenuCard selectFoodMenu() {
		MenuCard foodMenu = new MenuCard();

		String query = "select * from foodMenu";

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				int menuItemID = rs.getInt("menuItemID");
				String itemName = rs.getString("itemName");
				String category = rs.getString("category");
				double price = rs.getDouble("price");

				foodMenu.getMenuItems().add(new Dish(menuItemID, itemName, category, price));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return foodMenu;
	}
}
