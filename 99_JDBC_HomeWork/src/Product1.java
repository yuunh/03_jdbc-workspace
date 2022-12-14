import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Product1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		int result = 0;
		String sql = null;
		boolean dml = true;
		
		
		
		while (true) {

			System.out.println("=============================");
			System.out.println("1. SELECT");
			System.out.println("2. INSERT");
			System.out.println("3. DELETE");
			System.out.println("4. 종료");
			System.out.print("번호를 입력하세요 : ");
			int num = sc.nextInt();
			sc.nextLine();
			
			if (num == 1) {
				
			}
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
		
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
		}
	}

}
