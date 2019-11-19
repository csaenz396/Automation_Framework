package com.loanmart.test;

import java.util.HashMap;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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


public class AELApplicationSubmission extends TestBase {
	
	private ContactInformationPage contactInfopage;
	private VehicleInformationPage vehicleInformationPage;
	private AddressInformationPage addressInformationPage;
	private CustomizeYourOfferPage customizeYourOfferPage;
	private SuccessPage successPage;
	private String testURL;
	private String https = "https://";
	public static String suiteName = "ApplicationSubmissionAEL";
	private int rowIndex;
	private String loanNumber;
	
	@BeforeTest(groups = {"AELApplyAndEdit", "AEL", "Regression"})
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
	
	@BeforeMethod(groups = {"AELApplyAndEdit","AEL", "Regression"})
	public void launchBrowser() {
		driver.get(testURL);
	}
	
	@Test(groups = {"AELApplyAndEdit","AEL", "Regression"}, enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void ApplicationSubmissionAEL( HashMap<String, String> data ) throws InterruptedException {

		contactInformation(data);
		vehicleInformation(data);
		addressInformation(data);
		customizeYourOfferPage();
		successPage();
		rowIndex++;
		CustomListeners.testLocal.setDescription("AEL Submission Application -- "+data.get("State")+"--"+loanNumber);
		ExcelReader.addLoanNumber(rowIndex, "ApplicationSubmissionAEL", loanNumber);

	}
	
	private void contactInformation(HashMap<String,String> info) {
		
		log.debug("Inside Input Contact Information");

		CustomListeners.testLocal.log(LogStatus.INFO, "---  CONTACT INFO PAGE STARTED  ---");
		
		contactInfopage.setFirstName(info.get("FirstName"));
		contactInfopage.setLastName(info.get("LastName"));
		contactInfopage.setEmail("random-" + UUID.randomUUID().toString() + "@example.com");
		contactInfopage.setStateOfResidence(info.get("State"));
		contactInfopage.setDoYouHaveAnAutomobile(info.get("DoYouHaveAnAutomobile"));
		
		Assert.assertTrue(checkBoxesVisibilityContactPage());
		contactInfopage.setESignedConsent(info.get("ESignedConsent"));
		contactInfopage.setThirdPartiesConsent(info.get("ThirdPartiesConsent"));
		

		log.debug("Contact Information successfully Submitted");
		TestUtil.captureScreenshot("ContactPage");
		contactInfopage.clickNextButton();
		CustomListeners.testLocal.log(LogStatus.INFO, "---  CONTACT INFO PAGE COMPLETED  ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));


	}
	
	private void vehicleInformation(HashMap<String,String> info) {
		vehicleInformationPage.waitForVehiclePageLoad();
		log.debug("Inside Input Vehicle Information");
		CustomListeners.testLocal.log(LogStatus.INFO, "---  VEHICLE INFO PAGE STARTED  ---");
		
		vehicleInformationPage.setCarYear(info.get("CarYear"));
		vehicleInformationPage.setCarMake(info.get("CarMake"));
		vehicleInformationPage.setCarModel(info.get("CarModel"));
		vehicleInformationPage.setCarStyle(info.get("CarStyle"));
		vehicleInformationPage.setCarMileage(info.get("CarMileage"));
		vehicleInformationPage.setMobileNumber(info.get("MobileNumber"));
		
		Assert.assertTrue(checkBoxesVisibilityVehiclePage());
		vehicleInformationPage.setTerms(info.get("TextTerms"));
		vehicleInformationPage.setActiveMilitary(info.get("ActiveMilitary"));
		
		log.debug("Vehicle Information successfully Submitted");
		TestUtil.captureScreenshot("VehiclePage");
		vehicleInformationPage.clickNextButton();
		
		CustomListeners.testLocal.log(LogStatus.INFO, "---  VEHICLE INFO PAGE COMPLETED  ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));

	}
	
	private void addressInformation(HashMap<String,String> info) throws InterruptedException{
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
		
		Assert.assertTrue(otherIncomeVisibilityAddressPage());
		addressInformationPage.setOtherIncome(info.get("OtherIncome"));
		addressInformationPage.setHomeNumber(info.get("HomeNumber"));
		addressInformationPage.setDob(info.get("DOB"));
		addressInformationPage.setSsn(info.get("SSN"));
		
		Assert.assertTrue(termAndConditionsVisibilityAddressPage());
		
		log.debug("Address Information successfully Submitted");
		TestUtil.captureScreenshot("AddressPage");
		addressInformationPage.clickCustomizeMyOfferButton();
		
		CustomListeners.testLocal.log(LogStatus.INFO, "---  ADDRESS INFO PAGE COMPLETED  ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		
	}
	
	private void customizeYourOfferPage() {
		
		customizeYourOfferPage.waitForCustomizeYourOfferPageLoad();
		log.debug("Inside Customize Your Offer");
		CustomListeners.testLocal.log(LogStatus.INFO, "---  CUSTOMIZE YOUR OFFER PAGE STARTED  ---");
		log.debug("Customize Your Offer successfully Submitted");
		TestUtil.captureScreenshot("CustomizeYourOfferPage");
		customizeYourOfferPage.clickSubmitAndGetMoneyButton();
		CustomListeners.testLocal.log(LogStatus.INFO, "---  CUSTOMIZE YOUR OFFER PAGE COMPLETED  ---");
		CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
	}
	
	private void successPage() {
		successPage.waitForSuccessPageLoad();
		log.debug("Inside Success Page");
		CustomListeners.testLocal.log(LogStatus.INFO, "---  SUCCESS PAGE STARTED  ---");
		loanNumber = successPage.getLoanNumber();
		Assert.assertTrue(successTextVisibilitySuccessPage());
		Assert.assertNotEquals("", successPage.getLoanNumber());
		CustomListeners.testLocal.log(LogStatus.INFO, "---  SUCCESS PAGE COMPLETED  ---");
	}

	private boolean checkBoxesVisibilityContactPage() {
		if(contactInfopage.isESignedConsentVisible() && contactInfopage.isThirdPartiesConsentVisible())
			return true;
		else
			return false;
	}
	
	private boolean checkBoxesVisibilityVehiclePage() {
		if(vehicleInformationPage.isTermsCheckBoxvisible() && vehicleInformationPage.isMilitaryCheckBoxVisible())
			return true;
		else
			return false;
	}
	
	private boolean otherIncomeVisibilityAddressPage() {
		if(addressInformationPage.isOtherIncomeVisible())
			return true;
		else
			return false;
	}
	
	private boolean termAndConditionsVisibilityAddressPage() {
		if(addressInformationPage.isTermsAndConditionsNotVisible())
			return true;
		else
			return false;
	}
	
	private boolean successTextVisibilitySuccessPage() {
		if(successPage.isSuccessMessageVisible())
			return true;
		else
			return false;
	}
}
