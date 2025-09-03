package dev.coma.study.config.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.ExpiredJwtException;
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
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("accessToken")) {
					token = cookie.getValue();
					break;
				}
			}
		}
		
		if (token != null && token.length() != 0) {
			try {
				Authentication authentication = jwtTokenManager.getAuthenticationByToken(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				
				// SecurityException || MalformedException || SignatureException => 유효하지 않은 JWT 서명
				// ExpiredJwtException => 기간 만료 Token
				// UnsupportedJwtException => 지원되지 않는 Token
				// IllegalArgumentException => 잘못된 Token
				
				if (e instanceof ExpiredJwtException) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("refreshToken")) {
							String refreshToken = cookie.getValue();
							
							try {
								Authentication authentication = jwtTokenManager.getAuthenticationByToken(refreshToken);
								SecurityContextHolder.getContext().setAuthentication(authentication);
								
								String newAccessToken = jwtTokenManager.makeAccessToken(authentication);
								Cookie newCookie = new Cookie("accessToken", newAccessToken);
								newCookie.setPath("/");
								newCookie.setMaxAge(180);
								newCookie.setHttpOnly(true);
								response.addCookie(newCookie);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				} else {					
					e.printStackTrace();
				}
			}			
		}
		
		chain.doFilter(request, response);
	}
	
}
