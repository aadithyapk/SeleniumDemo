package utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class Log {
	
	public static Logger log = LogManager.getLogger("manualLogger");
	
	public static void startTestCase(String strTestCaseName) {
		log.info("*******************************\t"+strTestCaseName+"\t\t*******************************");
	}
	public static void endTestCase() {
		log.info("***************************************************************************************\n#\n#\n#");
	}
	public static void logInfo(String message) {
		log.log(Level.INFO, message);
	}
	public static void logWarning(String message) {
		log.log(Level.WARN, message);
	}
	public static void logError(String message) {
		log.log(Level.ERROR, message);
		Assert.assertTrue(false, message);
	}
	public static void logFatal(String message) {
		log.log(Level.FATAL, message);
		Assert.assertTrue(false, message);
	}
	public static void logDebug(String message) {
		log.log(Level.DEBUG, message);
	}
	public static void logTrace(String message) {
		log.log(Level.TRACE, message);
	}
	public static void logException(Exception e) {
		String strMessage = "Exception occured. Exception class - "+e.getClass().getSimpleName()+" Exception message - "+e.getMessage();
		log.log(Level.ERROR, strMessage);
		Assert.assertTrue(false, strMessage);
	}
	public static void logException(String strDescription, Exception e) {
		String strMessage = "Exception occured when "+strDescription+". Exception class - "+e.getClass().getSimpleName()+" Exception message - "+e.getMessage();
		log.log(Level.ERROR, strMessage);
		Assert.assertTrue(false, strMessage);
	}
}
