package com.coma.study.board.qna;

import com.coma.study.board.BoardVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaDTO extends BoardVO {
	private Long boardRef;
	private Long boardStep;
	private Long boardDepth;
}
