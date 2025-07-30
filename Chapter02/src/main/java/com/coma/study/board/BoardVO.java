package com.coma.study.board;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	private Long boardNum;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private LocalDateTime boardDate;
	private Long boardHit;
}
