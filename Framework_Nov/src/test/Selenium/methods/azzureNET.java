package test.Selenium.methods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;


import test.Selenium.*;
import test.Selenium.driver.GenericMethods;
import test.Selenium.pageObjects.azzureNET_IT_Pages;

public class azzureNET {

	test.Selenium.driver.GenericMethods gm = new test.Selenium.driver.GenericMethods();

	public void cadencyLogin(WebDriver driver,String userName,String password) throws InterruptedException
	{
		gm.waitForObject(driver,azzureNET_IT_Pages.username);
		driver.findElement(azzureNET_IT_Pages.username).sendKeys(userName);
		driver.findElement(azzureNET_IT_Pages.password).sendKeys(password);
		driver.findElement(azzureNET_IT_Pages.loginBtn).click();
		gm.waitForObject(driver,azzureNET_IT_Pages.menu);
		Thread.sleep(6000);
		gm.logScreenshot("Pass", "Successfully navigated to Cadency Home page", driver);
	}


	public void navigateToJobsSection(WebDriver driver) throws InterruptedException
	{
		driver.findElement(azzureNET_IT_Pages.lnkSysManagement).click();
		driver.findElement(azzureNET_IT_Pages.lnkJobs).click();
		Thread.sleep(3000);
		gm.waitForObject(driver, azzureNET_IT_Pages.lstSelectJobToCreate);

		WebElement lstSelectJob = driver.findElement(azzureNET_IT_Pages.lstSelectJobToCreate);
		if (lstSelectJob.isDisplayed())
			gm.logScreenshot("Pass", "Navigated to JOBS Page", driver);
		else
			gm.logScreenshot("Fail", "Not navigated to JOBS Page, Select Job To Create object is not found", driver);
	}
	
	public void verifyTblCellContentAndClickOnCellLink(WebDriver driver,String jobName) throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement tblElm = driver.findElement(By.xpath("//*[@class='k-selectable']"));
		if(tblElm.isDisplayed())
		{
			// Need to get the column position
			//*[@id='MergeJobsGridSection']//*[@class='k-grid-header']

			// Getting the column names and position
			List<WebElement> tblHeads = driver.findElements(By.xpath("//*[@id='MergeJobsGridSection']//*[@class='k-grid-header']//TH"));
			int colPos = 0;
			int matchColName = -1;
			int matchColLastResult = -1;

			// Getting the 'Name' column position
			for (colPos=0;colPos<tblHeads.size();colPos++)
			{
				String colName = tblHeads.get(colPos).getAttribute("data-title").toString().trim();
				System.out.println("Col names are : "+ colName);
				if (colName.equalsIgnoreCase("Name"))
				{
					matchColName = colPos;
					break;
				}
			}

			// Getting the Last Result Column position
			for (colPos=0;colPos<tblHeads.size();colPos++)
			{
				String colName = tblHeads.get(colPos).getAttribute("data-title").toString().trim();
				System.out.println("Col names are : "+ colName);
				if (colName.equalsIgnoreCase("Last Result"))
				{
					matchColLastResult = colPos;
					break;
				}
			}

			// Iterate over the table to identify the column
			List<WebElement> tblrows = driver.findElements(By.xpath("//*[@class='k-selectable']//TR"));

			for (int k=0;k<tblrows.size();k++)
			{
				if (tblrows.size()>0)
				{
					// Get the column and check whether matching content is present or not
					List<WebElement> column = tblrows.get(k).findElements(By.xpath(".//TD"));
					System.out.println("Column len are : "+ column.size());
					System.out.println("The match Col is : "+ matchColName);

					if (column.size()>0)
					{
						String cellContent = column.get(matchColName).getText();
						System.out.println("The cell content is : "+ cellContent);

						if (cellContent.equalsIgnoreCase(jobName))
						{
							WebElement elm = tblrows.get(k).findElement(By.xpath(".//*[text()='Run Now']"));
							elm.click();
							Thread.sleep(10000);

							//  Validate the Last Result column status to check whether it got Success or Fail. Not sure about the time the job will take to complete.
							// So added while loop

							while (true)
							{
								try
								{
									String resValue = column.get(matchColLastResult).getText();
									System.out.println("The last res value : "+ resValue);
									if (resValue.equalsIgnoreCase("Success"))
									{
										gm.logScreenshot("Pass", "Job ran successfully. Job Status is : "+ resValue, driver);
										break;
									}
									else if (resValue.equalsIgnoreCase("Fail"))
									{					
										gm.logScreenshot("Fail", "Job have some failures. Job Status is : "+ resValue, driver);
										break;
									}
								}
								catch(Exception e){continue;}
							}
							System.out.println("Coming out of loop");
							break;
						}
						else
							continue;
					}
					else
						gm.logScreenshot("Fail", "No Columns found", driver);
				}
				else
					gm.setLogMsg("Fail", "No rows found in the table");
			}
			if(matchColName==-1)
				gm.setLogMsg("Fail", "Name Column not found ");
		}
		else
			gm.logScreenshot("Fail", "Job Definitions table is not found", driver);
	}
}