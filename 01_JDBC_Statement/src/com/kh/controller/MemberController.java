package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

/*
 * Controller : View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
 * 				해당 메소드로 전달된 데이터 [가공처리 한 후] DAO로 전달하면서 호출
 * 				DAO로부터 반환받은 결과에 따라 성공인지 실패인지 판단 후 응답 화면 결정 (view 메소드 호출)
 */
public class MemberController {
	
	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메소드
	 * @param userId ~ hobby : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email, String phone, String address, String hobby) {
	
		// 받은 값들의 데이터를 직접적으로 처리해주는 DAO로 넘기기
		// 어딘가에 담아서 전달
		// 어딘가? => Member 객체!
		
		// 방법 1) 기본 생성자로 생성한 후에 각 필드에 setter 메소드 통해서 일일이 담는 방법 => 매개변수가 몇 개 없을 때 사용..
		// 방법 2) 매개변수 생성자를 통해서 생성과 동시에 담는 방법
		
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);
		// 나이를 반드시 int형으로 바꿔야함!!
		// String => int로 변경하는 방법? => parseInt();
		
		// 만약 new부터 빨간줄 뜨는 경우 => 그 데이터 타입을 받는 매개변수 생성자 없다는 거임 확인 잘하기!
		
		// System.out.println(m);
		
		int result = new MemberDao().insertMember(m);
		
		// 사용자에게 성공 여부 문구 보여주기
		if (result > 0) { // 성공 시
			new MemberMenu().displaySuccess("성공적으로 회원 추가되었습니다.");
		} else {		  // 실패 시
			new MemberMenu().displayFail("회원 추가 실패했습니다.");
		}
	}
	
	/**
	 * 사용자의 회원 전체 조회 요청을 처리해주는 메소드
	 */
	public void selectList() {
		
		ArrayList<Member> list = new MemberDao().selectList();
		
		// 사용자에게 조회 결과가 있는지 없는지 판단 후 보여줄 응답 화면 지정
		if (list.isEmpty()) { // 비어있을 경우 == 조회된 데이터가 없을 경우
			new MemberMenu().displayNoData("전체 조회 결과가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	public void selectByUserId(String userId) {
		
		Member m = new MemberDao().selectByUserId(userId);
		
		if (m == null) { // 검색 결과가 없을 경우 (조회된 데이터 없음)
			new MemberMenu().displayNoData(userId + "에 해당하는 검색 결과가 없습니다.");
		} else {
			new MemberMenu().displayMember(m);
		}
	}
	
	/**
	 * 사용자의 이름으로 키워드 검색 요청시 처리해주는 메소드
	 * @param keyword
	 */
	public void selectByUserName(String keyword) {
		
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		if (list.isEmpty()) { // 리스트가 비어있을 경우 => 검색 결과 없음
			new MemberMenu().displayNoData(keyword + "에 해당하는 검색 결과가 없습니다.");
		} else { // 리스에 뭐라고 있을 경우 => 검색 결과 있음
			new MemberMenu().displayMemberList(list);
		}
	}
	
	/**
	 * 정보 변경 요청 처리해주는 메소드
	 * @param userId : 변경하고자 하는 회원 아이디
	 * @param userPwd : 변경할 비밀번호
	 * @param email : 변경할 이메일
	 * @param phone : 변경할 전화번호
	 * @param address : 변경할 주소
	 */
	public void updateMember(String userId, String userPwd, String email, String phone, String address) {
		
		Member m = new Member();
		
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		
		int result = new MemberDao().updateMember(m);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원 정보가 변경되었습니다.");
		} else {
			new MemberMenu().displayFail("회원 정보 변경에 실패했습니다.");
		}
	}
	
	public void deleteMember(String userId) {
		
		int result = new MemberDao().deleteMember(userId);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원 정보가 삭제되었습니다.");
		} else {
			new MemberMenu().displayFail("회원 정보 삭제에 실패했습니다.");
		}
		
	}

}
