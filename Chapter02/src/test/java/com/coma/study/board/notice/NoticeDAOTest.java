package com.coma.study.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeDAOTest {
	@Autowired
	private NoticeDAO noticeDAO;

	@Test
	void insertBoardTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title8");
		noticeVO.setBoardContent("content8");
		noticeVO.setBoardWriter("writer8");
	 
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
