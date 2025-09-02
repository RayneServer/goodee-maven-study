package com.coma.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coma.study.common.interceptor.UpdateWriterCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UpdateWriterCheckInterceptor())
						.addPathPatterns("/notice/update", "/qna/update");
	}

}
