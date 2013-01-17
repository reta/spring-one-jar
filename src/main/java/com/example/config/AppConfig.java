package com.example.config;

import java.util.Arrays;

import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.example.services.PeopleService;

@Configuration
@ImportResource( "classpath:/META-INF/cxf/cxf.xml" )
public class AppConfig {	
	@Bean( destroyMethod = "shutdown" )
	public SpringBus cxf() {
		return new SpringBus();
	}
	
	@Bean
	public Server jaxRsServerFactory() {
		JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint( jaxRsConfig(), JAXRSServerFactoryBean.class );
		factory.setServiceBeans( Arrays.< Object >asList( peopleService() ) );
		factory.setAddress( "/" + factory.getAddress() );
		return factory.create();
	}
	
	@Bean 
	public JaxRsConfig jaxRsConfig() {
		return new JaxRsConfig();
	}
	
	@Bean 
	public PeopleService peopleService() {
		return new PeopleService();
	}
}
