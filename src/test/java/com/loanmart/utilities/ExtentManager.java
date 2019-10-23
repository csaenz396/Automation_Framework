package com.loanmart.utilities;

import java.io.File;
import java.util.HashMap;

import com.loanmart.base.TestBase;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	private static ExtentReports extentQA;
	private static ExtentReports extentLocal;
	private static HashMap<String, ExtentReports> extent = new HashMap<String, ExtentReports>();

	public static HashMap<String, ExtentReports> getInstance(String reportName) {

		if (extentQA == null && extentLocal == null) {
			
			extentQA = new ExtentReports("\\\\loanmart\\fs\\Dept\\Tech\\QA\\Automation\\LM Test Automation\\Reports\\"+ TestBase.mileStone + "\\" +reportName+ "\\" + reportName + ".html", true, 
					DisplayOrder.OLDEST_FIRST);
			
			extentLocal = new ExtentReports(
					System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + "\\" + reportName + "\\" + reportName + ".html", true,
					DisplayOrder.OLDEST_FIRST);
			
			extentQA.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\extentconfig\\"
					+ reportName + "_Config.xml"));
			
			extentLocal.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\extentconfig\\"
					+ reportName + "_Config.xml"));
			
		} else if (extentQA == null && extentLocal != null) {
			
			//String mileStone = json.getMileStone("Test");
			
			extentQA = new ExtentReports("\\\\loanmart\\fs\\Dept\\Tech\\QA\\Automation\\LM Test Automation\\Reports\\"+ TestBase.mileStone + "\\" +reportName+ "\\" + reportName + ".html", true, 
					DisplayOrder.OLDEST_FIRST);
			
			extentQA.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\extentconfig\\"
					+ reportName + "_Config.xml"));
			
		} else {
			
			extentLocal = new ExtentReports(
					System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + reportName + "\\" + reportName + ".html", true,
					DisplayOrder.OLDEST_FIRST);
			
			extentLocal.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\extentconfig\\"
					+ reportName + "_Config.xml"));
		}

		extent.put("extentQA", extentQA);
		extent.put("extentLocal", extentLocal);
		
		return extent;

	}
}
