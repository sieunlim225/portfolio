package com.portfolio.project.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {

	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	public void logging() {
		logger.error("ttttttttttttttttt");
	}
}
