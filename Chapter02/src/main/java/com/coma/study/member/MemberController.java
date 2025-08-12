package com.coma.study.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("join")
	public void getMemberJoin() throws Exception {
		// No body
	}
	
	@PostMapping("join")
	public String postMemberJoin(MemberDTO memberDTO, MultipartFile memberProfile, Model model) throws Exception {
		int result = memberService.joinMember(memberDTO, memberProfile);
		
		String resultMsg = "회원가입 진행 중 오류가 발생했습니다.";
		String url = "/";
		
		if (result > 0) {
			resultMsg = "회원가입에 성공했습니다.";
		}
		
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
}
