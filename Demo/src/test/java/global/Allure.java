package global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Allure {
	public static void allureServe() throws IOException
	{
		/*Runtime rt = Runtime.getRuntime();
		System.out.println("allure serve "+System.getProperty("user.dir")+"\\framework-output\\allure-results");
		//Process pr = rt.exec("allure serve "+System.getProperty("user.dir")+"\\framework-output\\allure-results");
		Process pr = rt.exec("ipconfig");
		System.out.println(pr);*/
		
		String command [] = {"cmd.exe", "/c", "allure", "serve", System.getProperty("user.dir")+"\\framework-output\\allure-results"};
		try {
		    Process process = Runtime.getRuntime().exec(command);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
