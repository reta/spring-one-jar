package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.AppConfig;

public class Starter {
	public static void main( final String[] args ) {
		final ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
		final SimpleBean bean = context.getBean( SimpleBean.class );
		bean.print();
	}
}
