package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;

public class FrameworkUtils {
	
	static Configuration testConfig;
	
	public static void loadTestProperties() {
		String testConfigPath = "./src/test/resources/configs/" + "test.properties";
		try {
		File propertiesFile = new File(testConfigPath);
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    		.configure(new Parameters().fileBased()
		    				.setFile(propertiesFile));
			testConfig = builder.getConfiguration();
		} catch (Exception e) {
			Log.logException("loading properties file from path - "+testConfigPath, e);
		}
	}
	
	public static String getTestProperty(String strPropKey) {
		return testConfig.getString(strPropKey);
	}
	
	public static Object[][] readTestData(String strSheetName, String strTestCaseName) {
		Object[][] testObjArray = null;
		try {
		String strFilePath;
		int intTestCaseRow;
		strFilePath = FrameworkUtils.getTestProperty("testdata.path");
		ExcelUtils.setExcelFile(strFilePath, strSheetName);
		intTestCaseRow = ExcelUtils.getRowContains(strTestCaseName,0);
		testObjArray = ExcelUtils.getTableArray(strFilePath,strSheetName,intTestCaseRow);
		} catch (Exception e) {
			Log.logException("Error in loading test data", e);
		} 
		return testObjArray;
	}
	
	public static void runCommand(String[] command) {
		try {
			String line;
			Process process = Runtime.getRuntime().exec(command);
			Log.logInfo("Executed command - "+Arrays.toString(command));
			Log.logInfo("Start output from terminal");
		    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    while ((line = reader.readLine()) != null) {
		    	Log.logInfo(line);
		    }
		    reader.close();
		    Log.logInfo("End output from terminal");
		    reader = null;
		    process = null;
		} catch (Exception e) {
			
		}
	}
	
}
