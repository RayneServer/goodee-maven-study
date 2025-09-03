package dev.coma.study.config.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 로그인 요청 시 실행하는 필터
// username, password를 꺼내 UserDetailService의 loadUserByUsername 호출
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private JwtTokenManager jwtTokenManager;
	
	public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenManager = jwtTokenManager;
		
		this.setFilterProcessesUrl("/member/loginProcess");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		// UsernamePasswordAuthenticationToken에서 UserDetailService의 loadUserByUsername 호출 
		// Password가 일치하는지 판별 => 해당 Authentication 객체를 SecurityContextHolder에 담아줌
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(memberId, memberPw);

		return authenticationManager.authenticate(token);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		// 로그인 성공 시 호출 => 사용자의 정보로 토큰 생성
		String accessToken = jwtTokenManager.makeAccessToken(authResult);
		String refreshToken = jwtTokenManager.makeRefreshToken(authResult);
		
		Cookie cookie = new Cookie("accessToken", accessToken);
		cookie.setPath("/");
		cookie.setMaxAge(180);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		
		cookie = new Cookie("refreshToken", refreshToken);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 10);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		
		response.sendRedirect("/");
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		String failMsg = "로그인할 수 없습니다. 관리자에게 문의해주세요.";
		if (failed instanceof BadCredentialsException) {
			failMsg = "비밀번호가 일치하지 않습니다.";
		} 
		
		if (failed instanceof InternalAuthenticationServiceException) {
			failMsg = "아이디가 존재하지 않습니다.";
		} 
		
		if (failed instanceof DisabledException) {
			failMsg = "유효하지 않은 계정입니다.";
		} 
		
		if (failed instanceof AccountExpiredException) {
			failMsg = "계정 유효 기간이 만료되었습니다.";
		} 
		
		if (failed instanceof LockedException) {
			failMsg = "잠긴 계정입니다.";
		} 
		
		if (failed instanceof CredentialsExpiredException) {
			failMsg = "비밀번호의 유효 기간이 만료되었습니다.";
		}
		
		failMsg = URLEncoder.encode(failMsg, StandardCharsets.UTF_8);
		response.sendRedirect("/member/login?failMsg=" + failMsg);
	}
	
}
