package com.coma.study.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(boardNum);
		
		BoardVO notice = noticeService.selectBoardDetail(noticeVO);
		model.addAttribute("notice", notice);
		
		return "notice/detail";
	}
	
	@GetMapping("add")
	public String noticeAdd() throws Exception {
		return "notice/add";
	}
	
	@PostMapping("add")
	public String noticeAdd(NoticeVO noticeVO) throws Exception {
		int result = noticeService.insertBoard(noticeVO);
		
		return "redirect:list";
	}
	
	@GetMapping("update")
	public String noticeUpdate(Long boardNum, Model model) throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(boardNum);
		
		BoardVO notice = noticeService.selectBoardDetail(noticeVO);
		model.addAttribute("notice", notice);
		
		return "notice/add";
	}
	
	@PostMapping("update")
	public String noticeUpdate(NoticeVO noticeVO, Model model) throws Exception {
		int result = noticeService.updateBoard(noticeVO);
		
		String resultMsg = "수정 중 오류가 발생했습니다.";
		String url = "detail?boardNum=" + noticeVO.getBoardNum();
		
		if (result > 0) {
			resultMsg = "수정에 성공했습니다.";
		}
		
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("url", url);
		
		return "commons/result";
		// return "redirect:detail?boardNum=" + noticeVO.getBoardNum();
	}
	
	@PostMapping("delete")
	public String noticeDelete(Long boardNum, Model model) throws Exception {
		int result = noticeService.deleteBoard(boardNum);
		
		String resultMsg = "삭제 중 오류가 발생했습니다.";
		String url = "list";
		
		if (result > 0) {
			resultMsg = "삭제에 성공했습니다.";
		}
		
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
}
