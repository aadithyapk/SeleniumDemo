package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Allure {
	public static void allureServe()
	{
		String command [] = {"cmd.exe", "/c", "allure", "serve", System.getProperty("user.dir")+FrameworkUtils.getTestProperty("allure-results.path")};
		try {
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
