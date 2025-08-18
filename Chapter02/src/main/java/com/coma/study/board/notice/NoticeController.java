package com.coma.study.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coma.study.board.BoardFileDTO;
import com.coma.study.board.BoardVO;
import com.coma.study.common.page.Pager;
import com.coma.study.member.MemberDTO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/notice/*")
@Slf4j
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@Value("${board.notice}")
	private String name;
	
	@ModelAttribute("boardName")
	public String getBoardName() {
		return name;
	}
	
	@GetMapping("list")
	public String noticeList(Pager pager, Model model) throws Exception {
		// model: request와 비슷한 생명주기를 가진 객체 => 스프링에서 데이터 전송 시 사용
		List<BoardVO> noticeList = noticeService.selectBoardList(pager);
		
		model.addAttribute("boardList", noticeList);
		
		return "board/list";
	}
	
	@GetMapping("detail")
	public String noticeDetail(Long boardNum, Model model) throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(boardNum);
		
		BoardVO notice = noticeService.selectBoardDetail(noticeVO);
		model.addAttribute("board", notice);
		
		return "board/detail";
	}
	
	@GetMapping("add")
	public String noticeAdd(@ModelAttribute("boardVO") BoardVO noticeVO) throws Exception {
		return "board/add";
	}
	
	@PostMapping("add")
	public String noticeAdd(@Valid BoardVO noticeVO, BindingResult bindingResult, MultipartFile[] boardAttaches, HttpSession session) throws Exception {
		if (bindingResult.hasErrors()) return "board/add";
		
		noticeVO.setBoardWriter(((MemberDTO) session.getAttribute("loginMember")).getMemberId());
		int result = noticeService.insertBoard(noticeVO, boardAttaches);
		
		return "redirect:list";
	}
	
	@GetMapping("update")
	public String noticeUpdate(Long boardNum, Model model) throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(boardNum);
		
		BoardVO notice = noticeService.selectBoardDetail(noticeVO);
		model.addAttribute("board", notice);
		
		return "board/add";
	}
	
	@PostMapping("update")
	public String noticeUpdate(NoticeVO noticeVO, MultipartFile[] boardAttaches, Model model) throws Exception {
		int result = noticeService.updateBoard(noticeVO, boardAttaches);
		
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
	
	@PostMapping("fileDelete")
	@ResponseBody
	public int postFileDelete(BoardFileDTO boardFileDTO, Model model) throws Exception {
		int result = noticeService.deleteBoardFile(boardFileDTO);

		return result;
	}
	
	@GetMapping("fileDownload")
	public String getFileDownload(BoardFileDTO boardFileDTO, Model model) throws Exception {
		boardFileDTO = noticeService.selectBoardAttach(boardFileDTO);
		model.addAttribute("boardFile", boardFileDTO);
		
		return "fileDownloadView";
	}
	
	@PostMapping("boardFile")
	@ResponseBody
	public String postBoardFile(MultipartFile boardFile) throws Exception {
		return noticeService.saveBoardFile(boardFile);
	}
	
	@PostMapping("boardFileDelete")
	@ResponseBody
	public boolean postBoardFileDelete(String fileName) throws Exception {
		return noticeService.unsaveBoardFile(fileName);
	}
}
