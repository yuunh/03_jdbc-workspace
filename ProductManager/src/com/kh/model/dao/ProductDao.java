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
import com.kh.model.vo.Product;

public class ProductDao {

	private Properties prop = new Properties();
	
	public ProductDao() {
		
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Product> selectList(Connection conn) {
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				list.add(new Product(rset.getString("product_id"),
									 rset.getString("p_name"),
									 rset.getInt("price"),
									 rset.getString("description"),
									 rset.getInt("stock")
									 ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int insertProduct(Connection conn, Product p) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getProduct_Id());
			pstmt.setString(2, p.getP_Name());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDescription());
			pstmt.setInt(5, p.getStock());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateProduct(Connection conn, Product p) {
		
	}
}
