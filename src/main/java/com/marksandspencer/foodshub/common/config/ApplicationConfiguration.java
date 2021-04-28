package com.marksandspencer.foodshub.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marksandspencer.foodshub.common.filter.JwtAuthenticationFilter;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthFilter() {
		FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtAuthenticationFilter());
		registrationBean.addUrlPatterns("/v1/*");
		return registrationBean;
	}

}
