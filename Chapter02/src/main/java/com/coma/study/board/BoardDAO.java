package com.coma.study.board;

public interface BoardDAO {
	
	BoardVO selectBoardDetail(BoardVO boardVO) throws Exception;
	int insertBoard(BoardVO boardVO) throws Exception;
	int updateBoard(BoardVO boardVO) throws Exception;
	int deleteBoard(Long boardNum) throws Exception;
}
