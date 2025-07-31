package com.coma.study.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coma.study.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class NoticeDAOTest {
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	void selectBoardDetailTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(2L);
		
		BoardVO result = noticeDAO.selectBoardDetail(noticeVO);
		log.info("result: {}", result);
		
		assertNotNull(result);
	}

	@Test
	void insertBoardTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title12");
		noticeVO.setBoardContent("content12");
		noticeVO.setBoardWriter("writer12");
	 
		int result = noticeDAO.insertBoard(noticeVO);
	 
		// 단정문
		assertEquals(1, result); 
	}
	 
	@Test
	void updateBoardTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(Long.valueOf("4"));
		noticeVO.setBoardTitle("title10");
		noticeVO.setBoardContent("content10");

		int result = noticeDAO.updateBoard(noticeVO);

		assertEquals(1, result);
	}
	
	@Test
	void deleteBoardTest() throws Exception {
		int result = noticeDAO.deleteBoard(Long.valueOf("1"));
		
		assertEquals(1, result);
	}
}
