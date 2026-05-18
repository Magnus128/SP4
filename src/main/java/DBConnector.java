import java.sql.DriverManager;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.*;

public class DBConnector {

	Connection conn;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

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

	public MenuCard selectMenuCard() {
		MenuCard menuCard = new MenuCard();

		String query = "select * from menuCard";

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				int menuItemID = rs.getInt("menuItemID");
				String itemName = rs.getString("itemName");
				String category = rs.getString("category");
				double price = rs.getDouble("price");

				menuCard.getMenuItems().add(new Dish(menuItemID, itemName, category, price));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return menuCard;
	}


	public void insertOrdre(Order order) {
		String query = "INSERT INTO OrderDetails ( orderID, orderDate, orderTime,itemID) VALUES ( ?, ? ,?,?)";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setLong(1, order.getOrderID());
			pr.setString(2, order.getOrderDate().toString());
			pr.setString(3, order.getOrderTime().format(formatter));
			pr.setDouble(4, order.getMenuID());

			pr.executeUpdate();
			//System.out.println("Inserted virker");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void insertInvoice(Invoice invoice) {
		String query = "INSERT INTO Invoices (orderID, orderDate, orderTime , totalBill) VALUES ( ?, ?, ?, ?)";

		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setLong(1, invoice.getOrderID());
			pr.setString(2, invoice.getDate().toString());
			pr.setString(3, invoice.getTime().format(formatter));
			pr.setDouble(4, invoice.getTotalAmount());

			pr.executeUpdate();
			//System.out.println("Inserted virker");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public double selectDailyRevenue(String day) throws IllegalArgumentException, SQLException{
		double revenue = 0;

		try {
			Statement statement = conn.createStatement();

			// day skal være formateret som YYYY-MM-DD
			String query = "SELECT SUM(totalBill) FROM Invoices WHERE orderDate = '" + day + "'";

			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				revenue = rs.getDouble(1);
			} else {
				throw new IllegalArgumentException();
			}
			conn.close();
		} catch (SQLException e) {
			throw e;
		}
		return revenue;
	}

	public double selectMonthlyRevenue(String yearMonth) throws IllegalArgumentException, SQLException {
		double revenue = 0;
		String[] values = yearMonth.split("-");
		String year = values[0];
		String month = values[1];
		try {
			Statement statement = conn.createStatement();

			// month skal være formateret som YYYY-MM
			String query = "select sum(totalBill)  from Invoices where strftime('%Y', orderDate) = '" + year + "' " +
					"and strftime('%m', orderDate) = '" + month + "'";

			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				revenue = rs.getDouble(1);
			} else {
				throw new IllegalArgumentException();
			}
			conn.close();
		} catch (SQLException e) {
			throw e;
		}
		return revenue;
	}

	public int[] selectBestSeller() throws SQLException {
		int itemID = 0;
		int count = 0;
		try {
			Statement statement = conn.createStatement();
			String query = "select menuItemID, itemName, count() as itemCount\n" +
					"from orderDetails join menuCard on menuCard.menuItemID = orderDetails.itemID\n" +
					"group by menuItemID\n" +
					"order by  count() desc\n" +
					"limit 1;";
			// rs gets the value of an itemID
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				itemID = rs.getInt("menuItemID");
				count = rs.getInt("itemCount");
			}
			conn.close();
		} catch (SQLException e) {
			throw e;
		}
		return new int[] {itemID, count};
	}
}
