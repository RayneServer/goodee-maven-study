package com.coma.study.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coma.study.board.BoardService;
import com.coma.study.board.BoardVO;

@Service
public class NoticeService implements BoardService {
	@Autowired
	private NoticeDAO noticeDAO;

	@Override
	public List<BoardVO> selectBoardList() throws Exception {
		return noticeDAO.selectBoardList();
	}

	@Override
	public BoardVO selectBoardDetail(BoardVO boardVO) throws Exception {
		return noticeDAO.selectBoardDetail(boardVO);
	}

}
