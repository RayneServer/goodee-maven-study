package com.coma.study.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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
		security.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
						// 권한 설정
						.authorizeHttpRequests(auth -> {
							auth.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")
									.requestMatchers("/product/add", "/product/update", "/product/delete").hasAnyRole("ADMIN", "MANAGER")
									// .requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").access("hasRole('ROLE_MEMBER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIM')");
									.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").authenticated()
									.anyRequest().permitAll();
						})
						// 사용자 정의 로그인 설정
						.formLogin(form -> {
							form.loginPage("/member/login")
									.usernameParameter("memberId")
									.passwordParameter("memberPw")
									.defaultSuccessUrl("/")
									.failureUrl("/member/login");
						})
						// 로그아웃 설정
						.logout(logout -> {
							logout.logoutUrl("/member/logout")
										// .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"));
										.invalidateHttpSession(true)
										.deleteCookies("JSESSIONID")
										.logoutSuccessUrl("/");	
						})
						;
		
		return security.build();
	}
}
