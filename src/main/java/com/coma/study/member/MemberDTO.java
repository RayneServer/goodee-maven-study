package com.coma.study.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.coma.study.member.validation.AddGroup;
import com.coma.study.member.validation.UpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO implements UserDetails, OAuth2User {
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
	
	private Map<String, Object> attributes;
	
	private String accessToken;
	private String sns;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantList = new ArrayList<>();
		
		for (RoleDTO roleDTO : roleDTOs) {
			grantList.add(new SimpleGrantedAuthority(roleDTO.getRoleName()));
		}
		
		return grantList;
	}

	@Override
	public String getPassword() {
		return memberPw;
	}

	@Override
	public String getUsername() {
		return memberId;
	}

	@Override
	public String getName() {
		return memberId;
	}
	
}
