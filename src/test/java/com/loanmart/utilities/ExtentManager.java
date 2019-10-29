package com.loanmart.utilities;


import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extentLocal;

	public ExtentReports getInstance(String reportName) {

		if (extentLocal == null) {
			
			/*
			 * extentQA = new
			 * ExtentReports("\\\\loanmart\\fs\\Dept\\Tech\\QA\\Automation\\LM Test Automation\\Reports\\"+ TestBase.mileStone + "
			 * \\" +reportName+ "\\" + reportName + ".html", true,
			 * DisplayOrder.OLDEST_FIRST);
			 */
			
			extentLocal = new ExtentReports(
					System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + "\\" + reportName + "\\" + reportName + ".html", true,
					DisplayOrder.OLDEST_FIRST);
			
			/*
			 * extentLocal.loadConfig(new File(System.getProperty("user.dir") +
			 * "\\src\\test\\resources\\extentconfig\\" + reportName + "_Config.xml"));
			 */
			
		}
		
		return extentLocal;
	}
}
