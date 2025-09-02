package com.coma.study.config.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.coma.study.member.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	// 로그인이 성공했을 때 실행하는 핸들러
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String rememberId = request.getParameter("rememberId");
		
		if (rememberId != null && rememberId.equals("1")) {
			Cookie cookie = new Cookie("rememberId", request.getParameter("memberId"));
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(cookie);
		}
		
		if (rememberId == null) {
			Cookie cookie = new Cookie("rememberId", null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		response.sendRedirect("/");
	}
	
}
