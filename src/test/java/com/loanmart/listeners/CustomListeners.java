package com.loanmart.listeners;

import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.loanmart.base.TestBase;
import com.loanmart.utilities.ExtentManager;
import com.loanmart.utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener {
	
	public HashMap<String, ExtentReports> reps;

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		
		/* rptName = arg0.getName(); */
		reps = ExtentManager.getInstance(arg0.getName());
		
		repQA = reps.get("extentQA");
		repLocal = reps.get("extentLocal");
		
		testQA = repQA.startTest(arg0.getName());
		testLocal = repLocal.startTest(arg0.getName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		System.setProperty("org.uncommons.reportng.escape-output","false");
		TestUtil.captureScreenshot(arg0.getName());
		testQA.log(LogStatus.FAIL, arg0.getName()+" Failed with exception : "+arg0.getThrowable());
		testQA.log(LogStatus.FAIL, testQA.addScreenCapture(TestUtil.screenshotPathQA+TestUtil.screenshotName));
		
		testLocal.log(LogStatus.FAIL, arg0.getName()+" Failed with exception : "+arg0.getThrowable());
		testLocal.log(LogStatus.FAIL, testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		
		//Reporter.log("Click to see Screenshot");
		//Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		//Reporter.log("<br>");
		//Reporter.log("<br>");
		//Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
		repQA.endTest(testQA);
		repLocal.endTest(testLocal);
		repQA.flush();
		repLocal.flush();
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		testQA.log(LogStatus.SKIP, " SKIPPED the test : "+arg0.getName());
		testLocal.log(LogStatus.SKIP, " SKIPPED the test : "+arg0.getName());
		repQA.endTest(testQA);
		repLocal.endTest(testLocal);
		repQA.flush();	
		repLocal.flush();
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		testQA = repQA.startTest(arg0.getName());
		testLocal = repLocal.startTest(arg0.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.setProperty("org.uncommons.reportng.escape-output","false");
		
		TestUtil.captureScreenshot(arg0.getName());
		
		testQA.log(LogStatus.PASS, arg0.getName()+" - Test completed");
		testQA.log(LogStatus.PASS, testQA.addScreenCapture(TestUtil.screenshotPathQA+TestUtil.screenshotName));
		
		testLocal.log(LogStatus.PASS, arg0.getName()+" - Test completed");
		testLocal.log(LogStatus.PASS, testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));

		repQA.endTest(testQA);
		repLocal.endTest(testLocal);
		
		repQA.flush();
		repLocal.flush();
		
	}

}
