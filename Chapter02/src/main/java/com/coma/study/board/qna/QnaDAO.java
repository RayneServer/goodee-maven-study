package com.coma.study.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.coma.study.board.BoardDAO;
import com.coma.study.board.BoardVO;

@Mapper
public interface QnaDAO extends BoardDAO {

	int updateRef(BoardVO boardVO) throws Exception;
	int updateReplyStep(QnaDTO qnaDTO) throws Exception;
}
