package com.example;

import org.apache.catalina.Context;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.Tomcat;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.example.config.AppConfig;

public class Starter {
	public static void main(final String[] args) throws Exception {
		final Tomcat tomcat = new Tomcat();
		tomcat.setPort( 8080 );
		
		Context context = tomcat.addContext( "/", System.getProperty( "java.io.tmpdir" ) );
		Tomcat.addServlet( context, "CXFServlet", new CXFServlet() );
		
		context.addServletMapping( "/rest/*", "CXFServlet" );
		context.addApplicationListener( ContextLoaderListener.class.getName() );
		context.setLoader( new WebappLoader( Thread.currentThread().getContextClassLoader() ) );
		
		context.addParameter( "contextClass", AnnotationConfigWebApplicationContext.class.getName() );
 		context.addParameter( "contextConfigLocation", AppConfig.class.getName() );
 		
		tomcat.start();
		tomcat.getServer().await();
	}	
}

