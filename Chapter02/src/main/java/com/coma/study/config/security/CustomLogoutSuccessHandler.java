package com.coma.study.config.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.coma.study.member.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
		
		request.setAttribute("resultMsg", memberDTO.getMemberName() + " 님 로그아웃 되었습니다.");
		request.setAttribute("resultIcon", "success");
		request.setAttribute("url", "/");
		
		request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp").forward(request, response);
	}

}
