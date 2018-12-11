package com.bootcamp.mavenapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bootcamp.mavenapp.dao.CustomerDao;
import com.bootcamp.mavenapp.dao.impl.CustomerDaoImpl;

@Configuration
public class DaoSpringConfig {
	
	@Bean
	public CustomerDao customerDao() {
		return new CustomerDaoImpl();
	}

}
