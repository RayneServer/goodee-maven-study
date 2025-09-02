package com.coma.study.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.coma.study.board.BoardVO;
import com.coma.study.common.page.Pager;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Transactional
class NoticeDAOTest {
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	void selectBoardListTest() throws Exception {
		Pager pager = new Pager();
		List<BoardVO> resultList = noticeDAO.selectBoardList(pager);
		
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
		
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("delete");
		noticeVO.setBoardContent("난 지워져야 해");
		noticeVO.setBoardWriter("ㅇㅇ");
		
		result = noticeDAO.insertBoard(noticeVO);

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
