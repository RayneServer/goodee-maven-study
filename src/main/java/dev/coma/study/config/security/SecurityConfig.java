package dev.coma.study.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import dev.coma.study.member.MemberService;

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
	@Autowired
	private JwtTokenManager jwtTokenManager;
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

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
						// Token 인증 방식에서는 사용하지 않음
						.formLogin(form -> {
							form.disable()
									;
						})
						
						// 로그아웃 설정 (개발자가 아니라 Spring Security Filter에서 로그아웃 처리)
						.logout((out) -> {
							out.logoutUrl("/member/logout")
								 .invalidateHttpSession(true)
								 .deleteCookies("accessToken")
								 .logoutSuccessUrl("/")
								 ;
						})
						
						// Token 인증 방식
						.httpBasic((basic) -> {
							basic.disable()
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
						// Token 인증 방식이기 때문에 session을 사용하지 않음
						.sessionManagement((session) -> {
							session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
										 ;
						})
						
						// 토큰 생성 방식으로 로그인
						.addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
						.addFilter(new JwtLoginFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
						
						// OAuth2 로그인 관리
						.oauth2Login((login) -> {
							login.userInfoEndpoint((point) -> {
								point.userService(memberService)
										 ;
							});
						})
						
						;
		
		return security.build();
	}
	
}
