package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.model.dao.GameDao;
import com.kh.model.vo.Game;

public class GameService {
	
	Connection conn = getConnection();

	public ArrayList<Game> gameList() {
		
		ArrayList<Game> list = new GameDao().selectGame(conn);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Game> searchName(String pName) {
		
		ArrayList<Game> list = new GameDao().searchName(conn, pName);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Game> searchPrice(int lowestPrice, int highestPrice) {
		
		ArrayList<Game> list = new GameDao().searchPrice(conn, lowestPrice, highestPrice);
		
		close(conn);
		
		return list;
	}
	
}
