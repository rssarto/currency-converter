package com.converter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.converter.dao"})
@ComponentScan(basePackages={"com.converter.*"})
public class CurrencyConverterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterAppApplication.class, args);
	}
}
