package com.coma.study.board;

import java.util.List;

public interface BoardDAO {
	
	List<BoardVO> selectBoardList() throws Exception;
	BoardVO selectBoardDetail(BoardVO boardVO) throws Exception;
	int insertBoard(BoardVO boardVO) throws Exception;
	int updateBoard(BoardVO boardVO) throws Exception;
	int deleteBoard(Long boardNum) throws Exception;
}
