package com.coma.study.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coma.study.member.validation.AddGroup;
import com.coma.study.member.validation.UpdateGroup;
import com.coma.study.product.ProductDTO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("join")
	public void getMemberJoin(MemberDTO memberDTO) throws Exception {
		// No body
	}
	
	@PostMapping("join")
	public String postMemberJoin(@Validated({AddGroup.class, UpdateGroup.class}) MemberDTO memberDTO, BindingResult bindingResult, MultipartFile memberProfile, Model model) throws Exception {
		if (!memberService.checkMemberJoinError(memberDTO, bindingResult)) {
			return "member/join";
		}
		
		memberDTO.setMemberPw(passwordEncoder.encode(memberDTO.getMemberPw()));
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
	
	@GetMapping("detail")
	public void getMemberDetail() throws Exception {
		// Empty
	}
	
	@GetMapping("update")
	public void getMemberUpdate(HttpSession session, Model model) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginMember");
		
		model.addAttribute("memberDTO", memberDTO);
	}
	
	@PostMapping("update")
	public String postMemberUpdate(@Validated(UpdateGroup.class) MemberDTO memberDTO, BindingResult bindingResult, MultipartFile memberProfile, HttpSession session, Model model) throws Exception {
		if (bindingResult.hasErrors()) {
			return "member/update";
		}
		
		int result = memberService.updateMemberDetail(memberDTO, memberProfile);
		
		String resultMsg = "회원정보 수정 중 오류가 발생했습니다.";
		String resultIcon = "warning";
		
		if (result > 0) {
			resultMsg = "회원정보가 수정되었습니다.";
			resultIcon = "success";
			String url = "detail";
			model.addAttribute("url", url);
			
			memberDTO.setMemberPw(((MemberDTO) session.getAttribute("loginMember")).getMemberPw());
			memberDTO = memberService.loginMember(memberDTO);
			session.setAttribute("loginMember", memberDTO);
		}
		
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("resultIcon", resultIcon);
		
		return "commons/result";
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
	
	@PostMapping("cart/delete")
	public String postMemberCartDelete(Long[] cartCheck, HttpSession session, Model model) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginMember");
		
		Map<String, Object> cartMap = new HashMap<>();
		cartMap.put("memberId", memberDTO.getMemberId());
		cartMap.put("productNums", cartCheck);
		
		int result = memberService.removeProductFromMyCart(cartMap);
		
		String resultMsg = "장바구니에서 삭제 중 오류가 발생했습니다.";
		String resultIcon = "warning";
		
		if (result > 0) {
			resultMsg = "장바구니에서 삭제했습니다.";
			resultIcon = "success";
			String url = "list";
			model.addAttribute("url", url);
		}
		
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("resultIcon", resultIcon);
		
		return "commons/result";
	}
}
