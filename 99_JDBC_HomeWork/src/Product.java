import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Product {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		int result = 0;

		String sql = null;

		while (true) {

			System.out.println("=============================");
			System.out.println("1. SELECT");
			System.out.println("2. INSERT");
			System.out.println("3. DELETE");
			System.out.println("4. 종료");
			System.out.print("번호를 입력하세요 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				conn = null;
				stmt = null;
				rset = null;

				sql = "SELECT * FROM PRODUCT";

				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
					stmt = conn.createStatement();
					rset = stmt.executeQuery(sql);

					while (rset.next()) {
						int pno = rset.getInt("PNO");
						String pname = rset.getString("PNAME");
						int price = rset.getInt("PRICE");
						Date rdate = rset.getDate("REG_DATE");

						System.out.println(pno + ", " + pname + ", " + price + ", " + rdate);
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
				break;
			case 2:
				System.out.print("상품명 : ");
				String name = sc.nextLine();

				System.out.print("가격 : ");
				int price = sc.nextInt();

				sc.nextLine();

				result = 0;
				conn = null;
				stmt = null;

				sql = "INSERT INTO PRODUCT VALUES(SEQ_PNO.NEXTVAL, " + " '" + name + "', " + price + ", SYSDATE)";

				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
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
				break;
			case 3:
				System.out.print("삭제할 상품명을 입력하세요 : ");
				name = sc.nextLine();

				result = 0;
				conn = null;
				stmt = null;

				sql = "DELETE FROM PRODUCT WHERE PNAME = '" + name + "'";

				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
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
				break;

			case 4:
				System.out.println("시스템을 종료합니다.");
				return;
			}
		}

	}
}