package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.SimpleBean;

@Configuration
public class AppConfig {
	@Bean
	public SimpleBean simpleBean() {
		return new SimpleBean();
	}
}
