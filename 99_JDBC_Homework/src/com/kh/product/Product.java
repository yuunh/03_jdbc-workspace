package com.kh.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

/**
 * 
 * @author lyh
 * @since 22.12.15
 * IF문으로
 */

public class Product { // class start

	public static void main(String[] args) { // main start

		// 사용할 객체 및 변수 셋팅
		
		// 스캐너 생성
		Scanner sc = new Scanner(System.in);

		// JDBC 객체
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String name = null;
		String sql = null;
		String type = null;
		int result = 0;
		int price = 0;
		boolean dml = true; // DML / SELECT

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			stmt = conn.createStatement();

			while (true) { // while start

				// 메뉴 출력 및 선택
				System.out.println("=============================");
				System.out.println("1. SELECT 조회");
				System.out.println("2. INSERT 추가");
				System.out.println("3. DELETE 삭제");
				System.out.println("4. 프로그램 종료");

				System.out.print("번호를 입력하세요 : ");
				int num = sc.nextInt();

				sc.nextLine(); // 버퍼에 남아있는 엔터 제거

				if (num == 1) { // 1. SELECT 조회

					dml = false;

					sql = "SELECT * FROM PRODUCT ORDER BY PNO";

				} else if (num == 2) { // 2. INSERT 추가

					dml = true;
					type = "insert";

					System.out.print("상품명 : ");
					name = sc.nextLine();

					System.out.print("상품 가격 : ");
					price = sc.nextInt();

					sc.nextLine();

					sql = "INSERT INTO PRODUCT VALUES(SEQ_PNO.NEXTVAL, " + " '" + name + "', " + price + ", SYSDATE)";

				} else if (num == 3) { // 3. DELETE 삭제

					dml = true;
					type = "delete";

					System.out.print("삭제할 상품명을 입력하세요 : ");
					name = sc.nextLine();

					sql = "DELETE FROM PRODUCT WHERE PNAME = '" + name + "'";

				} else { // 4. 프로그램 종료

					System.out.println("시스템을 종료합니다.");

					sc.close(); // 스캐너 반납

					return; // 메소드 빠져나감

				}
				if (dml) { // dml = true => insert, delete

					result = stmt.executeUpdate(sql);

					if (result > 0) {
						conn.commit();
						System.out.println(type + " 성공!");

					} else {
						conn.rollback();
						System.out.println(type + " 실패했습니다.");
					}

				} else { // dml = false => select

					rset = stmt.executeQuery(sql);

					while (rset.next()) {

						int pno = rset.getInt("PNO");
						name = rset.getString("PNAME");
						price = rset.getInt("PRICE");
						Date rdate = rset.getDate("REG_DATE");

						System.out.println(pno + ", " + name + ", " + price + ", " + rdate);
					}
				}
			} // while end

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			try { // 자원 반납
				
				rset.close();
				stmt.close();
				conn.close();

			} catch (SQLException e) {

			} catch (NullPointerException e) {

			}
		}
	} // main end
} // class end