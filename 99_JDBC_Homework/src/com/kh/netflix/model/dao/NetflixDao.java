package com.kh.netflix.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.netflix.model.vo.Netflix;

public class NetflixDao {

	/**
	 * 사용자가 입력한 정보를 추가시켜주는 메소드
	 * @param n
	 * @return
	 */
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
	
	public ArrayList<Netflix> selectList() {
		
		ArrayList<Netflix> list = new ArrayList<Netflix>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM NETFLIX";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "NETFLIX", "NETFLIX");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				Netflix n = new Netflix();
				
				n.setId(rset.getString("ID"));
				n.setLevel(rset.getString("NLEVEL"));
				n.setNickName(rset.getString("NICKNAME"));
				n.setSignupDate(rset.getDate("SIGNUPDATE"));
				n.setPoint(rset.getInt("POINT"));
				
				list.add(n);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 사용자의 아이디로 회원 검색 요청 처리해주는 메소드
	 * @param Id
	 * @return
	 */
	public Netflix selectId(String Id) {
		
		Netflix n = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM NETFLIX WHERE ID = '" + Id + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "NETFLIX", "NETFLIX");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			if (rset.next()) {
				n = new Netflix(rset.getString("ID"),
								rset.getString("NLEVEL"),
								rset.getString("NICKNAME"),
								rset.getDate("SIGNUPDATE"),
								rset.getInt("POINT"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n;
	}
	
	/**
	 * 사용자의 닉네임으로 키워드 검색 요청시 처리해주는 메소드
	 * @param keyword
	 * @return
	 */
	public ArrayList<Netflix> selectNickName(String keyword) {
		
		ArrayList<Netflix> list = new ArrayList<Netflix>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM NETFLIX WHERE NICKNAME LIKE '%" + keyword + "%'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "NETFLIX", "NETFLIX");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				list.add(new Netflix(rset.getString("ID"),
									 rset.getString("NLEVEL"),
									 rset.getString("NICKNAME"),
									 rset.getDate("SIGNUPDATE"),
									 rset.getInt("POINT")));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	
	/**
	 * 사용자가 입력한 아이디의 정보 수정 요청 처리해주는 메소드
	 * @param n
	 * @return
	 */
	public int updateMember(Netflix n) {
		
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "UPDATE NETFLIX "
				 + 	  "SET NLEVEL = '" + n.getLevel() + "'"
				 + 	  ", NICKNAME = '" + n.getNickName() + "'"
				 + 		 ", POINT = '" + n.getPoint() + "'"
				 + 	   " WHERE ID = '" + n.getId() + "'";
		
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
	
	public int deleteMember(String id) {
		
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "DELETE FROM NETFLIX WHERE ID = '" + id + "'";
		
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
