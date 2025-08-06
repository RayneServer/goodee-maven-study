package com.coma.study.common.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileDTO {
	private Long fileNum;
	private String originName;
	private String savedName;
}
