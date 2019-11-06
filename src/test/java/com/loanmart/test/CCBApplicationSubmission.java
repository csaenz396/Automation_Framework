package com.loanmart.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import com.loanmart.base.TestBase;
import com.loanmart.listeners.CustomListeners;
import com.loanmart.pages.mainwebsite.AddressInformationPage;
import com.loanmart.pages.mainwebsite.ContactInformationPage;
import com.loanmart.pages.mainwebsite.CustomizeYourOfferPage;
import com.loanmart.pages.mainwebsite.SuccessPage;
import com.loanmart.pages.mainwebsite.VehicleInformationPage;
import com.loanmart.utilities.ExcelReader;
import com.loanmart.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CCBApplicationSubmission extends TestBase{
	
	private ContactInformationPage contactInfopage;
	private VehicleInformationPage vehicleInformationPage;
	private AddressInformationPage addressInformationPage;
	private CustomizeYourOfferPage customizeYourOfferPage;
	private SuccessPage successPage;
	private String testURL;
	private String https = "https://";
	private static final ImmutableList<String> CCB_STATES = ImmutableList.of("Delaware","Distric of Columbia","Florida","Illinois","Indiana","Kansas","Kentucky","Michigan","Mississippi","Ohio","Oklahoma","Oregon","South Dakota","Tennessee","Texas","Washington");
	public static String suiteName = "ApplicationSubmissioCCB";
	private int rowIndex;
	private String loanNumber;
	

	
	@BeforeTest(groups = {"ApplyAndEdit","CCB"})
	public void setUpClass() {
		rowIndex = 0;
		testURL = https+devEnvironment+config.getProperty("baseurl_mainwebsite")+ "application/application";

		log.debug("Navigated to => " + testURL);
		contactInfopage = new ContactInformationPage(driver, suiteName);
		vehicleInformationPage = new VehicleInformationPage(driver,suiteName);
		addressInformationPage = new AddressInformationPage(driver,suiteName);
		customizeYourOfferPage = new CustomizeYourOfferPage(driver,suiteName);
		successPage = new SuccessPage(driver, suiteName);
	}
	
	@BeforeMethod(groups = {"ApplyAndEdit","CCB"})
	public void launchBrowser() {
		driver.get(testURL);
	}
	
	/*
	 * @AfterMethod(groups = "CCB") public void setLoanNumber() {
	 * System.out.println(rowIndex); System.out.println(loanNumber);
	 * ExcelReader.addLoanNumber(rowIndex, "ApplicationSubmissionCCB", loanNumber);
	 * }
	 */
	
	@Test(groups = {"ApplyAndEdit","CCB"}, enabled = true, dataProviderClass = TestUtil.class, dataProvider= "dp")
	public void ApplicationSubmissionCCB( HashMap<String, String> data ) throws InterruptedException  {

		
		if(CCB_STATES.contains(data.get("State"))) {
			contactInformation(data);
			vehicleInformation(data);
			addressInformation(data);
			customizeYourOfferPage();
			successPage();
		}
		
		rowIndex++;
		CustomListeners.testLocal.setDescription("CCB Submission Application -- "+data.get("State")+"--"+loanNumber);
		ExcelReader.addLoanNumber(rowIndex, "ApplicationSubmissionCCB", loanNumber);
	}
	
	private void contactInformation(HashMap<String,String> info) {
		
		log.debug("Inside Input Contact Information");

		CustomListeners.testLocal.log(LogStatus.INFO, "---  CONTACT INFO PAGE STARTED  ---");
		
		contactInfopage.setFirstName(info.get("FirstName"));
		contactInfopage.setLastName(info.get("LastName"));
		contactInfopage.setEmail("random-" + UUID.randomUUID().toString() + "@example.com");
		contactInfopage.setStateOfResidence(info.get("State"));
		Assert.assertTrue(ccbBoxVisibilityContactPage(info.get("State")));
		Assert.assertTrue(ccLogoVisibilityContactPage());
		contactInfopage.setDoYouHaveAnAutomobile(info.get("DoYouHaveAnAutomobile"));
		Assert.assertTrue(checkBoxesVisibilityContactPage());
		Assert.assertTrue(isStateFoundInDisclosureCCB(info.get("State")));
		log.debug("Contact Information successfully Submitted");
		TestUtil.captureScreenshot("ContactPage");
		contactInfopage.clickNextButton();
		CustomListeners.testLocal.log(LogStatus.INFO, "---  CONTACT INFO PAGE COMPLETED  ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
	}
	
	private void vehicleInformation(HashMap<String,String> info) throws InterruptedException {
		vehicleInformationPage.waitForVehiclePageLoad();
		log.debug("Inside Input Vehicle Information");
		CustomListeners.testLocal.log(LogStatus.INFO, "---  VEHICLE INFO PAGE STARTED  ---");
		
		vehicleInformationPage.setCarYear(info.get("CarYear"));
		vehicleInformationPage.setCarMake(info.get("CarMake"));
		vehicleInformationPage.setCarModel(info.get("CarModel"));
		vehicleInformationPage.setCarStyle(info.get("CarStyle"));
		vehicleInformationPage.setCarMileage(info.get("CarMileage"));
		vehicleInformationPage.setMobileNumber(info.get("MobileNumber"));
		Assert.assertTrue(isCccbDisclosureVisibleVehiclePage());
		
		vehicleInformationPage.setTerms(info.get("TextTerms"));
		vehicleInformationPage.setActiveMilitary(info.get("ActiveMilitary"));
		
		log.debug("Vehicle Information successfully Submitted");
		TestUtil.captureScreenshot("VehiclePage");
		vehicleInformationPage.clickNextButton();
		
		CustomListeners.testLocal.log(LogStatus.INFO, "---  VEHICLE INFO PAGE COMPLETED  ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
	}
	
	private void addressInformation(HashMap<String,String> info) throws InterruptedException {
		addressInformationPage.waitForAddressPageLoad();
		log.debug("Inside Input Address Information");
		CustomListeners.testLocal.log(LogStatus.INFO, "---  ADDRESS INFO PAGE STARTED  ---");
		
		addressInformationPage.setStreetAddress(info.get("StreetAddress"));
		addressInformationPage.setCity(info.get("City"));
		addressInformationPage.setState(info.get("State"));
		addressInformationPage.setZipCode(info.get("ZipCode"));
		addressInformationPage.setOwnership(info.get("Ownership"));
		addressInformationPage.setMortgageRent(info.get("MortgageRent"));
		addressInformationPage.setLivedYears(info.get("LivedYears"));
		addressInformationPage.setLivedMonths(info.get("LivedMonths"));
		addressInformationPage.setGrossIncome(info.get("GrossIncome"));
		addressInformationPage.setHomeNumber(info.get("HomeNumber"));
		addressInformationPage.setDob(info.get("DOB"));
		//addressInformationPage.setSsn(info.get("SSN"));
		Assert.assertTrue(addressInformationPage.verifyStateInTermsAndConditions(info.get("State")));
		addressInformationPage.setTerms(info.get("Terms"));
		log.debug("Address Information successfully Submitted");
		TestUtil.captureScreenshot("AddressPage");
		addressInformationPage.clickCcbSubmitButton();
		CustomListeners.testLocal.log(LogStatus.INFO, "---  ADDRESS INFO PAGE COMPLETED  ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		
	}
	
	private void customizeYourOfferPage() {
		customizeYourOfferPage.waitForCustomizeYourOfferPageLoad();
		log.debug("Inside Customize Your Offer");
		CustomListeners.testLocal.log(LogStatus.INFO, "---  CUSTOMIZE YOUR OFFER PAGE STARTED  ---");
		log.debug("Customize Your Offer successfully Submitted");
		TestUtil.captureScreenshot("CustomizeYourOfferPage");
		customizeYourOfferPage.clickSubmitButton();
		CustomListeners.testLocal.log(LogStatus.INFO, "---  CUSTOMIZE YOUR OFFER PAGE COMPLETED  ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		
	}
	
	private void successPage() {
		successPage.waitForSuccessPageLoad();
		log.debug("Inside Success Page");
		CustomListeners.testLocal.log(LogStatus.INFO, "---  SUCCESS PAGE STARTED  ---");
		loanNumber = successPage.getLoanNumber();
		Assert.assertTrue(successTextVisibilitySuccessPage());
		Assert.assertNotEquals("", loanNumber);
		CustomListeners.testLocal.log(LogStatus.INFO, "---  SUCCESS PAGE COMPLETED  ---");
	}

	private boolean checkBoxesVisibilityContactPage() {
		if(contactInfopage.isNotESignedConsentVisible() && contactInfopage.isNotThirdPartiesConsentVisible())
			return true;
		else
			return false;
	}
	
	private boolean ccbBoxVisibilityContactPage(String state) {
		if(contactInfopage.isCcbBoxVisible(state,wait))
			return true;
		else
			return false;
	}
	
	private boolean ccLogoVisibilityContactPage() {
		if(contactInfopage.isCcbLoanMartLogoVisible())
			return true;
		else
			return false;
	}
	
	private boolean isStateFoundInDisclosureCCB(String state) {
		if(contactInfopage.isStateFoundinDisclosureCCB(state))
			return true;
		else
			return false;
	}
	
	private boolean isCccbDisclosureVisibleVehiclePage() {
		if(vehicleInformationPage.CheckCcbConsent())
			return true;
		else
			return false;
	}
	
	private boolean successTextVisibilitySuccessPage() {
		if(successPage.isSuccessMessageVisibleCCB())
			return true;
		else
			return false;
	}

}
