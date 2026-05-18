public class User {

	private int userID;
	private String username;
	private double salary;
	private Position position;

	public User(int userID,String username,double salary,Position position) {
		this.userID = userID;
		this.username = username;
		this.salary = salary;
		this.position = position;
	}

	public int getUserID() {
		return userID;
	}

	public String getUsername() {
		return username;
	}

	public double getSalary() {
		return salary;
	}

	public Position getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "User{" +
				"userID=" + userID +
				", username='" + username + '\'' +
				", salary=" + salary +
				", position=" + position +
				'}';
	}
}
