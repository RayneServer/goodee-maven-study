package com.coma.study.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PhotoDTO {
	private Long albumId;
	private Long id;
	private String title;
	private String url;
	private String thumbnailUrl;
}
