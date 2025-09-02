package dev.coma.study.config.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
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
		String token = jwtTokenManager.createToken(authResult);
		
		Cookie cookie = new Cookie("accessToken", token);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		response.sendRedirect("/");
	}
	
}
