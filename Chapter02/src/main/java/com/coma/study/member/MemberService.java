package com.coma.study.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.coma.study.common.file.FileManager;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private FileManager fileManager;
	@Value("${app.upload}")
	private String upload;

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

	public MemberDTO loginMember(MemberDTO memberDTO) throws Exception {
		return memberDAO.selectMember(memberDTO);
	}
}
