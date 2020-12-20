package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Allure {
	public static void allureServe()
	{
		String command [];
		try {
			String resultsPath = System.getProperty("user.dir")+FrameworkUtils.getTestProperty("allure-results.path");
			command = new String [] {"cmd.exe", "/c", "allure", "serve", resultsPath};
			Process process = Runtime.getRuntime().exec(command);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		    reader.close();
		} catch (Exception e) {
			Log.logException("executing allure serve", e);
		}
	}
}
