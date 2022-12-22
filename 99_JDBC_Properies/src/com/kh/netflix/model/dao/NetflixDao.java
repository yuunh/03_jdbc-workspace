package com.kh.netflix.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.netflix.common.JDBCTemplate;
import com.kh.netflix.model.vo.Netflix;

public class NetflixDao {
	
	private Properties prop = new Properties();
	
	public NetflixDao() {
		
		try {
			prop.loadFromXML(new FileInputStream("resources/test.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 사용자가 입력한 정보를 추가시켜주는 메소드
	 * @param n
	 * @return
	 */
	public int insertMember(Connection conn, Netflix n) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getId());
			pstmt.setString(2, n.getLevel());
			pstmt.setString(3, n.getNickName());
			pstmt.setInt(4, n.getPoint());

			result= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Netflix> selectList(Connection conn) {
		
		ArrayList<Netflix> list = new ArrayList<Netflix>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Netflix n = new Netflix();
				
				n.setId(rset.getString("ID"));
				n.setLevel(rset.getString("N_LEVEL"));
				n.setNickName(rset.getString("NICKNAME"));
				n.setSignupDate(rset.getDate("SIGNUP_DATE"));
				n.setPoint(rset.getInt("POINT"));
				
				list.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	/**
	 * 사용자의 아이디로 회원 검색 요청 처리해주는 메소드
	 * @param Id
	 * @return
	 */
	public Netflix selectId(Connection conn, String Id) {
		
		Netflix n = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Id);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				n = new Netflix(rset.getString("ID"),
								rset.getString("N_LEVEL"),
								rset.getString("NICKNAME"),
								rset.getDate("SIGNUP_DATE"),
								rset.getInt("POINT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return n;
	}
	
	/**
	 * 사용자의 닉네임으로 키워드 검색 요청시 처리해주는 메소드
	 * @param keyword
	 * @return
	 */
	public ArrayList<Netflix> selectNickName(Connection conn, String keyword) {
		
		ArrayList<Netflix> list = new ArrayList<Netflix>();
		
		PreparedStatement pstmt = null
				;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNickName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				list.add(new Netflix(rset.getString("ID"),
									 rset.getString("N_LEVEL"),
									 rset.getString("NICKNAME"),
									 rset.getDate("SIGNUP_DATE"),
									 rset.getInt("POINT")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;

	}
	
	/**
	 * 사용자가 입력한 아이디의 정보 수정 요청 처리해주는 메소드
	 * @param n
	 * @return
	 */
	public int updateMember(Connection conn, Netflix n) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getLevel());
			pstmt.setString(2, n.getNickName());
			pstmt.setInt(3, n.getPoint());
			pstmt.setString(4, n.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteMember(Connection conn, String id) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
