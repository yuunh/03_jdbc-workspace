package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.GameController;
import com.kh.model.vo.Game;

public class GameMenu {

	private Scanner sc = new Scanner(System.in);
	private GameController gc = new GameController();

	public void mainMenu() {

		while (true) {

			System.out.println("\n===== Game Program =====");

			System.out.println("1) 게임 목록 조회 ");
			System.out.println("2) 게임 이름으로 검색");
			System.out.println("3) 게임 가격으로 검색");
			System.out.println("4) 게임 추가");
			System.out.println("5) 게임 정보 수정");
			System.out.println("6) 게임 삭제");
			System.out.println("0) 프로그램 종료");

			System.out.println("\n메뉴를 선택하세요.");
			System.out.print(">> ");
			int menu = sc.nextInt();

			sc.nextLine(); // 버퍼에 남아있는 엔터 제거

			switch (menu) {
			case 1:
				gc.selectGame();
				break;
			case 2:
				gc.searchName(pName());
				break;
			case 3:
				gc.searchPrice(lowestPrice(), highestPrice());
				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			case 0:
				System.out.println("\n== 프로그램이 종료됩니다. 이용해주셔서 감사합니다. ==");
				return;
			default:
				System.out.println("\n== 번호를 잘못입력하셨습니다. 다시 입력해주세요. ==");
				break;

			}

		} // while

	} // main
	
	public String pName() {
		
		System.out.print("\n게임 이름을 입력하세요 : ");
		
		return sc.nextLine();
	}
	
	public int lowestPrice() {
		
		System.out.print("\n최소 가격을 입력하세요 : ");
		
		return sc.nextInt();
		
		//sc.nextLine();
	}
	
	public int highestPrice() {
		
		System.out.print("최대 가격을 입력하세요 : ");
		
		return sc.nextInt();
		
		//sc.nextLine();
	}
	
	public void displayNoDate(String message) {
		
		System.out.println("\n" + message);
		
	}
	
	public void displayGameList(ArrayList<Game> list) {
		
		System.out.println("\n== 조회된 데이터는 다음과 같습니다. ==\n");
		
		for (Game g : list) {
			System.out.println(g);
		}
	}
}
