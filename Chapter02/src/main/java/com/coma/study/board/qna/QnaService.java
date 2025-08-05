package com.coma.study.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coma.study.board.BoardService;
import com.coma.study.board.BoardVO;

@Service
public class QnaService implements BoardService {
	@Autowired
	private QnaDAO qnaDAO;

	@Override
	public List<BoardVO> selectBoardList() throws Exception {
		return qnaDAO.selectBoardList();
	}

	@Override
	public BoardVO selectBoardDetail(BoardVO boardVO) throws Exception {
		return qnaDAO.selectBoardDetail(boardVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		int result = qnaDAO.insertBoard(boardVO);
		result = qnaDAO.updateRef(boardVO);
		
		return result;
	}
	
	public int insertReply(QnaDTO qnaDTO) throws Exception {
		QnaDTO parent = (QnaDTO) qnaDAO.selectBoardDetail(qnaDTO);
		qnaDTO.setBoardRef(parent.getBoardRef());
		qnaDTO.setBoardStep(parent.getBoardStep() + 1);
		qnaDTO.setBoardDepth(parent.getBoardDepth() + 1);
		
		int result = qnaDAO.updateReplyStep(parent);
		result = qnaDAO.insertBoard(qnaDTO);
		
		return result;
	}

	@Override
	public int updateBoard(BoardVO boardVO) throws Exception {
		return 0;
	}

	@Override
	public int deleteBoard(Long boardNum) throws Exception {
		return 0;
	}

}
