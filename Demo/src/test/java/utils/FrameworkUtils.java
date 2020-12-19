package utils;

import java.io.File;
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
}
