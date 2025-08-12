package com.coma.study.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.coma.study.common.file.FileManager;

@Service
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
		
		memberDAO.insertMemberProfile(memberProfileDTO);
		
		return result;
	}
}
