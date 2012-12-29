package com.example;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.example.config.AppConfig;
import com.example.config.DispatcherConfig;

public class SimpleWebApplicationInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext container) {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register( AppConfig.class );

		// Manage the lifecycle of the root application context
		container.addListener( new ContextLoaderListener( rootContext ) );

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register( DispatcherConfig.class );

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet( "dispatcher", new DispatcherServlet( dispatcherContext ) );
		dispatcher.setLoadOnStartup( 1 );
		dispatcher.addMapping( "/" );
	}
}
