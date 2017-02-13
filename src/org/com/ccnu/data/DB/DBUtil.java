package org.com.ccnu.data.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author InJavaWeTrust
 * 
 */
public class DBUtil {

	/**
	 * 连接数据库
	 * 
	 * @return
	 */
	public Connection connection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccnutou","root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public ResultSet findAll() throws SQLException {
		Connection conn = this.connection();
		PreparedStatement pS = conn.prepareStatement("select id,url, name,other,abstracts from b_book");
		return pS.executeQuery();
	}

}
