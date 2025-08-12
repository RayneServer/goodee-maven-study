package com.coma.study.member;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	private String memberPhone;
	private LocalDate memberBirth;
	private Boolean memberNonExpired;
	private Boolean memberNonLocked;
	private Boolean credentialNonExpired;
	private Boolean enabled;
	
	// Member Profile
	private MemberProfileDTO memberProfileDTO;
	
	// Roles
	private List<RoleDTO> roleDTOs;
}
