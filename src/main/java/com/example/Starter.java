package com.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.example.config.DispatcherConfig;

public class Starter {
	public static void main( final String[] args ) throws Exception {
		Server server = new Server( 8080 );
		        
 		// Create the dispatcher servlet's Spring application context
 		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
 		dispatcherContext.register( DispatcherConfig.class );

 		// Register and map the dispatcher servlet
 		final ServletHolder servletHolder = new ServletHolder( new DispatcherServlet( dispatcherContext ) );
 		final ServletContextHandler context = new ServletContextHandler(); 		
 		context.setContextPath( "/" );
 		context.addServlet( servletHolder, "/*" ); 	
 		context.addEventListener( new ContextLoaderListener() );
 		
 		context.setInitParameter( "contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext" );
 		context.setInitParameter( "contextConfigLocation", "com.example.config.AppConfig" );
 		
        server.setHandler( context );
        server.start();
        server.join();
	}
}

