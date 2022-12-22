package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class TestRun {

	public static void main(String[] args) {

		/*
		 *  * JDBC용 객체
		 *  - Connection : DB의 연결정보를 담고있는 객체
		 *  - [Prepared]Statement : 연결된 DB에 SQL문 전달해서 실행하고 그 결과를 받아내는 객체
		 *  - ResultSet : SELECT문 실행 후 조회된 결과물들이 담겨있는 객체
		 *  
		 *  * JDBC 과정 (순서 중요 !!)
		 *  1) jdbc driver 등록 : 해당 DBMS(오라클)가 제공하는 클래스 등록
		 *  2) Connection 생성 : 연결하고자 하는 DB정보를 입력해서 해당 DB와 연결하면서 생성
		 *  3) Statement 생성 : Connection 객체를 이용해서 생성 (SQL문 실행 및 결과를 받는 객체)
		 *  4) SQL문 전달하면서 실행 : Statement 객체를 이용해서 SQL문 실행
		 *  5) 결과받기
		 *  	> SELECT문 실행 => ResultSet 객체 (조회된 데이터들이 담겨있음) => 6_1
		 *  	>	 DML문 실행 => int (처리된 행 수)						=> 6_2
		 *  6_1) ResultSet에 담겨있는 데이터들을 하나씩 뽑아서 vo객체에 주섬주섬 옮겨담기 [+ 여러행 조회시에는 ArrayList에 차곡차곡 담기]
		 *  6_2) 트랜젝션 처리 (성공적으로 수행했으면 commit, 실패했으면 rollback)
		 *  7) 다 사용한 JDBC 객체를 반드시!! 자원 반납!! 안하면 디비 락걸림.. (close) => 생성된 역순으로 ~
		 */
		
		/*
		// 1. 각자 pc(localhost)에 JDBC계정에 연결한 후 TEST 테이블에 INSERT 해보기!
		// insert문 => 처리된 행 수(int) => 트랜젝션 처리
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("번호 : ");
		int num = sc.nextInt();
		
		sc.nextLine();
		
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		// 필요한 변수 셋팅
		int result = 0; 		// 결과(처리된 행수)를 받아줄 변수
		Connection conn = null; // DB의 연결정보를 보관할 객체
		Statement stmt = null; 	// SQL문 전달해서 실행 후 결과받는 객체
		
		// ResultSet은 select 에서만 필요함! 그래서 지금은 패스~
		
		// 앞으로 실행할 SQL문 작성 ("완성형태"로 만들기) ("쿼리문" 안에 세미콜론 없어야됨!! 절대!! 중요!!)
		// String sql = "INSERT INTO TEST VALUES(1, '강개순', SYSDATE)";
		String sql = "INSERT INTO TEST VALUES(" + num + ", '" + name + "', SYSDATE)";
		
		// 우리가 DB에도 접속정보 입력하는 것처럼 자바에도 접속정보를 입력해야함
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 패키지명 클래스명 : forName => 찾아서 등록
			
			// System.out.println("jdbc driver 등록 성공!");
			
			// OracleDriver 자바에서 제공하는 클래스 아님 패키지 이름을 보삼 oracle로 시작함..
			// 그럼 어케 씀..? => ojdbc6.jar 파일을 등록해줘야함!
			
			// 2) Connection 객체 생성 : DB에 연결(url, 계정명, 비번)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4,5) sql문을 전달하면서 실행 후 결과받기 (처리된 행수)
			result = stmt.executeUpdate(sql);
			// 내가 실행할 sql문이 DML문(I,U,D) => stmt.executeUpdate("dml문") : int
			// 내가 실행할 sql문이 SELECT문	   => stmt.executeQuery("select문") : ResultSet
			
			// 6) 트랜젝션 처리
			if (result > 0) { // 성공했을 경우 commit
				conn.commit();
			} else { // 실패했을 경우 rollback
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("OracleDriver 클래스를 찾지 못했습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 다 쓴 JDBC 객체 자원 반납 (생성된 역순으로)
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (result > 0) {
				System.out.println("insert 성공!!");
			} else {
				System.out.println("insert 실패ㅠㅠ");
			}
		}
		*/
		
		// 2. 내 PC DB상에 JDBC계정에 TEST 테이블에 있는 모든 데이터 조회해보기
		//	  SELECT문 => 결과 ResultSet(조회된 데이터들 담겨있음) 받기 => ResultSet으로부터 데이터 뽑기
		
		// 필요한 변수 셋팅
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; // select문 실행하여 조회된 결과 값들이 처음에 실질적으로 담길 객체

		// 실행할 sql문
		String sql = "SELECT * FROM TEST";

		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// 3) Statement 객체 생성
			stmt = conn.createStatement();

			// 4,5) sql문 전달해서 실행 후 결과받기 (ResultSet 객체)
			rset = stmt.executeQuery(sql);

			// rset.next() => 커서를 움직이는 메소드 boolean : 다음꺼 있으면 true 없으면 false

			// 6)
			while (rset.next()) {
				int tno = rset.getInt("TNO");
				String tname = rset.getString("TNAME");
				Date tdate = rset.getDate("TDATE");

				System.out.println(tno + ", " + tname + ", " + tdate);
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
		
	}

}
