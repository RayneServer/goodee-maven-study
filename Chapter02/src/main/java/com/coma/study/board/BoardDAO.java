package com.coma.study.board;

import java.util.List;

import com.coma.study.common.page.Pager;

public interface BoardDAO {
	
	List<BoardVO> selectBoardList(Pager pager) throws Exception;
	BoardVO selectBoardDetail(BoardVO boardVO) throws Exception;
	Long selectTotalCount(Pager pager) throws Exception;
	int insertBoard(BoardVO boardVO) throws Exception;
	int insertBoardAttach(BoardFileDTO boardFileDTO) throws Exception;
	int updateBoard(BoardVO boardVO) throws Exception;
	int deleteBoard(Long boardNum) throws Exception;
}
