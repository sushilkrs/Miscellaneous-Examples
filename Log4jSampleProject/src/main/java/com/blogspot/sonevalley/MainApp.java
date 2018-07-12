package com.blogspot.sonevalley;

import org.apache.log4j.Logger;

public class MainApp {

	final static Logger logger = Logger.getLogger(MainApp.class);

	public static void main(String args[]) {

		MainApp app = new MainApp();
		app.generateLog("myLogger");
	}

	private void generateLog(String parameter) {

		if (logger.isDebugEnabled()) {
			logger.debug("This is debug : " + parameter);
		}

		if (logger.isInfoEnabled()) {
			logger.info("This is info : " + parameter);
		}

		if(logger.isTraceEnabled()) {
			logger.trace("This is trace : " + parameter);
		}
		
		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);
	}
}
