package com.kh.netflix.controller;

import java.util.ArrayList;

import com.kh.netflix.model.dao.NetflixDao;
import com.kh.netflix.model.vo.Netflix;
import com.kh.netflix.view.NetflixMenu;
import com.kh.view.MemberMenu;

public class NetflixController {

	/**
	 * 회원 추가 요청을 처리해주는 메소드
	 * @param id
	 * @param level
	 * @param nickName
	 * @param point
	 */
	public void insertMember(String id, String level, String nickName, int point) {
		
		Netflix n = new Netflix(id, level, nickName, point);
		
		int result = new NetflixDao().insertMember(n);
		
		if (result > 0) {
			new NetflixMenu().displaySuccess("성공적으로 회원 추가되었습니다.");
		} else {
			new NetflixMenu().displayFail("회원 추가 실패했습니다.");
		}
	}
	
	/**
	 * 회원 목록 조회 요청을 처리해주는 메소드
	 */
	public void selectList() {
		
		ArrayList<Netflix> list = new NetflixDao().selectList();
		
		if (list.isEmpty()) {
			new NetflixMenu().displayNoData("목록 조회 결과가 없습니다.");
		} else {
			new NetflixMenu().displayNetflixList(list);
		}
	}
	
	/**
	 * 사용자의 아이디로 검색 요청시 처리해주는 메소드
	 * @param Id
	 */
	public void selectId(String Id) {
		
		Netflix n = new NetflixDao().selectId(Id);
		
		if (n == null) {
			new NetflixMenu().displayNoData(Id + "에 해당하는 검색 결과가 없습니다.");
		} else {
			new NetflixMenu().displayNetflix(n);
		}
	}
	
	/**
	 * 사용자의 닉네임으로 키워드 검색 요청시 처리해주는 메소드
	 * @param keyword
	 */
	public void selectNickName(String keyword) {
		
		ArrayList<Netflix> list = new NetflixDao().selectNickName(keyword);
		
		if (list.isEmpty()) {
			new NetflixMenu().displayNoData(keyword + "에 해당하는 검색 결과가 없습니다.");
		} else {
			new NetflixMenu().displayNetflixList(list);
		}
	}
	
	/**
	 * 정보 수정 요청 처리해주는 메소드
	 * @param id
	 * @param level
	 * @param nickName
	 * @param point
	 */
	public void updateMember(String id, String level, String nickName, int point) {
		
		Netflix n = new Netflix();
		
		n.setId(id);
		n.setLevel(level);
		n.setNickName(nickName);
		n.setPoint(point);
		
		int result = new NetflixDao().updateMember(n);
		
		if (result > 0) {
			new NetflixMenu().displaySuccess("성공적으로 회원 정보가 수정되었습니다.");
		} else {
			new NetflixMenu().displayFail("회원 정보 수정에 실패했습니다.");
		}
	}
	
	/**
	 * 정보 삭제 요청 처리해주는 메소드
	 * @param id
	 */
	public void deleteMember(String id) {
		
		int result = new NetflixDao().deleteMember(id);
		
		if (result > 0) {
			new NetflixMenu().displaySuccess("성공적으로 회원정보가 삭제되었습니다.");
		} else {
			new NetflixMenu().displayFail("회원 정보 삭제에 실패했습니다.");
		}
	}
}
