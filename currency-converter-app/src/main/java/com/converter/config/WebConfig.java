package com.converter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/logout");
		registry.addMapping("/signup");
		registry.addMapping("/token/**");
		registry.addMapping("/api/**")
			.allowedMethods("GET", "HEAD", "POST", "PUT", "OPTIONS");
	}	

}

