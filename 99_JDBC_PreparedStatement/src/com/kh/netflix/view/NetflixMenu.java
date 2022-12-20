package com.kh.netflix.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.kh.netflix.controller.NetflixController;
import com.kh.netflix.model.vo.Netflix;

public class NetflixMenu {

	private Scanner sc = new Scanner(System.in);
	
	private NetflixController nc = new NetflixController();
	
	/**
	 * 메인 화면
	 * 사용자가 보게될 첫 화면
	 */
	public void mainMenu() {
		
		while (true) {
			System.out.println("\n=== Netflix 회원 관리 시스템 ===");
			System.out.println("1. 신규 회원 등록");
			System.out.println("2. 회원 목록 조회");
			System.out.println("3. 회원 정보 검색");
			System.out.println("4. 회원 정보 수정");
			System.out.println("5. 회원 정보 삭제");
			System.out.println("6. 시스템 종료");
			
			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			
			sc.nextLine();

			switch (menu) {
			case 1:
				inputMember();
				break;
			case 2:
				nc.selectList();
				break;
			case 3:
				selectMember();
				break;
			case 4:
				updateMember();
				break;
			case 5:
				nc.deleteMember(inputMemberId());
				break;
			case 6:
				System.out.println("\n이용해주셔서 감사합니다. 프로그램을 종료합니다.");
				return;

			default:
				System.out.println("\n번호를 잘못입력하셨습니다. 다시 입력해주세요.");
			}

		}
	} // mainMenu end
	
	/**
	 * 신규 회원 등록
	 * 등록할 회원의 정보를 입력받아서 회원 등록 요청
	 */
	public void inputMember() {
		
		System.out.println("\n=== 신규 회원 등록 ===");
		
		System.out.print("신규 회원 ID(7자 이내) : ");
		String id = sc.nextLine();
		
		System.out.print("신규 회원 등급 : ");
		String level = sc.nextLine();
		
		System.out.print("신규 회원 닉네임(4자 이내) : ");
		String nickName = sc.nextLine();
		
		System.out.print("신규 회원 포인트 : ");
		int point = sc.nextInt();
		
		sc.nextLine();
		
		nc.insertMember(id, level, nickName, point);
	}
	
	public void selectMember() {
		
		System.out.println("\n1. ID로 검색");
		System.out.println("2. 닉네임으로 검색(키워드)");
		
		System.out.print("\n>> ");
		int num = sc.nextInt();
				
		sc.nextLine();
		
		switch (num) {
		case 1:
			nc.selectId(inputMemberId());
			break;
		case 2:
			nc.selectNickName(inputMemberNickName());
			break;
		default:
			System.out.println("\n번호를 잘못입력하셨습니다. 다시 입력해주세요.");
		}
	}
	
	/**
	 * 사용자에게 검색할 아이디 입력받은 후 입력된 값을 반환시켜주는 메소드
	 * @return
	 */
	public String inputMemberId() {
		
		System.out.print("\n회원 아이디 입력 : ");
		
		return sc.nextLine();
	}
	
	/**
	 * 사용자에게 검색할 닉네임 입력받은 후 입력된 값을 반환시켜주는 메소드
	 * @return
	 */
	public String inputMemberNickName() {
		
		System.out.print("\n회원 닉네임 입력 : ");
		
		return sc.nextLine();
	}
	
	/**
	 * 사용자에게 변경할 정보들 입력받는 화면
	 */
	public void updateMember() {
		
		System.out.println("\n=== 회원 정보 수정 ===");
		
		String id = inputMemberId();
		
		System.out.print("수정할 등급 : ");
		String level = sc.nextLine();
		
		System.out.print("수정할 닉네임 : ");
		String nickName = sc.nextLine();
		
	
		System.out.print("수정할 포인트 : ");
		int point = sc.nextInt();
		
		sc.nextLine();
		
		nc.updateMember(id, level, nickName, point);
	}

	//------------------------------ 응답 화면 ------------------------------

	/**
	 * 서비스 요청 처리 후 성공했을 경우 사용자가 보게될 응답 화면
	 * @param message
	 */
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	/**
	 * 서비스 요청 처리 후 실패했을 경우 사용자가 보게될 응답 화면
	 * @param message
	 */
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}
	
	/**
	 * 조회 서비스 요청시 조회 결과가 없을 경우 사용자가 보게될 응답 화면
	 * @param message
	 */
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	/**
	 * 조회 서비스 요청시 조회 결과가 여러 행일 경우 사용자가 보게될 응답 화면
	 * @param list
	 */
	public void displayNetflixList(ArrayList<Netflix> list) {
		System.out.println("\n조회된 결과는 다음과 같습니다.");
		
		for (Netflix n : list) {
			System.out.println(n);
		}
	}
	
	/**
	 * 조회 서비스 요청시 조회 결과가 한 행일 경우 사용자가 보게될 응답 화면
	 * @param n
	 */
	public void displayNetflix(Netflix n) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.");
		System.out.println(n);
	}
}
