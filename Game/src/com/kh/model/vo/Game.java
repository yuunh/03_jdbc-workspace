package com.kh.model.vo;

import java.util.Date;

public class Game {

	private int pNo; // 게임 번호
	private String pName; // 게임 이름
	private int price; // 게임 가격
	private Date regDate; // 게임 등록일자
	
	public Game() {}

	public Game(int pNo, String pName, int price, Date regDate) {
		super();
		this.pNo = pNo;
		this.pName = pName;
		this.price = price;
		this.regDate = regDate;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
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

	@Override
	public String toString() {
		return pNo + ", " + pName + ", " + price + ", " + regDate;
	}

}
