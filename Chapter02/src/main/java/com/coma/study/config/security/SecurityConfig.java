package com.coma.study.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.coma.study.member.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	@Autowired
	private LoginFailureHandler loginFailureHandler;
	@Autowired
	private AddLogoutHandler addLogoutHandler;
	@Autowired
	private AddLogoutSuccessHandler addLogoutSuccessHandler;
	@Autowired
	private MemberService memberService;

	// js, img, css 등 static resource를 security에서 제외
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		
		// web => WebSecurity type
		return web -> {
			web.ignoring().requestMatchers("/css/**", "/js/**", "/vendor/**", "/files/**");
		};
	}

	// 인증 및 권한 설정
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		security.cors(cors -> cors.disable())
						.csrf(csrf -> csrf.disable())
						
						// 권한 설정
						.authorizeHttpRequests(auth -> {
							auth.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")
									.requestMatchers("/product/add", "/product/update", "/product/delete").hasAnyRole("ADMIN", "MANAGER")
									// .requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").access("hasRole('ROLE_MEMBER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIM')");
									.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").authenticated()
									.anyRequest().permitAll();
						})
						
						// 사용자 정의 로그인 설정 (개발자가 아니라 Spring Security Filter에서 로그인을 검증) => 하지만 기본 설정은 다 해줘야 함
						.formLogin(form -> {
							form.loginPage("/member/login")
									.usernameParameter("memberId") // ID가 username이 아닌 경우
									.passwordParameter("memberPw") // PW가 password가 아닌 경우
									// .defaultSuccessUrl("/") // 성공 시 이동할 url의 기본값
									// .successForwardUrl(null) // Forward로 진행
									.successHandler(loginSuccessHandler) // 직접 핸들러를 만들 경우
									// .failureUrl("/member/login")
									.failureHandler(loginFailureHandler)
									;
						})
						
						// 로그아웃 설정 (개발자가 아니라 Spring Security Filter에서 로그아웃 처리)
						.logout(logout -> {
							logout.logoutUrl("/member/logout")
										.addLogoutHandler(addLogoutHandler)
										.logoutSuccessHandler(addLogoutSuccessHandler)
										// .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"));
										.invalidateHttpSession(true) // 세션을 만료시킴
										.deleteCookies("JSESSIONID")
										// .logoutSuccessUrl("/")
										;
						})
						
						// 자동 로그인 설정
						.rememberMe((remember) -> {
							remember.rememberMeParameter("rememberMe")
											.tokenValiditySeconds(60)
											.key("rememberKey")
											.userDetailsService(memberService)
											.authenticationSuccessHandler(loginSuccessHandler)
											.useSecureCookie(false)
											;
						})
						
						// 다중 세션 관리
						.sessionManagement((session) -> {
							session.maximumSessions(1)
										 .maxSessionsPreventsLogin(false)// false: 이전 사용자 로그아웃 true: 로그인 차단
										 .expiredUrl("/")
										 ;
						})
						;
		
		return security.build();
	}
	
}
