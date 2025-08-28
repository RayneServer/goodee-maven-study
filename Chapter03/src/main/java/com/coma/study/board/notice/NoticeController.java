package com.coma.study.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coma.study.board.BoardDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/notice/**")
@Slf4j
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@GetMapping
	public List<NoticeDTO> getNotice() throws Exception {
		List<NoticeDTO> noticeList = noticeService.getNoticeList();
		
		return noticeList;
	}
	
	@GetMapping("{boardNum}")
	public BoardDTO getNoticeDetail(@PathVariable("boardNum") Long boardNum) throws Exception {
		BoardDTO boardDTO = noticeService.getNoticeDetail(boardNum);
		
		return boardDTO;
	}
	
	@PostMapping
	public void postNotice() {
		
	}
	
	@PutMapping("{boardNum}")
	public void putNotice(@PathVariable("boardNum") Long boardNum) {
		
	}
	
	@DeleteMapping("{boardNum}")
	public void deleteNotice(@PathVariable("boardNum") Long boardNum) {
		
	}
}
