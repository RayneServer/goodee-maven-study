package com.coma.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.coma.study.config.security.CustomLogoutSuccessHandler;
import com.coma.study.config.security.LoginFailureHandler;
import com.coma.study.config.security.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	@Autowired
	private LoginFailureHandler loginFailureHandler;
	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> {
			web.ignoring().requestMatchers("/css/**", "/js/**", "/vendor/**", "/files/**");
		};
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		security
						// CORS
						.cors((cors) -> cors.disable())
						
						// CSRF
						.csrf((csrf) -> csrf.disable())
						
						// Authorization
						.authorizeHttpRequests((auth) -> {
							auth
									.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")
									.requestMatchers("/product/add", "/product/update", "/product/delete").hasAnyRole("ADMIN", "MANAGER")
									.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").hasAnyRole("ADMIN", "MANAGER", "MEMBER")
									.anyRequest().permitAll()
									;
						})
						
						// Login Authentication
						.formLogin((form) -> {
							form
									.loginPage("/member/login")
									.usernameParameter("memberId")
									.passwordParameter("memberPw")
									.successHandler(loginSuccessHandler)
									.failureHandler(loginFailureHandler)
									;
						})
						
						// Logout
						.logout((out) -> {
							out
								 .logoutUrl("/member/logout")
								 .logoutSuccessHandler(customLogoutSuccessHandler)
								 .invalidateHttpSession(true)
								 .deleteCookies("JSESSIONID")
								 ;
						})
						
						;
		
		return security.build();
	}
}
