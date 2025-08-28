package com.coma.study.board.notice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
	@Autowired
	private NoticeRepository noticeRepository;
	
	public List<NoticeDTO> getNoticeList() throws Exception {
		List<NoticeDTO> noticeList = noticeRepository.findAll();
		
		return noticeList;
	}
	
	public NoticeDTO getNoticeDetail(Long boardNum) throws Exception {
		Optional<NoticeDTO> result = noticeRepository.findById(boardNum);
		
		return result.orElseThrow();
	}
}
