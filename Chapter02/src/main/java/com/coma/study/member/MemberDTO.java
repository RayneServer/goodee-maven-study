package com.coma.study.member;

import java.time.LocalDate;
import java.util.List;

import com.coma.study.member.validation.AddGroup;
import com.coma.study.member.validation.UpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
	@NotBlank(groups = AddGroup.class)
	private String memberId;
	@Size(min = 6, max = 8, groups = AddGroup.class)
	private String memberPw;
	private String memberPwCheck;
	@NotBlank(groups = {AddGroup.class, UpdateGroup.class})
	private String memberName;
	@Email(groups = {AddGroup.class, UpdateGroup.class})
	private String memberEmail;
	// @Pattern(regexp = "")
	private String memberPhone;
	@NotNull(groups = {AddGroup.class, UpdateGroup.class})
	@Past(groups = {AddGroup.class, UpdateGroup.class})
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
