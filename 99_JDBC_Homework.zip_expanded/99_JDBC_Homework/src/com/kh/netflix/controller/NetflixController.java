package com.kh.netflix.controller;

import com.kh.netflix.model.dao.NetflixDao;
import com.kh.netflix.model.vo.Netflix;
import com.kh.netflix.view.NetflixMenu;

public class NetflixController {

	public void insertMember(String id, String level, String nickName, int point) {
		
		Netflix n = new Netflix(id, level, nickName, point);
		
		int result = new NetflixDao().insertMember(n);
		
		if (result > 0) {
			new NetflixMenu().displaySuccess("성공적으로 회원 추가되었습니다.");
		} else {
			new NetflixMenu().displayFail("회원 추가 실패했습니다.");
		}
	}
}
