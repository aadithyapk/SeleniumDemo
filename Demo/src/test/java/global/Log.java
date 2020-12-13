package global;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	
	public static Logger log = LogManager.getLogger("manualLogger");
	
	public static void startTestCase(String strTestCaseName) {
		log.info("*******************************\t"+strTestCaseName+"\t\t*******************************");
	}
	
	public static void endTestCase() {
		log.info("***************************************************************************************\n#\n#\n#");
	}
}
