package com.coma.study.home;

import java.security.Principal;
import java.util.Enumeration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.coma.study.member.MemberDTO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping("/")
	public String home(HttpSession session) {
//		Enumeration<String> keys = session.getAttributeNames();
//		
//		while (keys.hasMoreElements()) {
//			log.info("key: {}", keys.nextElement());
//		}
//
//		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
//		log.info("{}", obj.getClass().getName());
//		
//		SecurityContextImpl impl = (SecurityContextImpl) obj;
//		Authentication authentication = impl.getAuthentication();
//		log.info("Auth: {}", authentication);
//		
//		// -----
//		
//		authentication = SecurityContextHolder.getContext().getAuthentication();
//		MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
//		log.info("{}", memberDTO.getMemberName());
		
		return "index";
	}
	
	@GetMapping("/info")
	public void info(Principal principal) {
		MemberDTO memberDTO = (MemberDTO) ((Authentication) principal).getPrincipal();
		// log.info("{}", memberDTO.getMemberName());
	}
}
