package com.coma.study.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coma.study.board.BoardVO;

@Controller
@RequestMapping(value = "/notice/*")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("list")
	public String noticeList(Model model) throws Exception {
		// model: request와 비슷한 생명주기를 가진 객체 => 스프링에서 데이터 전송 시 사용
		List<BoardVO> noticeList = noticeService.selectBoardList();
		model.addAttribute("noticeList", noticeList);
		
		return "notice/list";
	}
	
	@GetMapping("detail")
	public String noticeDetail(Long boardNum, Model model) throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardNum(boardNum);
		
		BoardVO notice = noticeService.selectBoardDetail(boardVO);
		model.addAttribute("notice", notice);
		
		return "notice/detail";
	}
	
	@GetMapping("add")
	public void insertNotice() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title");
		noticeVO.setBoardContent("content");
		noticeVO.setBoardWriter("writer");
		
		// int result = noticeDAO.insertBoard(noticeVO);
	}
}
