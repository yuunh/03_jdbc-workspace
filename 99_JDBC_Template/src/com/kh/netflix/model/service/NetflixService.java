package com.kh.netflix.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.netflix.common.JDBCTemplate;
import com.kh.netflix.model.dao.NetflixDao;
import com.kh.netflix.model.vo.Netflix;

public class NetflixService {

	public int insertMember(Netflix n) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NetflixDao().insertMember(conn, n);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public ArrayList<Netflix> selectList() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Netflix> list = new NetflixDao().selectList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public Netflix selectId(String Id) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Netflix n = new NetflixDao().selectId(conn, Id);
		
		JDBCTemplate.close(conn);
		
		return n;
	}
	
	public ArrayList<Netflix> selectNickName(String keyword) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Netflix> list = new NetflixDao().selectNickName(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int updateMember(Netflix n) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NetflixDao().updateMember(conn, n);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int deleteMember(String Id) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NetflixDao().deleteMember(conn, Id);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
}
