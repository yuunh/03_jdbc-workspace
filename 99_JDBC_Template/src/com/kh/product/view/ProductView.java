package com.kh.product.view;

import java.util.Scanner;

import com.kh.product.controller.Controller;
import com.kh.product.model.vo.Product;

public class ProductView {
	Scanner sc = new Scanner(System.in);
	Controller cl = new Controller();
	Product pd = new Product();
	String name = "";

	
	
	public void mainmenu() {
			while(true) {
			int result = 0;
			System.out.println("제품관련 프로그램");
			System.out.println("1) 제품 검색(SELECT)");
			System.out.println("2) 제품 추가(INSERT)");
			System.out.println("3) 제품 삭제(DELETE)");
			System.out.println("4) 종료 (EXIT)");
			System.out.print("숫자 입력 : ");
			int num = sc.nextInt();
			sc.nextLine();
			
			switch(num) {
			case 1: 
				System.out.println(cl.select()); break;
				
			case 2:
				result = insertMenu(result);
				break;
				
			case 3:
				result = deleteMenu(result);
				break;
			
			case 4:
				return;
			} //switch end
			if (result == 1) {
				System.out.println("성공!");
			} else if(result == 2) {
				System.out.println("실패!");
			} 
		} //while end
	} // mainmenu end
	
	
	
	public int insertMenu(int result) {
		System.out.print("제품명 : ");
		name = sc.nextLine();
		
		System.out.print("제품가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		
		return result = cl.dml(name, price);
	}//insertMenu end
	
	
	public int deleteMenu(int result) {
		System.out.print("제품명 : ");
		String name = sc.nextLine();
		return result = cl.dml(name, -1);
	}//delete end
	
	
}// class end
