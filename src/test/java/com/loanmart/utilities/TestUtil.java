package com.loanmart.utilities;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.loanmart.base.TestBase;

/*import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.LogStatus;
*/
public class TestUtil extends TestBase{
	
	public static void captureScreenshot() {
		System.out.println("Taking screenshot");

	}
	
	
	@DataProvider(name = "dp")
	public Object[][] getData(Method m){
		List<HashMap<String,String>> information = new ArrayList<HashMap<String,String>>();
		information = excel.getDataFromExcelFile(2);
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
