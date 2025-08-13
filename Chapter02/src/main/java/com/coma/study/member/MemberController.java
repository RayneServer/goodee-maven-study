package com.coma.study.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coma.study.product.ProductDTO;

import jakarta.servlet.http.HttpSession;
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
	
	@GetMapping("login")
	public void getMemberLogin() throws Exception {
		// No body
	}
	
	@PostMapping("login")
	public String postMemberLogin(MemberDTO memberDTO, HttpSession session, Model model) throws Exception {
		MemberDTO result = memberService.loginMember(memberDTO);
		
		String resultMsg = "아이디 또는 비밀번호가 일치하지 않습니다.";
		String url = "login";
		
		if (result != null) {
			session.setAttribute("loginMember", result);
			resultMsg = result.getMemberName() + " 님 환영합니다!";
			url = "/";
		}
		
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
	
	@GetMapping("logout")
	public String getMemberLogout(HttpSession session, Model model) throws Exception {
		session.removeAttribute("loginMember");
		
		String resultMsg = "로그아웃 되었습니다.";
		String url = "/";
		
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
	
	@GetMapping("detail")
	public void getMemberDetail() throws Exception {
		// Empty
	}
	
	@GetMapping("cart/list")
	public void getMemberCartList(HttpSession session, Model model) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginMember");
		List<ProductDTO> productList = memberService.getCartList(memberDTO);
		
		model.addAttribute("productList", productList);
	}
	
	@GetMapping("cart/add")
	@ResponseBody
	public boolean getMemberCartAdd(Long productNum, HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginMember");
		int result = memberService.addProductInMyCart(productNum, memberDTO);
		
		if (result > 0) return true;
		else return false;
	}
}
