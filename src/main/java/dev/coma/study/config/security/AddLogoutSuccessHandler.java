package dev.coma.study.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import dev.coma.study.member.MemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AddLogoutSuccessHandler implements LogoutSuccessHandler {
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String clientId;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if (authentication instanceof OAuth2AuthenticationToken) {
			MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
			if (memberDTO.getSns().toUpperCase().equals("KAKAO")) {
				// response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id=" + clientId + "&logout_redirect_uri=http://localhost/");
			}
		}
		
		response.sendRedirect("/");
	}

}
