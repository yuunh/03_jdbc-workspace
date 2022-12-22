package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// 공통 템플릿 (매번 반복적으로 작성될 코드를 메소드로 정의하자)
public class JDBCTemplate {

	// 모든 메소드 static 메소드로 만들거임
	// static 메소드는 실행되자마자 메모리 영역에 올라감
	// 싱글톤 패턴 : 메모리 영역에 단 한 번만 올려두고 매번 재사용하는 개념 (Math 클래스)
	
	/**
	 * 1. Connection 객체 생성 (DB와 접속) 한 후 해당 Connection 객체 반환해주는 메소드
	 * @return
	 */
	public static Connection getConnection() {
		
		/*
		 * 기존의 방식 : jdbc driver 구문, 접속할 db의 url, 접속할 계정명 / 비번 등을 자바소스코드 내에 명시적으로 작성 => 정적코딩방식
		 *
		 * > 문제점 : dbms가 변경되었을 경우, 접속할 db의 url 또는 게정명 / 비번이 변경될 경우 => 자바소스코드를 수정해야됨
		 * 			=> 수정된 내용을 반영시키고자 한다면 프로그램 재구동 해야됨 (프로그램이 비정상적으로 종료될 수 있음)
		 * 			=> 유지보수 불편하다!!
		 * 
		 * > 해결방식 : db관련 정보들을 별도로 관리하는 외부파일(.properties)로 만들어서 관리한다
		 * 			 외부파일로부터 읽어들여서 반영시키면 됨 => 동적코딩방식
		 */
		
		Connection conn = null;
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
			
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"),
											   prop.getProperty("username"),
											   prop.getProperty("password"));
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 2. Commit을 처리해주는 메소드 (Connection 전달 받아서)
	 * @param conn
	 */
	public static void commit(Connection conn) {
		
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 3. Rollback을 처리해주는 메소드 (Connection 전달 받아서)
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// JDBC용 객체 전달 받아서 반납 처리해주는 메소드
	
	/**
	 * 4. Statement 관련 객체 전달받아서 반납시켜주는 메소드
	 * @param stmt
	 */
	public static void close(Statement stmt) { // 얘가 부모라서 PreparedStatement 받을 수 있음
		
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 5. Connection 객체 전달받아서 반납시켜주는 메소드
	 * @param conn
	 */
	public static void close(Connection conn) {
		
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 6. ResultSet 객체 전달받아서 반납시켜주는 메소드
	 * @param rset
	 */
	public static void close(ResultSet rset) {
		
		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
