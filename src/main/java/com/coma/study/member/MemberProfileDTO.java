package com.coma.study.member;

import com.coma.study.common.file.FileDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberProfileDTO extends FileDTO {
	private String memberId;
}
