package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

// View : 사용자가 보게 될 시각적인 요소(화면) 출력 및 입력
public class MemberMenu {

	// Scanner 객체 생성 (다 쓸 수 있게 전역으로)
	private Scanner sc = new Scanner(System.in);
	
	// MemberController 객체 생성 (바로 요청할 수 있게 전역으로)
	private MemberController mc = new MemberController();
	
	
	/**
	 * 사용자가 보게될 첫 화면 (메인 화면)
	 */
	public void mainMenu() {
	
		while (true) { // while start
			
			System.out.println("\n   == 회원관리 프로그램 ==");
		    System.out.println("┌───────────────────────────────┐");
		    System.out.println("│   0. 로그인      		│");
		    System.out.println("│   1. 회원 추가     		│"); 		// insert
		    System.out.println("│   2. 회원전체조회     	 	│"); 	// select
		    System.out.println("│   3. 회원 아이디 검색   	 	│"); 	// where
		    System.out.println("│   4. 회원 이름으로 키워드 검색 	│"); 	// where
		    System.out.println("│   5. 회원 정보 변경    	 	│"); 	// update
		    System.out.println("│   6. 회원탈퇴     		│"); 		// delete
		    System.out.println("│   10. 프로그램 종료   		│");
		    System.out.println("└───────────────────────────────┘");
			
			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			
			sc.nextLine(); // 버퍼에 남아있는 엔터 제거
			
			switch (menu) { // switch start
			case 0: loginMember();
					break;
			case 1: inputMember(); 
					break;
			case 2: mc.selectList();  // 조회는 입력받을 거 없으니까 바로 Controller 호출
					break; 
			case 3: mc.selectByUserId(inputMemberId()); 
					break;
			case 4: mc.selectByUserName(inputMemberName()); 
					break;
			case 5: updateMember();
					break;
			case 6: mc.deleteMember(inputMemberId());
					break;
			case 10: System.out.println("이용해주셔서 감사합니다. 프로그램이 종료됩니다."); 
					return;
			default: System.out.println("메뉴를 잘못입력하셨습니다. 다시 입력해주세요.");
				
			} // switch end
		} // while end
	} // mainMenu end
	
	/**
	 * 로그인 요청하는 화면
	 */
	public void loginMember() {
		
		System.out.println("\n=== 로그인 ===");
		
		String userId = inputMemberId();
		
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		mc.loginMember(userId, userPwd);
	}
	
	/**
	 * 회원 추가 화면 (서브화면)
	 * 추가하고자 하는 회원의 정보를 입력받아서 회원 추가 요청
	 */
	public void inputMember() {
		
		System.out.println("\n==== 회원 추가 ====");
		
		// 아이디 ~ 취미까지 입력받기
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		
		System.out.print("성별(F/M) : ");
		String gender = sc.nextLine();
		
		System.out.print("나이 : "); // 웹에서는 문자로 넘어오기 때문에 문자열로 받자
		String age = sc.nextLine();
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("전화번호(- 빼고 입력) : ");
		String phone = sc.nextLine();
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		System.out.print("취미(,로 공백없이 작성) : ");
		String hobby = sc.nextLine();
		
		// 회원 추가 요청 == controller 메소드 호출
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
	}
	
	/**
	 * 사용자에게 회원 아이디 입력받은 후 입력된 값을 반환시켜주는 메소드
	 * @return : 사용자가 입력한 아이디 값
	 */
	public String inputMemberId() {
		
		System.out.print("\n회원 아이디 입력 : ");
		
		return sc.nextLine();
	}
	
	/**
	 * 사용자에게 검색할 회원명(키워드) 입력받은 후 입력된 값을 반환시켜주는 메소드
	 * @return : 사용자가 입력한 회원명(키워드)
	 */
	public String inputMemberName() {
		
		System.out.print("\n회원 이름(키워드) : ");
		
		return sc.nextLine();
	}
	
	/**
	 * 사용자에게 변경할 정보들(비번, 이메일, 전화번호, 주소)과 해당 회원 아이디 입력받는 화면
	 */
	public void updateMember() {
		
		System.out.println("\n==== 회원 정보 변경 ====");
		
		// 비번, 이메일, 전화번호, 주소, !아이디!
		
		String userId = inputMemberId(); // 위의 두 줄을 다음과 같이 줄일 수 있음!
		
		System.out.print("변경할 암호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("변경할 이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("변경할 전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("변경할 주소 : ");
		String address = sc.nextLine();
		
		mc.updateMember(userId, userPwd, email, phone, address);
		
	}
	
	//------------------------------ 응답 화면 ------------------------------
	
	/**
	 * 서비스 요청 처리 후 성공했을 경우 사용자가 보게 될 응답 화면
	 * @param message : 성공 메세지
	 */
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	/**
	 * 서비스 요청 처리 후 실패했을 경우 사용자가 보게 될 응답 화면
	 * @param message : 실패 메세지
	 */
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}
	
	/**
	 * 조회 서비스 요청시 조회 결과가 없을 경우 사용자가 보게 될 응답 화면
	 * @param message
	 */
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	/**
	 * 조회 서비스 요청시 조회 결과가 여러 행일 경우 사용자가 보게 될 응답 화면
	 * @param list
	 */
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.");
		
		// 향상된 for문 (For Each문)
		for (Member m : list) { // == m : list.get(0) => m : list.get(1) => ....
			System.out.println(m);
		}
	}
	
	/**
	 * 조회 서비스 요청시 조회 결과가 한 행일 경우 사용자가 보게 될 응답 화면
	 * @param m
	 */
	public void displayMember(Member m) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.");
		System.out.println(m);
	}
}
