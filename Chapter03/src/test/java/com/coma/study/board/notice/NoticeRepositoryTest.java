package com.coma.study.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.coma.study.board.BoardDTO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
// @Transactional
@Slf4j
class NoticeRepositoryTest {
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Test
	void test() {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardTitle("친구 결혼식 가는데 합의금 얼마 내야 하나요?");
		noticeDTO.setBoardContent("ㅇㅅㅇ");
		noticeDTO.setBoardWriter("ㅇㅇ");
		
		NoticeDTO result = noticeRepository.save(noticeDTO);
		
		assertNotNull(result);
	}
	
	@Test
	void test2() {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBoardNum(1L);
		
		boardDTO = noticeRepository.findById(boardDTO.getBoardNum()).get();
		
		log.info(boardDTO.getBoardTitle());
		
		assertNotNull(boardDTO);
	}

}
