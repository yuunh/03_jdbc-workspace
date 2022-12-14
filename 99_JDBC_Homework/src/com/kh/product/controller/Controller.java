package com.kh.product.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.product.model.vo.Product;

public class Controller {
	int result =0;
	String sql = "";
	Connection conn = null;
	Statement stmt = null;	
	ResultSet rset = null;
	Product pd = null;
	ArrayList<Product> list = new ArrayList<>();
	
	public ArrayList select() {
		ArrayList<Product> list = new ArrayList<>();
		sql = "SELECT * FROM PRODUCT";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //jdbc driver 등록
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC", "JDBC");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				pd = new Product(rset.getInt("PNO")
						, rset.getString("PNAME")
						, rset.getInt("PRICE") 
						, rset.getDate("REG_DATE"));
				list.add(pd);
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
	}// Select end

	public int dml(String name, int price) {
		int i = 0;
		if( price >= 0) {
			sql = "INSERT INTO PRODUCT VALUES(SEQ_PRO.NEXTVAL, '" + name + "', " + price + ", SYSDATE)"; 
		} else {
			sql = "DELETE FROM PRODUCT WHERE PNAME = '" + name + "'";
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC", "JDBC");
			
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) { //성공
				conn.commit();
				i++;
			}else { // 실패
				conn.rollback();
				i +=2;
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
		return i;
	}// INSERT END
	
}//class end
