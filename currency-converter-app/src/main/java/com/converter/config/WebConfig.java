package com.converter.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Autowired
	private Environment env;
	
	@Bean
	public BasicDataSource dataSource() {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        if( dbUrl == null ) {
        	dbUrl = env.getProperty("spring.datasource.url");
        }
        
        String username = System.getenv("JDBC_DATABASE_USERNAME");
        if( username == null ) {
        	username = env.getProperty("spring.datasource.username");
        }
        
        String password = System.getenv("JDBC_DATABASE_PASSWORD");
        if( password == null ) {
        	password = env.getProperty("spring.datasource.password");
        }

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

}

