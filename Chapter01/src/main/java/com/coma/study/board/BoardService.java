package com.coma.study.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	public void boardList() {
		System.out.println("boardList");
	}
	
	public void boardDetail() {
		System.out.println("boardDetail");
	}
}
