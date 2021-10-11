package utils;

public class Allure {
	public static void allureServe()
	{
		String command [];
		try {
			String resultsPath = System.getProperty("user.dir")+FrameworkUtils.getTestProperty("allure-results.path");
			command = new String [] {"cmd.exe", "/c", "allure", "serve", resultsPath};
			FrameworkUtils.runCommand(command);
		} catch (Exception e) {
			Log.logException("Exception in executing allure serve", e);
		}
	}
}
