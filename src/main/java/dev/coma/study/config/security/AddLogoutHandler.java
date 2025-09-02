package dev.coma.study.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import dev.coma.study.member.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AddLogoutHandler implements LogoutHandler {

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

}
