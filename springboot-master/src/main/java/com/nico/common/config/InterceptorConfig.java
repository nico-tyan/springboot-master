package com.nico.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nico.common.interceptor.ErrorInterceptor;

//@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	
	
	@Bean
	public ErrorInterceptor errorInterceptor() {

		 return new ErrorInterceptor();

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(errorInterceptor()).addPathPatterns("/**");

		super.addInterceptors(registry);
	}
}
