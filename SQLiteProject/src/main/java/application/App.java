package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int[] ids = { 1, 2, 3, 4 };
		String[] names = { "John", "Kohl", "Charlie", "Kale" };

		// Load JDBC class
		Class.forName("org.sqlite.JDBC");

		// Make connection
		String dbUrl = "jdbc:sqlite:people.db";
		Connection conn = DriverManager.getConnection(dbUrl);
		conn.setAutoCommit(false);

		Statement stmt = conn.createStatement();

		String sql = "create table if not exists user (id integer primary key, name text not null)";
		stmt.execute(sql);

		/*
		 * sql = "insert into user(id, name) values (101, 'John')"; stmt.execute(sql);
		 * 
		 * sql = "insert into user(id, name) values (102, 'Kohl')"; stmt.execute(sql);
		 */

		sql = "insert into user(id, name )values (?, ?)";
		PreparedStatement insertStmt = conn.prepareStatement(sql);

		int i;

		for (i = 0; i < ids.length; i++) {
			insertStmt.setInt(1, ids[i]);
			insertStmt.setString(2, names[i]);

			// insertStmt.executeUpdate();
		}

		conn.commit();

		insertStmt.close();

		sql = "select id, name from user";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");

			System.out.println("Id : " + id + " Name: " + name);

		}

		sql = "drop table user";
		stmt.execute(sql);

		System.out.println(conn);

		rs.close();
		stmt.close();
		conn.close();

	}

	
}
