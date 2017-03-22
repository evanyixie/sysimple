package org.bit.linc.web.commons;

import org.apache.commons.configuration.Configuration;
import org.bit.linc.config.*;
import org.bit.linc.exception.SysimpleException;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartWeb {
	public static void main(String[] args) throws SysimpleException {
		//If SYSIMPLE_HOME have not been configured, the web server will not start.
		String SYSIMPLE_HOME=System.getProperty("SYSIMPLE_HOME");
		if(null==SYSIMPLE_HOME){
			System.out.println("The environment variable SYSIMPLE_HOME hava not been configured.");
			System.out.println("Set the SYSIMPLE_HOME and try again!.");
			return;
		}
		
		//Start web server
		Server server = new Server();
		server.setStopAtShutdown(true);
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setReuseAddress(false);
		Configuration config=ApplicationProperties.get();
		int port=config.getInt("sysimple.webserver.port", DefaultConfiguration.SYSIMPLE_WEBSERVER_PORT.getInt());	
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector}); 
        WebAppContext webAppContext;
        if(args.length==0){
        	webAppContext = new WebAppContext("src/main/webapp","/");
            webAppContext.setDescriptor("src/main/webapp/WEB-INF/web.xml");
            webAppContext.setResourceBase("src/main/webapp");
        }else{
        	webAppContext = new WebAppContext();
        	webAppContext.setWar(args[0]);
        }
        webAppContext.setDisplayName("jetty");
        webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        webAppContext.setConfigurationDiscovered(true);
        webAppContext.setParentLoaderPriority(true);
        server.setHandler(webAppContext);
        server.setStopAtShutdown(true);
        try{
            server.start();
            System.out.println("********************************************************");
            System.out.println("The SySimple Has Started !!!");
        }catch(Exception e){
        }
	}
}