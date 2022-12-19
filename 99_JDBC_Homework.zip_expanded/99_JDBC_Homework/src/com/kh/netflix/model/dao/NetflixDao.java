package com.kh.netflix.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.netflix.model.vo.Netflix;

public class NetflixDao {

	public int insertMember(Netflix n) {
		
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "INSERT INTO NETFLIX VALUES ('" + n.getId() 
											+ "', '" + n.getLevel() 
											+ "', '" + n.getNickName()
									+ "', SYSDATE, " + n.getPoint() + ")";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "NETFLIX", "NETFLIX");
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
