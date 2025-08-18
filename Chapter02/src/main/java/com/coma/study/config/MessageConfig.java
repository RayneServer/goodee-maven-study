package com.coma.study.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

	@Bean
	LocaleResolver localeResolver() { // 메서드명 바꾸면 오류남
		// Session에 보관하는 경우
		SessionLocaleResolver sessionResolver = new SessionLocaleResolver();
		sessionResolver.setDefaultLocale(Locale.KOREAN);
	
		// Cookie에 보관하는 경우
		CookieLocaleResolver cookieResolver= new CookieLocaleResolver("lang");
		cookieResolver.setDefaultLocale(Locale.KOREAN);
		
		return cookieResolver;
	}
	
	LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		
		return interceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.localeChangeInterceptor())
						.addPathPatterns("/**");
	}
}
