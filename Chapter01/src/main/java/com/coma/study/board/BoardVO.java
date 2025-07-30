package com.coma.study.board;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardVO {
	private Integer num;
	private String name;
	private String title;
	
	public void setKind(String kind) {
		this.title = kind;
	}
}
