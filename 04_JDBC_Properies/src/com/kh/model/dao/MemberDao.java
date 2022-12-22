package com.kh.model.dao;

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
import java.util.Scanner;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Member;

// DAO(Data Access Object) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 SQL문 실행 후 결과받기(JDBC)
//							 결과를 Controller로 다시 리턴
public class MemberDao {

	/*
	 * 기존의 방식 : DAO 클래스에 사용자가 요청할 때마다 실행해댜되는 SQL문을 자바 소스코드 내에 명시적으로 작성 => 정적코딩방식
	 * 
	 * > 문제점 : SQL문을 수정해야될 경우 자바 소스코드 파일 수정해야됨 => 수정된 내용을 반영시키고자 한다면 프로그램 재구동해야함
	 * 
	 * > 해결방식 : SQL문을 별도로 관리하는 외부파일(.xml)을 만들어서 실시간으로 그 파일에 기록된 SQL문을 읽어들여서 실행 => 동적코딩방식
	 * 			 여러줄 쓸 수 있도록 => xml로 하는게 좋다!
	 */
	
	private Properties prop = new Properties();
	
	// 사용자가 어떤 서비스 요청할 때마다 계속 new MemberDao().xxx;호출
	// 즉, 서비스 요청할 떄마다 기본생성자가 매번 실행됨
	
	public MemberDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String loginMember(Connection conn, String userId, String userPwd) {
		
		// select문 => 한행 조회 ResultSet => String 변수 => 컬럼 하나만 뽑으면 됨
		
		String userName = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				userName = rset.getString("username");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return userName;
	}
	
	public int insertMember(Connection conn, Member m) {
		
		// insert문 => 처리된 행수 => 트랜젝션 처리
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			// 3) PreparedStatment 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			// 4,5) sql문 실행 및 결과받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			// conn은 아직 반납하면 안됨!! (트랜젝션 처리때 필요)
		}
		return result;
	}
	
	public int deleteMember(Connection conn, String userId) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Member> selectList(Connection conn) {
		
		// select문 => ResultSet => ArrayList
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				list.add(new Member(rset.getInt("userno"),
									rset.getString("userid"),
									rset.getString("userpwd"),
									rset.getString("username"),
									rset.getString("gender"),
									rset.getInt("age"),
									rset.getString("email"),
									rset.getString("phone"),
									rset.getString("address"),
									rset.getString("hobby"),
									rset.getDate("enrolldate")
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
	
	public Member selectByUserId(Connection conn, String userId) {
		
		Member m = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByUserId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				m = new Member(rset.getInt("userno"),
								rset.getString("userid"),
								rset.getString("userpwd"),
								rset.getString("username"),
								rset.getString("gender"),
								rset.getInt("age"),
								rset.getString("email"),
								rset.getString("phone"),
								rset.getString("address"),
								rset.getString("hobby"),
								rset.getDate("enrolldate")
								);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}
	
	public ArrayList<Member> selectByUserName(Connection conn, String keyword) {
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByUserName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				list.add(new Member(rset.getInt("userno"),
									rset.getString("userid"),
									rset.getString("userpwd"),
									rset.getString("username"),
									rset.getString("gender"),
									rset.getInt("age"),
									rset.getString("email"),
									rset.getString("phone"),
									rset.getString("address"),
									rset.getString("hobby"),
									rset.getDate("enrolldate")
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
	
	public int updateMember(Connection conn, Member m) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getAddress());
			pstmt.setString(4, m.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
