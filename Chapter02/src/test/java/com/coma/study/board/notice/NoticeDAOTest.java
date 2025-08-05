package com.coma.study.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
	void selectBoardListTest() throws Exception {
		List<BoardVO> resultList = noticeDAO.selectBoardList();
		
		assertNotEquals(0, resultList.size());
	}
	
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
		int result = 0;
		
		for (int i = 0; i < 105; i++) {
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setBoardTitle("title" + i);
			noticeVO.setBoardContent("냉무");
			noticeVO.setBoardWriter("ㅇㅇ");
			
			result = noticeDAO.insertBoard(noticeVO);
			
			if (i % 10 == 0) {
				Thread.sleep(500);
			}
		}
		
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
