package DAL;

import java.sql.*;

public class databaseConnect {
	public Connection connection;

	public boolean openConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school2?useSSL=false", "root", "");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
