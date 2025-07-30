package com.coma.study.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {
	@Autowired
	private BoardService boardService; // 객체 생성은 Service의 annotation이 담당

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String boardList() {
		System.out.println("boardList");
		
		return "board/list";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public void boardDetail(BoardVO boardVO) { // primitive type보다는 Wrapper class로 받자
		// String num = request.getParameter("num");
		// int n = Integer.parseInt(num);
		
		// BoardVO boardVO = new BoardVO();
		// boardVO.setName(kind);
		// boardVO.setNum(num);
		
		System.out.println("boardDetail: " + boardVO);
		// return "board/detail"; <= return이 없으면 들어온 url을 기준으로 판단
	}
}
