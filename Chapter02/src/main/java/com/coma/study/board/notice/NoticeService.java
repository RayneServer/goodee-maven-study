package com.coma.study.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coma.study.board.BoardService;
import com.coma.study.board.BoardVO;
import com.coma.study.common.page.Pager;

@Service
public class NoticeService implements BoardService {
	@Autowired
	private NoticeDAO noticeDAO;

	@Override
	public List<BoardVO> selectBoardList(Pager pager) throws Exception {
		Long totalCount = noticeDAO.selectTotalCount();
		
		pager.initPage(totalCount);
		return noticeDAO.selectBoardList(pager);
	}

	@Override
	public BoardVO selectBoardDetail(BoardVO boardVO) throws Exception {
		return noticeDAO.selectBoardDetail(boardVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		return noticeDAO.insertBoard(boardVO);
	}

	@Override
	public int updateBoard(BoardVO boardVO) throws Exception {
		return noticeDAO.updateBoard(boardVO);
	}

	@Override
	public int deleteBoard(Long boardNum) throws Exception {
		return noticeDAO.deleteBoard(boardNum);
	}

}
