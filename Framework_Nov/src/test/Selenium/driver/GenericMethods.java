package test.Selenium.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class GenericMethods {

	/****
	 *   This method used to log a screenshot of the particular screen
	 * @param condition - Whether the condition is Pass/ Fail / Info
	 * @param msg - Message that needs to display in the output report
	 * @param driver - WebDriver
	 */
	public void logScreenshot(String condition,String msg, WebDriver driver)
	{	
		String userDirector = System.getProperty("user.dir") + "/"; 

		String s1 = null,s2 ="";
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		if(true)
		{
			try {

				String failureImageFileName =  new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime())+ ".png"; 
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("Screenshot\\"+failureImageFileName)); 
				s2 = "<a href=\""+ userDirector +"\\Screenshot\\" + failureImageFileName +"\"><img src=\"file:///" + userDirector +"\\Screenshot\\" + failureImageFileName + "\" alt=\"\""+ "height='300' width='300' border =1/> "+"<br />";


			} catch (IOException e1) {
				e1.printStackTrace();
			}


			if (condition.equalsIgnoreCase("info"))	
			{
				s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>Info</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"

					+ "<tr>"
					+ "<td colspan=2>" 									
					+ "<table width= 100% ; rules =rows >"

												+ "<tbody>"

													+ "<tr>"
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+"Screenshot"+"</strong></td>" 
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+s2+"</strong></td>"

													+ "</tr>"
													+"</tbody>"
													+"</table>"

								+ "</td>"	
								+"</tr>"

				+ "</tbody>"
				+"</table>";

			}

			if (condition.equalsIgnoreCase("Pass"))	
			{
				s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:green;font-size:12px;font-family:verdana;\\><strong>Pass</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"

					+ "<tr>"
					+ "<td colspan=2>" 
					+ "<table width= 100% ;rules =rows >"

												+ "<tbody>"

													+ "<tr>"
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+"Screenshot"+"</strong></td>" 
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+s2+"</strong></td>"

													+ "</tr>"
													+"</tbody>"
													+"</table>"

								+ "</td>"	
								+"</tr>"

				+ "</tbody>"
				+"</table>";

			}
			if (condition.equalsIgnoreCase("Fail"))	
			{
				s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:red;font-size:12px;font-family:verdana;\\><strong>Fail</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"

					+ "<tr>"
					+ "<td colspan=2>" 
					+ "<table width= 100% ; rules =rows >"

												+ "<tbody>"

													+ "<tr>"
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+"Screenshot"+"</strong></td>" 
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+s2+"</strong></td>"

													+ "</tr>"
													+"</tbody>"
													+"</table>"
													+ "</td>"	
													+"</tr>"

				+ "</tbody>"
				+"</table>";

			}
			Reporter.log(s1);
		}
	}

	/*** This method is used to set the log message in the output report
	 * 
	 * @param condition - Whether it is Info,pass or fail
	 * @param msg - What is the message need to be passed in the output report
	 */
	public void setLogMsg(String condition, String msg)
	{
		String s1 ="";

		if (condition.equalsIgnoreCase("info"))	
		{
			s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>Info</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"									
					+ "</tbody>"
					+"</table>";

		}

		if (condition.equalsIgnoreCase("Pass"))	
		{
			s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:Green;font-size:12px;font-family:verdana;\\><strong>Pass</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"									
					+ "</tbody>"
					+"</table>";

		}
		if (condition.equalsIgnoreCase("fail"))	
		{
			s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:red;font-size:12px;font-family:verdana;\\><strong>Fail</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"									
					+ "</tbody>"
					+"</table>";
		}
		Reporter.log(s1);
	}


	/*** This method used to Wait for an object to be located in the UI
	 * 
	 * @param driver - Webdriver
	 * @param object - Object, which needs to be located
	 */
	public void waitForObject(WebDriver driver,By object)
	{
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.presenceOfElementLocated(object));
	}

	/** This method used to read the excel data and store it two dimensional Array
	 * 
	 * @param fileName - Where the file located in project
	 * @param sheetName - Sheet, where data needs to be extracted
	 * @return
	 */
	public String[][] getExcelData(String fileName, String sheetName) 
	{
		String[][] arrayExcelData = null;
		org.apache.poi.ss.usermodel.Workbook tempWB;

		try {

			if(fileName.contains(".xlsx")){
				tempWB = new XSSFWorkbook(fileName);
			}
			else{				
				InputStream inp = new FileInputStream(fileName);
				tempWB = (org.apache.poi.ss.usermodel.Workbook) new HSSFWorkbook(new POIFSFileSystem(inp));					
			}

			org.apache.poi.ss.usermodel.Sheet sheet = tempWB.getSheet(sheetName);

			// Total rows counts the top heading row
			int totalNoOfRows = sheet.getLastRowNum();
			System.out.println("The total rows are : " + totalNoOfRows);
			Row row = sheet.getRow(0);
			int totalNoOfCols = row.getLastCellNum();
			System.out.println("The total colums are : " + totalNoOfCols);

			arrayExcelData = new String[totalNoOfRows][totalNoOfCols];

			try {
				for (int i= 1 ; i < totalNoOfRows+1; i++) {

					for (int j=0; j < totalNoOfCols; j++) 
					{
						row = sheet.getRow(i);
						try{
							System.out.println(row.getCell(j).toString().trim());
							arrayExcelData[i-1][j] = row.getCell(j).toString().trim();
						}
						catch(Exception e){
							arrayExcelData[i-1][j] = "";
						}

						System.out.println("Coming here");
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	/** This method used to find the Column Index in excel sheet, depends on the column name
	 * 
	 * @param sheet - Excel sheet, where needs to find the column
	 * @param ColName - Col Name
	 * @return
	 */
	public int findCol(Sheet sheet,String ColName)
	{
		Row row = null;
		int colCount = 0;

		row = sheet.getRow(0);
		if (!(row==null))
		{
			colCount = row.getLastCellNum();
		}
		else
			colCount = 0;

		for (int j=0;j<colCount;j++)
		{
			if(!( row.getCell(j)==null)){
				if (row.getCell(j).toString().trim().equalsIgnoreCase(ColName)|| row.getCell(j).toString().trim().equalsIgnoreCase((ColName+"[][String]"))){
					return j;
				}
			}
		}
		return -1;
	}

	/*** This method used to get the value from the excel sheet
	 * 
	 * @param SheetName - Sheet, where data needs to extracted
	 * @param colName - Column Name
	 * @param rowNo - Row number, at which data needs to extracted
	 * @return
	 */
	public String getValueFromDatasheet(String SheetName,String colName,int rowNo)
	{
		try
		{
			Workbook tempWB;
			String value ="";
			if (EnvironmentVariables.dataPoolPath.contains(".xlsx"))
				tempWB = new XSSFWorkbook(EnvironmentVariables.dataPoolPath);

			else
			{
				FileInputStream inp = new FileInputStream(EnvironmentVariables.dataPoolPath);
				tempWB = (Workbook) new HSSFWorkbook(new POIFSFileSystem(inp));	
			}

			Sheet sheet = tempWB.getSheet(SheetName);
			Row row = sheet.getRow(rowNo);

			if(row == null){
				return null;
			}
			try{
				value = row.getCell(findCol(sheet, colName)).toString().trim();
				return value;
			}
			finally {}
		}
		catch(FileNotFoundException e)
		{
			setLogMsg("Fail", "File not found in the path : "+ EnvironmentVariables.dataPoolPath);
		}
		catch(IOException e)
		{
			setLogMsg("Fail", "Problem in reading the File");
		}
		return null;
	}
}