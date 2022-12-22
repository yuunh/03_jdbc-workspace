package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductController {

	public void selecList() {
		
		ArrayList<Product> list = new ProductService().selectList();
		
		if (list.isEmpty()) {
			new ProductMenu().displayNoDate("조회된 상품이 없습니다.");
		} else {
			new ProductMenu().displayProductList(list);
		}
	}
	
	public void insertProduct(String productId, String pName, int price, String description, int stock) {
		
		Product p = new Product(productId, pName, price, description, stock);
		
		int result = new ProductService().insertProduct(p);
		
		if (result > 0) {
			new ProductMenu().displaySuccess("상품이 추가되었습니다.");
		} else {
			new ProductMenu().displayFail("상품 추가를 실패했습니다.");
		}
	}
	
	public void updateProduct(String productId, String pName, int price, String description, int stock) {
		
		Product p = new Product();
		
		p.setProduct_Id(productId);
		p.setP_Name(pName);
		p.setPrice(price);
		p.setDescription(description);
		p.setStock(stock);
		
		int result = new ProductService().updateProduct(p);
		
		if (result > 0) {
			new ProductMenu().displaySuccess("상품 정보가 수정되었습니다.");
		} else {
			new ProductMenu().displayFail("상품 정보 수정에 실패했습니다.");
		}
	}
}
