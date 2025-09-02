package dev.coma.study.board;

import java.util.List;

import dev.coma.study.common.page.Pager;

public interface BoardDAO {
	
	List<BoardVO> selectBoardList(Pager pager) throws Exception;
	BoardVO selectBoardDetail(BoardVO boardVO) throws Exception;
	Long selectTotalCount(Pager pager) throws Exception;
	BoardFileDTO selectBoardAttach(BoardFileDTO boardFileDTO) throws Exception;
	int insertBoard(BoardVO boardVO) throws Exception;
	int insertBoardAttach(BoardFileDTO boardFileDTO) throws Exception;
	int updateBoard(BoardVO boardVO) throws Exception;
	int deleteBoard(Long boardNum) throws Exception;
	int deleteBoardAttach(Long boardNum) throws Exception;
	int deleteBoardAttachOne(Long fileNum) throws Exception;
}
