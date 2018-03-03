package test.Selenium.scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import driver.EnvironmentVariables;
import driver.GenericMethods;

public class AssureNET_IT {
	
	WebDriver driver = null;
	methods.azzureNET a = new methods.azzureNET();
	GenericMethods gm = new GenericMethods();
		
	@BeforeClass
	public void intialSetUp()
	{
		System.setProperty(EnvironmentVariables.driverType, EnvironmentVariables.driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String className = this.getClass().getSimpleName();
		System.out.println("Clas Name is : "+ className);
		driver.navigate().to("https://hess.preprodrpm.accenture.com/hess/Login.aspx");
	}
		
	@Parameters("sheetName")
	@Test(dataProvider ="cadencyJobValidation")
	public void azzureNET(String flag, String action, String url,String userName, String password, String jobName)
	{
		try {
			a.cadencyLogin(driver, userName,password);
			a.navigateToJobsSection(driver);
			a.verifyTblCellContentAndClickOnCellLink(driver, jobName);
		} 
		
		catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	@DataProvider(name = "cadencyJobValidation") // Giving a name to data provider
	public Object[][] values(ITestContext context)
	{
		String shetName = context.getCurrentXmlTest().getParameter("sheetName");
		Object[][] c1 = gm.getExcelData(EnvironmentVariables.dataPoolPath, shetName);
		return c1;
	}

	

	@AfterClass
	public void quitDriver()
	{
		driver.close();
		driver.quit();
	}
}
