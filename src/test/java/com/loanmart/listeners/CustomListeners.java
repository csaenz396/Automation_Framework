package com.loanmart.listeners;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.loanmart.mainwebsite.ApplyForLoan;
import com.loanmart.utilities.ExtentManager;
import com.loanmart.utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners implements ITestListener {
	private ExtentManager manager = new ExtentManager();
	private ExtentReports reps;
	public static ExtentTest testLocal;


	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		
		reps = manager.getInstance(System.getProperty("TEST_NAME"));
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		System.setProperty("org.uncommons.reportng.escape-output","false");
		TestUtil.captureScreenshot(arg0.getName());
		
		testLocal.log(LogStatus.FAIL, arg0.getName()+" Failed with exception : "+arg0.getThrowable());
		testLocal.log(LogStatus.FAIL, testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		reps.endTest(testLocal);
		reps.flush();
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {

		testLocal.log(LogStatus.SKIP, " SKIPPED the test : "+arg0.getName());
		reps.endTest(testLocal);	
		reps.flush();
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {

		testLocal = reps.startTest(arg0.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.setProperty("org.uncommons.reportng.escape-output","false");
		
		TestUtil.captureScreenshot(arg0.getName());
		
		testLocal.log(LogStatus.PASS, arg0.getName()+" - Test completed");
		testLocal.log(LogStatus.PASS, testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));


		reps.endTest(testLocal);
		reps.flush();
		
	}

}
