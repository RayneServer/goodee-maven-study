package com.coma.study.config.security;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		String resultMsg = "로그인 중 오류가 발생했습니다.";
		String resultIcom = "warning";
		String url = "/member/login";
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		if (memberId == null || memberId.equals("")) {
			resultMsg = "아이디를 입력해주세요.";
		} else if (memberPw == null || memberPw.equals("")) {
			resultMsg = "비밀번호를 입력해주세요.";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			resultMsg = "아이디가 존재하지 않습니다.";
		} else if (exception instanceof BadCredentialsException) {
			resultMsg = "비밀번호가 일치하지 않습니다.";
		} else if (exception instanceof DisabledException) {
			resultMsg = "비활성화된 계정입니다.";
		}
		
		request.setAttribute("resultMsg", resultMsg);
		request.setAttribute("resultIcon", resultIcom);
		request.setAttribute("url", url);
		
		request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp").forward(request, response);
	}

}
