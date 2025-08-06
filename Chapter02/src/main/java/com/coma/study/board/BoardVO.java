package com.coma.study.board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

	// board Attach
	private BoardFileDTO boardFileDTO;
	
	// boardDate formatter
	private String boardDateToString;
	
	public void setBoardDate(LocalDateTime boardDate) {
		this.boardDate = boardDate;
		setBoardDateToString(boardDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
	}
}
