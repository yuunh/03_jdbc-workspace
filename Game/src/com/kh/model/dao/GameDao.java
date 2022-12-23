package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Game;

public class GameDao {

	private Properties prop = new Properties();

	public GameDao() {
		
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Game> selectGame(Connection conn) {
		
		ArrayList<Game> list = new ArrayList<Game>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectGame");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				list.add(new Game(rset.getInt("pNo"),
								  rset.getString("pName"),
								  rset.getInt("price"),
								  rset.getDate("reg_Date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Game> searchName(Connection conn, String pName) {
		
		ArrayList<Game> list = new ArrayList<Game>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + pName + "%");
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Game(rset.getInt("pNo"), 
								  rset.getString("pName"), 
								  rset.getInt("price"),
								  rset.getDate("reg_Date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Game> searchPrice(Connection conn, int lowestPrice, int highestPrice) {
		
		ArrayList<Game> list = new ArrayList<Game>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchPrice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, lowestPrice);
			pstmt.setInt(2, highestPrice);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				list.add(new Game(rset.getInt("pNo"), 
								  rset.getString("pName"), 
								  rset.getInt("price"),
								  rset.getDate("reg_Date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
}
