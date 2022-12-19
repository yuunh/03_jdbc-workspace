package com.kh.netflix.view;

import java.util.Date;
import java.util.Scanner;

import com.kh.netflix.controller.NetflixController;

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

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

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

}
