package com.coma.study.board;

public interface BoardDAO {
	
	int insertBoard(BoardVO boardVO) throws Exception;
	int updateBoard(BoardVO boardVO) throws Exception;
	int deleteBoard(Long boardNum) throws Exception;
}
