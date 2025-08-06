package com.coma.study.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.coma.study.common.page.Pager;

public interface BoardService {
	
	List<BoardVO> selectBoardList(Pager pager) throws Exception;
	BoardVO selectBoardDetail(BoardVO boardVO) throws Exception;
	int insertBoard(BoardVO boardVO, MultipartFile multipartFile) throws Exception;
	int updateBoard(BoardVO boardVO) throws Exception;
	int deleteBoard(Long boardNum) throws Exception;
}
