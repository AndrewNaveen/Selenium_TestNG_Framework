package pageObjects;

import org.openqa.selenium.By;

public class azzureNET_IT_Pages {

	public static By username=By.xpath("//*[@id='loginIdTextBox']");
	public static By password=By.xpath("//*[@id='passwordTextBox']");
	public static By loginBtn=By.xpath("//*[@id='loginButton']");
	public static By menu = By.xpath("//*[@id='menu_mega']"); 
	
	public static By lnkSysManagement = By.xpath("//*[text()='System Management']");
	public static By lnkJobs = By.xpath("//*[text()='Jobs']");
	public static By lstSelectJobToCreate = By.xpath("//*[text()='Select Job to Create']");
	public static By txtJobs = By.xpath("//*[@id='tabJob']");
	
	
}
