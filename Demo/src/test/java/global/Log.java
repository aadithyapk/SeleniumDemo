package global;

import org.apache.log4j.Logger;

public class Log {
	
	public static Logger log = Logger.getLogger("ManualLogger");
	
	public Log() {
		log = Logger.getLogger("ManualLogger");
	}
	
	public static void startTestCase(String strTestCaseName) {
		log.info("*******************************\t"+strTestCaseName+"\t\t*******************************");
	}
	
	public static void endTestCase() {
		log.info("***************************************************************************************\n\n\n");
	}
}
