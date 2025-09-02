package dev.coma.study.member;

import dev.coma.study.common.file.FileDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberProfileDTO extends FileDTO {
	private String memberId;
}
