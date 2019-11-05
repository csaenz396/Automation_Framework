package com.loanmart.test;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import com.loanmart.base.TestBase;
import com.loanmart.listeners.CustomListeners;
import com.loanmart.pages.LOS.AccountLookupPage;
import com.loanmart.pages.LOS.AdditionalInfoPage;
import com.loanmart.pages.LOS.AddressPage;
import com.loanmart.pages.LOS.FinalizePage;
import com.loanmart.pages.LOS.IncomePage;
import com.loanmart.pages.LOS.MainPage;
import com.loanmart.pages.LOS.TermsPage;
import com.loanmart.pages.LOS.VehiclePage;
import com.loanmart.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class LOSEditApplication extends TestBase {
	
	private AccountLookupPage accountLookupPage;
	private VehiclePage vehiclePage;
	private MainPage mainPage;
	private AddressPage addressPage;
	private IncomePage incomePage;
	private FinalizePage finalizePage;
	private TermsPage termsPage;
	private AdditionalInfoPage additionalInfoPage;
	private String testURL;
	private String https = "https://";
	private static final ImmutableList<String> CCB_STATES = ImmutableList.of("Delaware","Distric of Columbia","Florida","Illinois","Indiana","Kansas","Kentucky","Michigan","Mississippi","Ohio","Oklahoma","Oregon","South Dakota","Tennessee","Texas","Washington");
	public static String suiteName = "LOSEditApplication";
	private int failureCounter;
	
	@BeforeTest(groups = {"ApplyAndEdit","EditLOS", "Regression"})
	public void setUpClass() {
		testURL = https+devEnvironment+config.getProperty("baseurl_los")+ "ael/lookup";
		log.debug("Navigated to => " + testURL);
		accountLookupPage = new AccountLookupPage(driver,suiteName);
		vehiclePage = new VehiclePage(driver,suiteName);
		mainPage = new MainPage(driver, suiteName);
		addressPage = new AddressPage(driver, suiteName);
		incomePage = new IncomePage(driver, suiteName);
		finalizePage = new FinalizePage(driver,suiteName);
		termsPage = new TermsPage(driver, suiteName);
		additionalInfoPage = new AdditionalInfoPage(driver, suiteName);
		failureCounter = 0;
	}
	
	@BeforeMethod(groups = {"ApplyAndEdit","EditLOS", "Regression"})
	public void launchBrowser() {
		driver.get(testURL);
	}
	
	@AfterMethod(groups = {"ApplyAndEdit","EditLOS", "Regression"})
	public void failureCheckup() {
		if(failureCounter > 0)
			CustomListeners.testLocal.log(LogStatus.FAIL, "THERE ARE TEST ERROS");
	}
	
	@Test(groups = {"ApplyAndEdit","EditLOS", "Regression"}, dependsOnGroups= "CCB", enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void EditLOSApplication(HashMap<String, String> data) throws InterruptedException {
		
		if(CCB_STATES.contains(data.get("State"))) {
			CustomListeners.testLocal.setDescription("Edit Application LOS -- "+data.get("State")+"--"+data.get("LoanNumber"));
			accountLookupPage(data, "CCB");
			vehiclePage(data);
			addressPage(data);
			incomePage(data);
			finalizePage(data);
			termsPage(data);
			additionalInfoPage(data);
		}
		else
			accountLookupPage(data, "AEL");
		
		if(failureCounter> 0) {
			CustomListeners.testLocal.log(LogStatus.FAIL, " TEST COMPLETED ==> THERE IS/ARE "+failureCounter+" TEST ERROR(S)");
			Assert.fail("One or more test have failed --- Please reference test report for more details");
			//Assert.fail();
		}

	}
	
	private void accountLookupPage(HashMap<String, String> info, String productType) {
		log.debug("Inside Account Lookup Page");
		CustomListeners.testLocal.log(LogStatus.INFO, "--- ACCOUNT LOOKUP STARTED ---");
		accountLookupPage.setSearchLoanNumber(info.get("LoanNumber"));
		accountLookupPage.clickSearchApplicationsButton();
		accountLookupPage.waitForPageLoad();
		TestUtil.captureScreenshot("LookUpPage");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		CustomListeners.testLocal.log(LogStatus.INFO, "VERIFYING CURRENT STATUS");
		failureCounter(accountLookupPage.verifyAssertEquals("Value Vehicle", accountLookupPage.getStatus()));
		CustomListeners.testLocal.log(LogStatus.INFO, "VERIFYING PHONE NUMBER(S)");
		failureCounter(accountLookupPage.verifyAssertContains(TestUtil.convertToPhoneFormat(info.get("MobileNumber")), accountLookupPage.getPhoneNumbers()));
		CustomListeners.testLocal.log(LogStatus.INFO, "VERIFYING NAME");
		failureCounter(accountLookupPage.verifyAssertEquals(info.get("FirstName")+" "+info.get("LastName"), accountLookupPage.getName()));
		CustomListeners.testLocal.log(LogStatus.INFO, "VERIFYING DOB");
		failureCounter(accountLookupPage.verifyAssertEquals(info.get("DOB"), accountLookupPage.getDOB()));
		CustomListeners.testLocal.log(LogStatus.INFO, "VERIFYING PRODUCT TYPE");
		failureCounter(accountLookupPage.verifyAssertEquals(productType, accountLookupPage.getProduct()));
		CustomListeners.testLocal.log(LogStatus.INFO, "--- ACCOUNT LOOKUP COMPLETED ---");
		accountLookupPage.clickOpenApplicationButton();
	}
	
	private void vehiclePage(HashMap<String, String> info) throws InterruptedException {
		log.debug("Inside Vehicle Page");
		CustomListeners.testLocal.log(LogStatus.INFO, "--- VEHICLE PAGE STARTED ---");
		vehiclePage.waitForVehiclePageLoad();
		vehiclePage.setColor(info.get("Color"));
		vehiclePage.setTitleStatus(info.get("TitleStatus"));
		vehiclePage.setStateTitleRegistered(info.get("StateTitle"));
		vehiclePage.setVehiclePaidOff(info.get("PaidOff"));
		vehiclePage.setRunningAndNoDamage(info.get("Running"));
		vehiclePage.setHowLongHaveYouOwnedThisCar(info.get("HowLongHaveYouOwnedThisCar"));
		vehiclePage.setDoYouDriveThisCarToWork(info.get("DoYouDriveThisCarToWork"));
		vehiclePage.setAreYouAbleToProvideProofOfInsurance(info.get("AreYouAbleToProvideProofOfInsurance"));
		vehiclePage.setHowLongHaveYouBeenInYourCurrentJob(info.get("HowLongHaveYouBeenInYourCurrentJob"));
		vehiclePage.setWillYouBeAbleToDriveYourCar(info.get("WillYouBeAbleToDriveYourCar"));
		vehiclePage.setAreYouAbleToProvideProof(info.get("AreYouAbleToProvideProofBankAccount"));
		vehiclePage.setDoYouHaveAnotherCar(info.get("DoYouHaveAnotherCar"));
		TestUtil.captureScreenshot("VehiclePage");
		CustomListeners.testLocal.log(LogStatus.INFO, "--- VEHICLE PAGE COMPLETED ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		mainPage.clickNextButton();
	}
	
	private void addressPage(HashMap<String, String> info){
		log.debug("Inside Address Page");
		CustomListeners.testLocal.log(LogStatus.INFO, "--- ADDRESS PAGE STARTED ---");
		mainPage.waitForNextButttonVisibility();
		addressPage.copyPhysicalAddressButtonCLick();
		TestUtil.captureScreenshot("addressPage");
		CustomListeners.testLocal.log(LogStatus.INFO, "--- ADDRESS PAGE COMPLETED ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		mainPage.clickNextButton();
	}
	
	private void incomePage(HashMap<String, String> info) throws InterruptedException {
		log.debug("Inside Address Page");
		CustomListeners.testLocal.log(LogStatus.INFO, "--- INCOME PAGE STARTED ---");
		mainPage.waitForNextButttonVisibility();
		incomePage.setIncometype(info.get("IncomeType"));
		failureCounter(incomePage.verifyAssertEquals(info.get("GrossIncome"), incomePage.getGrossMonthlyIncome()));
		incomePage.setEmployerName(info.get("EmployerName"));
		incomePage.setEmployeeTitle(info.get("EmployeeTitle"));
		incomePage.setYearsAtEmployer(info.get("YearsAtEmployer"));
		incomePage.setMonthsAtEmployer(info.get("MonthsAtEmployer"));
		TestUtil.captureScreenshot("incomePage");
		CustomListeners.testLocal.log(LogStatus.INFO, "--- INCOME PAGE COMPLETED ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		mainPage.clickNextButton();
		
		
	}
	
	private void finalizePage(HashMap<String, String> info) {
		log.debug("Inside Finalize Page");
		CustomListeners.testLocal.log(LogStatus.INFO, "--- FINALIZE PAGE STARTED ---");
		finalizePage.waitForFinalizePageLoad();
		failureCounter(finalizePage.verifyAssertEqual(info.get("DOB"),finalizePage.getDateOfBirth()));
		finalizePage.setSsn(info.get("SSN"));
		finalizePage.setCreditPull();
		finalizePage.setMilitaryActive();
		finalizePage.setInitial();
		TestUtil.captureScreenshot("finalizePage");
		CustomListeners.testLocal.log(LogStatus.INFO, "--- INCOME PAGE COMPLETED ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		mainPage.clickPullCreditButton();
	}
	
	private void termsPage(HashMap<String, String> info) {
		log.debug("Inside Terms Page");
		CustomListeners.testLocal.log(LogStatus.INFO, "--- TERMS PAGE STARTED ---");
		termsPage.waitForTermsPageLoad();
		mainPage.waitForNextButttonVisibility();
		TestUtil.captureScreenshot("termsPage");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		failureCounter(termsPage.verifyAssertEquals(info.get("FICO"), termsPage.getFICO()));
		failureCounter(termsPage.verifyNotEquals("0", termsPage.getCustomerScore()));
		failureCounter(termsPage.verifyNotEquals("0", termsPage.getNeustarAddressScore()));
		failureCounter(termsPage.verifyAssertEquals("Standard", termsPage.getProgramType()));
		CustomListeners.testLocal.log(LogStatus.INFO, "--- TERMS PAGE COMPLETED ---");
		mainPage.clickNextButton();
		
	}
	
	private void additionalInfoPage(HashMap<String, String>info) {
		log.debug("Inside Additional Info Page");
		CustomListeners.testLocal.log(LogStatus.INFO, "--- ADDITIONAL INFO PAGE STARTED ---");
		additionalInfoPage.waitForAdditionalInfoPageLoad();
		additionalInfoPage.setReasonForLoan(info.get("ReasonForLoan"));
		additionalInfoPage.setDriverLicenseNumber(info.get("DriverLicenseNumber"));
		additionalInfoPage.setDriverLicenseState(info.get("DriverLicenseState"));
		mainPage.clickSaveButton();
		additionalInfoPage.savingAdditionalInfoPageLoad();
		TestUtil.captureScreenshot("additionalInfoPage");
		CustomListeners.testLocal.log(LogStatus.INFO, "--- ADDITIONAL INFO PAGE COMPLETED ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		
	}
	
	private void failureCounter(boolean pass) {
		if(!pass)
			failureCounter++;
			
	}
}
