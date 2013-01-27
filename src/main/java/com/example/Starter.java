package com.example;

import java.security.ProtectionDomain;

import org.apache.catalina.startup.Tomcat;

public class Starter {
	public static void main(final String[] args) throws Exception {
		final ProtectionDomain domain = Starter.class.getProtectionDomain();

		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir( domain.getCodeSource().getLocation().toExternalForm() );
		tomcat.setPort( 8080 );
		tomcat.addWebapp( "/", "webapp");
		tomcat.getHost().setAppBase( domain.getCodeSource().getLocation().toExternalForm() );
		tomcat.getHost().setAutoDeploy(true);
		tomcat.getHost().setDeployOnStartup(true);
		tomcat.getHost().setCreateDirs( false );
		
		// or we could do this for root context:
		// tomcat.addWebapp("/", webRoot);
		tomcat.start();
		tomcat.getServer().await();

	}	
}

