package com.example;

import java.io.File;
import java.io.IOException;

import org.apache.catalina.Context;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.example.config.AppConfig;

public class Starter {	
	private final static Log log = LogFactory.getLog( Starter.class );
	
	public static void main(final String[] args) throws Exception {
		final File base = createBaseDirectory();
		log.info( "Using base folder: " + base.getAbsolutePath() );
		
		final Tomcat tomcat = new Tomcat();
		tomcat.setPort( 8080 );
		tomcat.setBaseDir( base.getAbsolutePath() );	
		
		Context context = tomcat.addContext( "/", base.getAbsolutePath() );
		Tomcat.addServlet( context, "CXFServlet", new CXFServlet() );
		
		context.addServletMapping( "/rest/*", "CXFServlet" );
		context.addApplicationListener( ContextLoaderListener.class.getName() );
		context.setLoader( new WebappLoader( Thread.currentThread().getContextClassLoader() ) );
		
		context.addParameter( "contextClass", AnnotationConfigWebApplicationContext.class.getName() );
 		context.addParameter( "contextConfigLocation", AppConfig.class.getName() );
 		
		tomcat.start();
		tomcat.getServer().await();
	}

	private static File createBaseDirectory() throws IOException {
		final File base = File.createTempFile( "tmp-", "" );
		
		if( !base.delete() ) {
			throw new IOException( "Cannot (re)create base folder: " + base.getAbsolutePath()  );
		}
		
		if( !base.mkdir() ) {
			throw new IOException( "Cannot create base folder: " + base.getAbsolutePath()  );	        
	    }
		
		return base;
	}	
}

