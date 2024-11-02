package com.multi.schema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private TenantInterceptor tenantInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("InterceptorRegistry tenantInterceptor ADDED");
		registry.addInterceptor(tenantInterceptor);
	}
}
