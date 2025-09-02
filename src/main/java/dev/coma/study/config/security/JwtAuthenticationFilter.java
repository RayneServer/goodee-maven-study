package dev.coma.study.config.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

	private JwtTokenManager jwtTokenManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenManager tokenManager) {
		super(authenticationManager);
		
		this.jwtTokenManager = tokenManager;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 토큰 검증
		Cookie[] cookies = request.getCookies();
		String token = "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("accessToken")) {
				token = cookie.getValue();
				break;
			}
		}
		System.out.println("Token: " + token);
		if (token != null && token.length() != 0) {
			try {
				Authentication authentication = jwtTokenManager.getAuthenticationByToken(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				System.out.println(authentication.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		chain.doFilter(request, response);
	}
	
}
