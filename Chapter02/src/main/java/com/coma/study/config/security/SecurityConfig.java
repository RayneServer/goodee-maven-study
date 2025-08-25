package com.coma.study.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// js, img, css 등의 static resource는 security에서 제외
	@Bean
	WebSecurityCustomizer customizer() {
		return null;
	}
	
}
