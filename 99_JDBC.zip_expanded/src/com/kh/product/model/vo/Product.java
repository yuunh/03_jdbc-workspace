package com.kh.product.model.vo;

import java.sql.Date;

public class Product {
	private int pno;
	private String pname;
	private int price;
	private Date regDate;
	
	public Product() {}

	public Product(int pno, String pname, int price, Date regDate) {
		this.pno = pno;
		this.pname = pname;
		this.price = price;
		this.regDate = regDate;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String toString() {
		return pno + ". pname=" + pname + ", price=" + price + ", regDate=" + regDate + "\n";
	}
}
