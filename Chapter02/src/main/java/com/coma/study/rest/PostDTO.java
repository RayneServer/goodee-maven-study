package com.coma.study.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDTO {
	private Long userId;
	private Long id;
	private String title;
	private String body;
}
