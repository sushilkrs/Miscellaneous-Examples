package com.blogspot.sonevalley;

import org.apache.log4j.Logger;

public class App 
{
	final static Logger logger = Logger.getLogger(App.class);
	
    public static void main( String[] args ) {
        App app = new App();
        app.generateLog("myLogger");
    }		
    
    private void generateLog(String parameter) {
    	            
        if(logger.isDebugEnabled()){
			logger.debug("This is debug : " + parameter);
		}
		
		if(logger.isInfoEnabled()){
			logger.info("This is info : " + parameter);
		}
		
		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);
    }
}
