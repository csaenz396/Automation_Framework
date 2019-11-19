package com.loanmart.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.loanmart.base.TestBase;
import com.loanmart.listeners.CustomListeners;
import com.relevantcodes.extentreports.LogStatus;

/*import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.LogStatus;
*/

/*##############################################################################*/
/*
 * This class takes care of taking screenshots, providing data to the test cases
 * and converting strings to different types of formats.
 */
/*###############################################################################*/
public class TestUtil{
	
	public static String screenshotPathLocal;
	public static String screenshotPathQA;
	public static String screenshotName;
	private ExcelReader excel = new ExcelReader();
	
	/*##############################################################################*/
	/*
	 * Function to take screenshots for test reporting
	 */
	/*###############################################################################*/
	public static void captureScreenshot(String testName) {
		File scrFile = ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.FILE);

		Calendar c = Calendar.getInstance();

		screenshotPathLocal = "screenshots\\";
		screenshotName = testName+c.get(Calendar.MILLISECOND)+ ".png";

		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\"+System.getProperty("TEST_NAME")+"\\screenshots\\"+ screenshotName));
			//FileUtils.copyFile(scrFile, new File( "\\\\loanmart\\fs\\Dept\\Tech\\QA\\Automation\\LM Test Automation\\Reports\\"+mileStone+"\\"+rptName+"\\Screenshots\\"+screenshotName));
			//FileUtils.copyFile(scrFile,  new File(screenshotPathQA+screenshotName));
		} catch (IOException e) {
			//testQA.log(LogStatus.FAIL, "FAIL=> Can not take AScreenshot");
			CustomListeners.testLocal.log(LogStatus.FAIL, "FAIL=> Can not take AScreenshot");
		}

	}
	
	/*##############################################################################*/
	/*
	 * return an multidimensional array of objects. This gets data from excel reader
	 * class.  and returns it to the test that called this function.
	 */
	/*###############################################################################*/
	
	@DataProvider(name = "dp")
	public Object[][] getData(Method m) throws IOException{
		//System.out.println(m.getName()+"***************");
		String testName;
		if(m.getName() == "EditLOSApplication")
			testName = "ApplicationSubmissionCCB";
		else if(m.getName() == "AELEditLOSApplication")
			testName = "ApplicationSubmissionAEL";
		else
			testName = m.getName();
		
		List<HashMap<String,String>> information = new ArrayList<HashMap<String,String>>();
		information = excel.getDataFromExcelFile(testName);
		//System.out.println(information);
		
		Object[][] data  =  new Object[information.size()][1];
		int[] counter = {0};
		information.forEach((content)->{
			data[counter[0]][0] = content;
			counter[0]++;
		});
		
		return data;
		
	}
	
	/*##############################################################################*/
	/*
	 * Convert string of number to a phone number format
	 */
	/*###############################################################################*/
	public static String convertToPhoneFormat(String phoneNumber) {
		return "("+phoneNumber.substring(0,3)+") "+phoneNumber.substring(3,6)+" - "+phoneNumber.substring(6,10);
	}
	
	/*##############################################################################*/
	/*
	 * Conver string of numbers to SSN
	 */
	/*###############################################################################*/
	public static String convertToSSNFormat(String SSNNumber) {
		return SSNNumber.substring(0,3)+"-"+SSNNumber.substring(3,5)+"-"+SSNNumber.substring(5,9);
	}


}
