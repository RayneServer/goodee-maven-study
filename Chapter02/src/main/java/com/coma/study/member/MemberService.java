package com.coma.study.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.coma.study.common.file.FileManager;
import com.coma.study.product.ProductDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MemberService extends DefaultOAuth2UserService implements UserDetailsService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private FileManager fileManager;
	@Value("${app.upload}")
	private String upload;
	
	public MemberDTO selectMember(MemberDTO memberDTO) throws Exception {
		return memberDAO.selectMember(memberDTO);
	}

	public int joinMember(MemberDTO memberDTO, MultipartFile memberProfile) throws Exception {
		int result = memberDAO.insertMember(memberDTO);
		
		MemberProfileDTO memberProfileDTO = new MemberProfileDTO();
		memberProfileDTO.setMemberId(memberDTO.getMemberId());
		memberProfileDTO.setOriginName("default.jpg");
		memberProfileDTO.setSavedName("default.jpg");
		
		if (memberProfile != null && !memberProfile.isEmpty()) {
			String fileName = fileManager.fileSave(upload + "member", memberProfile);
			memberProfileDTO.setOriginName(memberProfile.getOriginalFilename());
			memberProfileDTO.setSavedName(fileName);
		}
		
		result = memberDAO.insertMemberProfile(memberProfileDTO);
		
		Map<String, Object> memberRoleDataMap = new HashMap<>();
		memberRoleDataMap.put("memberId", memberDTO.getMemberId());
		memberRoleDataMap.put("roleNum", 3);
		
		result = memberDAO.insertMemberRole(memberRoleDataMap);
		
		return result;
	}

	public int addProductInMyCart(Long productNum, MemberDTO memberDTO) throws Exception {
		Map<String, Object> cartMap = new HashMap<>();
		cartMap.put("productNum", productNum);
		cartMap.put("memberId", memberDTO.getMemberId());
		
		int result = memberDAO.insertCart(cartMap);
		return result;
	}

	public List<ProductDTO> getCartList(MemberDTO memberDTO) throws Exception {
		// TODO 페이징 처리
		return memberDAO.selectCartList(memberDTO);
	}

	public int removeProductFromMyCart(Map<String, Object> cartMap) throws Exception {
		return memberDAO.deleteCartList(cartMap);
	}
	
	public boolean checkMemberJoinError(MemberDTO memberDTO, BindingResult bindingResult) throws Exception {
		boolean errorCheck = true;
		errorCheck = !bindingResult.hasErrors(); // Annotation 검증
		
		// 사용자 정의: Password 일치 여부 검증
		if (!memberDTO.getMemberPw().equals(memberDTO.getMemberPwCheck())) {
			bindingResult.rejectValue("memberPwCheck", "member.password.notEqual");
			errorCheck = false;
		}
		
		MemberDTO selectDTO = memberDAO.selectMember(memberDTO);
		if (selectDTO != null) {
			bindingResult.rejectValue("memberId", "member.id.isExist");
			errorCheck = false;
		}
		
		return errorCheck;
	}

	public int updateMemberDetail(MemberDTO memberDTO, MultipartFile memberProfile) throws Exception {
		int result = memberDAO.updateMember(memberDTO);
		
		return result;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(username);
		try {
			memberDTO = memberDAO.selectMember(memberDTO);
		} catch (Exception e) { e.printStackTrace(); }
		
		return memberDTO;
	}

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		String sns = userRequest.getClientRegistration().getRegistrationId();
		OAuth2User user = null;
		
		if (sns.toUpperCase().equals("KAKAO")) {
			user = OAuthByKakao(userRequest);
		}
		
		return user;
	}
	
	private OAuth2User OAuthByKakao(OAuth2UserRequest userRequest) {
		OAuth2User user = super.loadUser(userRequest);
		
		Map<String, Object> properties = (Map<String, Object>) user.getAttributes().get("properties");
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(properties.get("nickname").toString());
		
		MemberProfileDTO memberProfileDTO = new MemberProfileDTO();
		memberProfileDTO.setSavedName(properties.get("profile_image").toString());
		memberDTO.setMemberProfileDTO(memberProfileDTO);
		
		List<RoleDTO> roleDTOs = new ArrayList<>();
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setRoleName("ROLE_MEMBER");
		roleDTOs.add(roleDTO);
		memberDTO.setRoleDTOs(roleDTOs);
		
		memberDTO.setAttributes(user.getAttributes());
		
		memberDTO.setSns(userRequest.getClientRegistration().getRegistrationId());
		memberDTO.setAccessToken(userRequest.getAccessToken().getTokenValue());
		
		return memberDTO;
	}

}
