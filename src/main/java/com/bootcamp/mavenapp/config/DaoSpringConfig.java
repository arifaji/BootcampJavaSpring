package com.bootcamp.mavenapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bootcamp.mavenapp.dao.AccountDao;
import com.bootcamp.mavenapp.dao.CustomerDao;
import com.bootcamp.mavenapp.dao.TransactionDao;
import com.bootcamp.mavenapp.dao.impl.AccountDaoImpl;
import com.bootcamp.mavenapp.dao.impl.CustomerDaoImpl;
import com.bootcamp.mavenapp.dao.impl.TransactionDaoImpl;

@Configuration
public class DaoSpringConfig {
	
	@Bean
	public CustomerDao customerDao() {
		return new CustomerDaoImpl();
	}
	
	@Bean
	public AccountDao accountDao() {
		return new AccountDaoImpl();
	}
	
	@Bean
	public TransactionDao transactionDao() {
		return new TransactionDaoImpl();
	}
	
	@Bean
	public WebMvcConfigurerAdapter corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE").allowedOrigins("*")
				.allowedHeaders("*");
			}
		};
	}
}
