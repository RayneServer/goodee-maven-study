package com.coma.study.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coma.study.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/account/*")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping("add")
	public String getAccountAdd(Long productNum, HttpSession session, Model model) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginMember");
		int result = accountService.createAccount(productNum, memberDTO);
		
		String resultMsg = "상품 가입 중 오류가 발생했습니다.";
		String resultIcon = "warning";
		
		if (result > 0) {
			resultMsg = "상품 가입에 성공했습니다!";
			resultIcon = "success";
		}
		
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("resultIcon", resultIcon);
		
		return "commons/result";
	}
	
	@PostMapping("add")
	public String postAccountAdd(Long[] cartCheck, HttpSession session, Model model) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginMember");
		int result = accountService.createAccountList(cartCheck, memberDTO);
		
		String resultMsg = "상품 가입 중 오류가 발생했습니다.";
		String resultIcon = "warning";
		
		if (result > 0) {
			resultMsg = "상품 가입에 성공했습니다!";
			resultIcon = "success";
			String url = "/member/cart/list";
			model.addAttribute("url", url);
		}
		
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("resultIcon", resultIcon);
		
		return "commons/result";
	}
}
