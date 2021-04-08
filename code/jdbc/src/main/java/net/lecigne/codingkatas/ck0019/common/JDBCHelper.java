package net.lecigne.codingkatas.ck0019.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCHelper {

	private final String driver;
	private final String url;
	private final String user;
	private final String password;

	public JDBCHelper(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection cnx = null;
		Class.forName(this.driver);
		cnx = DriverManager.getConnection(this.url, this.user, this.password);
		return cnx;
	}

	public void disconnect(Connection cnx, PreparedStatement ps) {
		try {
			if (cnx != null) {
				cnx.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disconnect(Connection cnx, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect(cnx, ps);
	}

}
