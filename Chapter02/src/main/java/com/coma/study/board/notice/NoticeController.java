package com.coma.study.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/notice/*")
public class NoticeController {
	@Autowired
	private NoticeDAO noticeDAO;
	
	@GetMapping("list")
	public String noticeList() throws Exception {
		return "notice/list";
	}
	
	@GetMapping("add")
	public void insertNotice() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title");
		noticeVO.setBoardContent("content");
		noticeVO.setBoardWriter("writer");
		
		int result = noticeDAO.insertBoard(noticeVO);
	}
}
