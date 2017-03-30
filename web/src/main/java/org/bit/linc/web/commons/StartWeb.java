package org.bit.linc.web.commons;

import org.bit.linc.commons.config.*;
import org.bit.linc.commons.exception.SysimpleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartWeb{
	private static final Logger logger = LoggerFactory.getLogger(StartWeb.class);
	private static EmbeddedServer embeddedServer;
	public static void main(String[] args) throws SysimpleException {
		//If failed to init config properties, web server will not start.
		if(false==new HierarchicalConfigProperties().initConfigProperties()){
			return;
		}
		//Start web server
		int port=Integer.valueOf(System.getProperty("sysimple.webserver.port"));
        logger.info("starting Sysimple in port:{}",port);
        try{
        	if(args.length==0){
            	embeddedServer=new EmbeddedServer(port, "src/main/webapp");
            }else{
            	embeddedServer=new EmbeddedServer(port, true, args[0]);
            }
        	embeddedServer.start();
        }catch(Exception e){
        	logger.error(e.getMessage());      	
        	System.exit(0);
        }
	}
	
}