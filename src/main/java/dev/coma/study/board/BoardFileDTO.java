package dev.coma.study.board;

import dev.coma.study.common.file.FileDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDTO extends FileDTO {
	private Long boardNum;
}
