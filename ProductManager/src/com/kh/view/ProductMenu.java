package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {

	private Scanner sc = new Scanner(System.in);
	
	private ProductController pc = new ProductController();
	
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("\n==== 프로그램 메뉴 ====");
			
			System.out.println("1. 전제 조회 하기");
			System.out.println("2. 상품 추가 하기");
			System.out.println("3. 상품 수정 하기");
			System.out.println("4. 상품 삭제 하기");
			System.out.println("5. 상품 검색 하기");
			System.out.println("0. 프로그램 종료하기");
			
			System.out.print("\n>> 메뉴 선택 : ");
			int menu = sc.nextInt();
			
			sc.nextLine();
			
			switch (menu) {
			case 1: pc.selecList();
				break;
			case 2: inputProduct();
				break;
			case 3: updateProduct();
				break;
			case 4:
				break;
			case 5:
				break;
			case 0: System.out.println("이용해주셔서 감사합니다. 프로그램이 종료됩니다.");
				return;
			default: System.out.println("메뉴를 잘못입력하셨습니다. 다시 입력해주세요.");
				break;
			} // switch
		} // while
	} // main
	
	public void inputProduct() {
		
		System.out.println("\n==== 상품 추가 ====");
		
		System.out.print("추가할 상품 아이디 : ");
		String productId = sc.nextLine();
		
		System.out.print("추가할 상품 이름 : ");
		String pName = sc.nextLine();
		
		System.out.print("추가할 상품 가격 : ");
		int price = sc.nextInt();
		
		sc.nextLine();
		
		System.out.print("추가할 상품 상세 정보 : ");
		String description = sc.nextLine();
		
		System.out.print("추가할 상품 재고 : ");
		int stock = sc.nextInt();
		
		sc.nextLine();
		
		pc.insertProduct(productId, pName, price, description, stock);
		
	}
	
	public void updateProduct() {
		
		System.out.println("\n==== 상품 정보 수정 ====");
		
		System.out.print("수정할 상품 아이디 : ");
		String productId = sc.nextLine();
		
		System.out.print("수정할 상품 이름 : ");
		String pName = sc.nextLine();
		
		System.out.print("수정할 상품 가격 : ");
		int price = sc.nextInt();
		
		sc.nextLine();
		
		System.out.print("수정할 상품 상세 정보 : ");
		String description = sc.nextLine();
		
		System.out.print("수정할 상품 재고 : ");
		int stock = sc.nextInt();
		
		pc.updateProduct(productId, pName, price, description, stock);
		
	}
	
	//------------------------------ 응답 화면 ------------------------------

	public void displayNoDate(String message) {
		
		System.out.println("/n" + message);
	}
	
	public void displayProductList(ArrayList<Product> list) {
		
		System.out.println("\n<조회된 데이터는 다음과 같습니다>");
		System.out.println();
		
		for (Product p : list) {
			System.out.println(p);
		}
	}
	
	public void displaySuccess(String message) {
		
		System.out.println(message);
	}
	
	public void displayFail(String message) {
		
		System.out.println(message);
	}
}
