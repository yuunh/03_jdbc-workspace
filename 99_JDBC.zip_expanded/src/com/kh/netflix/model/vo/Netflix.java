package com.kh.netflix.model.vo;

import java.util.Date;

public class Netflix {

	// 필드부
	private String id;
	private String level;
	private String nickName;
	private Date signupDate;
	private int point;
	
	// 생성자부
	
	public Netflix() {}
	
	public Netflix(String id, String level, String nickName, Date signupDate, int point) {
		super();
		this.id = id;
		this.level = level;
		this.nickName = nickName;
		this.signupDate = signupDate;
		this.point = point;
	}
	
	public Netflix(String id, String level, String nickName, int point) {
		super();
		this.id = id;
		this.level = level;
		this.nickName = nickName;
		this.point = point;
	}

	// getter setter
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getSignupDate() {
		return signupDate;
	}
	public void setSignupDate(Date signupDate) {
		this.signupDate = signupDate;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

	// toString
	@Override
	public String toString() {
		return id + ", " + level + ", " + nickName + ", " + signupDate + ", " + point;
	}
	
}
