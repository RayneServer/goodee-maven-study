package dev.coma.study.board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	private Long boardNum;
	@NotBlank
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private LocalDateTime boardDate;
	private Long boardHit;

	// board Attach
	private List<BoardFileDTO> boardFileDTOs;
	
	// boardDate formatter
	private String boardDateToString;
	
	public void setBoardDate(LocalDateTime boardDate) {
		this.boardDate = boardDate;
		setBoardDateToString(boardDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
	}
}
