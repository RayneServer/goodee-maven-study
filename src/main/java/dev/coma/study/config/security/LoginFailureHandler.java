package dev.coma.study.config.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		// 로그인 실패 시 Security에서 Exception을 발생
		log.info("{}", exception);
		
		// InternalAuthenticationServiceException: ID가 존재하지 않는 경우
		// BadCredentialsException: 비밀번호가 일치하지 않는 경우
		// DisabledException: 유효하지 않은 사용자 (isEnabled = false)
		// AccountExpiredException: 유효 기간이 만료된 사용자 (isAccountNonExpired = false)
		// LockedException: 잠긴 사용자 (isAccountNonLocked = false)
		// CredentialsExpiredException: 자격 증명 유효 기간이 만료된 사용자 (isCredentialsNonExpired = false)
		
		String failMsg = "로그인할 수 없습니다. 관리자에게 문의해주세요.";
		if (exception instanceof BadCredentialsException) {
			failMsg = "비밀번호가 일치하지 않습니다.";
		} 
		
		if (exception instanceof InternalAuthenticationServiceException) {
			failMsg = "아이디가 존재하지 않습니다.";
		} 
		
		if (exception instanceof DisabledException) {
			failMsg = "유효하지 않은 계정입니다.";
		} 
		
		if (exception instanceof AccountExpiredException) {
			failMsg = "계정 유효 기간이 만료되었습니다.";
		} 
		
		if (exception instanceof LockedException) {
			failMsg = "잠긴 계정입니다.";
		} 
		
		if (exception instanceof CredentialsExpiredException) {
			failMsg = "비밀번호의 유효 기간이 만료되었습니다.";
		}
		
		
		failMsg = URLEncoder.encode(failMsg, StandardCharsets.UTF_8);
		response.sendRedirect("/member/login?failMsg=" + failMsg);
	}

}
