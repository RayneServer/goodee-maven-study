package com.coma.study.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coma.study.common.filter.AdminCheckFilter;
import com.coma.study.common.filter.TestFilter;

import jakarta.servlet.Filter;

@Configuration
public class FilterConfig implements WebMvcConfigurer {
	
	@Bean
	FilterRegistrationBean<Filter> addTestFilterBean() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new TestFilter());
		filterRegistrationBean.addUrlPatterns("/notice/*", "/qna/*");
		filterRegistrationBean.setOrder(100); // 숫자가 낮을수록 우선순위 높음
		
		return filterRegistrationBean;
	}
	
	@Bean
	FilterRegistrationBean<Filter> addAdminCheckFilterBean() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new AdminCheckFilter());
		filterRegistrationBean.addUrlPatterns("/notice/add", "/notice/update", "/notice/delete");
		filterRegistrationBean.setOrder(20);
		
		return filterRegistrationBean;
	}
	
	
}
