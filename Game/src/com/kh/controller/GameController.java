package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.GameService;
import com.kh.model.vo.Game;
import com.kh.view.GameMenu;

public class GameController {

	public void selectGame() {
		
		ArrayList<Game> list = new GameService().gameList();
		
		if (list.isEmpty()) {
			new GameMenu().displayNoDate("\n== 조회된 결과가 없습니다. ==");
		} else {
			new GameMenu().displayGameList(list);
		}
	}
	
	public void searchName(String pName) {
		
		ArrayList<Game> list = new GameService().searchName(pName);
		
		if (list.isEmpty()) {
			new GameMenu().displayNoDate("\n== " + pName + "에 해당하는 게임이 없습니다. ==");
		} else {
			new GameMenu().displayGameList(list);
		}
	}
	
	public void searchPrice(int lowestPrice, int highestPrice) {
		
		ArrayList<Game> list = new GameService().searchPrice(lowestPrice, highestPrice);
		
		if (list.isEmpty()) {
			new GameMenu().displayNoDate("\n== 조회된 결과가 없습니다. ==");
		} else {
			new GameMenu().displayGameList(list);
		}
	}
	
	public void insertGame(String pName, int price) {
		
		Game g = new Game(pName, price);
		
		int result = new GameService().insertGame(g);
		
		if (result > 0) {
			new GameMenu().displaySuccess("\n== 게임이 추가되었습니다. ==");
		} else {
			new GameMenu().displayFail("\n== 게임 추가에 실패했습니다. ==");
		}
	}
	
	public void updateGaem(String pName, int price) {
		
		Game g = new Game(pName, price);
		
		int result = new GameService().updateGame(g);
		
		if (result > 0) {
			new GameMenu().displaySuccess("\n== 게임이 수정되었습니다. ==");
		} else {
			new GameMenu().displayFail("\n== 게임 수정에 실패했습니다. ==");
		}
		
	}
}
