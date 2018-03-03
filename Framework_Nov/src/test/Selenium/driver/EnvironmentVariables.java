package test.Selenium.driver;

public class EnvironmentVariables {

	// Update the Work Book Name 
	public final static String workbookName = "Selenium_DataSheet.xlsx";
	public final static String driverPath = "C:\\Users\\n.kumarasamy\\Desktop\\Selenium_Artifacts\\chromedriver.exe";
	public final static String driverType = "webdriver.chrome.driver";
	
	public final static String dataPoolPath = System.getProperty("user.dir")+"\\src\\test\\Selenium\\resources\\"+workbookName;
			
}
