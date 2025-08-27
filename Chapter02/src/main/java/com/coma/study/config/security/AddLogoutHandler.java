package com.coma.study.config.security;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.coma.study.member.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AddLogoutHandler implements LogoutHandler {
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String clientId;
	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String redirectUri;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		if (authentication instanceof OAuth2AuthenticationToken) {
			MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
			if (memberDTO.getSns().toUpperCase().equals("KAKAO")) {
				logoutByKakao(memberDTO);
			}
		}
	}
	
	private void logoutByKakao(MemberDTO memberDTO) {
		WebClient webClient = WebClient.create("https://kapi.kakao.com/v1/user/logout");
		
		Mono<String> response = webClient.post()
																	 .header("Authorization", "Bearer " +  memberDTO.getAccessToken())
																	 .retrieve()
																	 .bodyToMono(String.class)
																	 ;
		
		log.info("Logout: {}", response.block());
	}
	
	private void logoutWithKakao(MemberDTO memberDTO) {
		WebClient webClient = WebClient.create("https://kauth.kakao.com/oauth/logout");
		
		
		Mono<String> response = webClient.get()
																		 .uri("?client_id={clientId}&logout_redirect_uri={redirectUri}", clientId, "http://localhost/member/logout")
																		 .retrieve()
																		 .bodyToMono(String.class)
																		 ;
		
		log.info("Logout 2: {}", response.block());
	}

}
