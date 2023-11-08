package com.ty.school_db.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

	private static List<Connection> connPool = new ArrayList<Connection>();
	private static String driverPath = "org.postgresql.Driver";
	private static String url = "jdbc:postgresql://localhost:5432/school1";
	private static String user = "postgres";
	private static String password = "root";

	private static int poolSize = 5;

	static {

		try {
			Class.forName(driverPath);

			for (int i = 0; i < poolSize; i++) {
				Connection connection = createConnection();
				connPool.add(connection);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection createConnection() {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnectionObject() {

		if (!connPool.isEmpty()) {
			return connPool.remove(0);
		} else {
			return createConnection();
		}
	}

	public static void receiveConnectionObject(Connection con) {

		if (connPool.size() < poolSize) {

			connPool.add(con);
		} else {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
