package com.coma.study.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String boardList() {
		System.out.println("boardList");
		
		return "board/list";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public void boardDetail() {
		System.out.println("boardDetail");
	}
}
