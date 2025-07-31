package com.coma.study.board;

import java.util.List;

public interface BoardService {
	
	List<BoardVO> selectBoardList() throws Exception;
	BoardVO selectBoardDetail(BoardVO boardVO) throws Exception;
}
