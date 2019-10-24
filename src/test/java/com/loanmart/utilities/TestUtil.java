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
import com.relevantcodes.extentreports.LogStatus;

/*import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.LogStatus;
*/
public class TestUtil extends TestBase{
	
	public static String screenshotPathLocal;
	public static String screenshotPathQA;
	public static String screenshotName;
	
	public static void captureScreenshot(String testName) {

		System.out.println("*********milestone: "+mileStone+"**************");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		//Date d = new Date();
		Calendar c = Calendar.getInstance();

		//screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";// changed it from jpg to png
		screenshotPathLocal = System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\"+"report"+"\\screenshots\\";
		screenshotPathQA = "\\\\loanmart\\fs\\Dept\\Tech\\QA\\Automation\\LM Test Automation\\Reports\\"+mileStone+"\\"+"report"+"\\Screenshots\\";
		screenshotName = testName+c.get(Calendar.MILLISECOND)+ ".png";

		try {
			//FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\"+rptName+"\\screenshots\\"+ screenshotName));
			FileUtils.copyFile(scrFile, new File(screenshotPathLocal+screenshotName));
			//FileUtils.copyFile(scrFile, new File( "\\\\loanmart\\fs\\Dept\\Tech\\QA\\Automation\\LM Test Automation\\Reports\\"+mileStone+"\\"+rptName+"\\Screenshots\\"+screenshotName));
			FileUtils.copyFile(scrFile,  new File(screenshotPathQA+screenshotName));
		} catch (IOException e) {
			//testQA.log(LogStatus.FAIL, "FAIL=> Can not take AScreenshot");
			testLocal.log(LogStatus.FAIL, "FAIL=> Can not take AScreenshot");
			testQA.log(LogStatus.FAIL, "FAIL=> Can not take AScreenshot");
		}

	}
	
	
	@DataProvider(name = "dp")
	public Object[][] getData(Method m){
		System.out.println(m.getName()+"***************");
		List<HashMap<String,String>> information = new ArrayList<HashMap<String,String>>();
		information = excel.getDataFromExcelFile(m.getName());
		System.out.println(information);
		
		Object[][] data  =  new Object[information.size()][1];
		int[] counter = {0};
		information.forEach((content)->{
			data[counter[0]][0] = content;
			counter[0]++;
		});
		
		return data;
		
	}


}
