package com.loanmart.mainwebsite;

import java.util.HashMap;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.loanmart.base.TestBase;
import com.loanmart.pages.mainwebsite.AddressInformationPage;
import com.loanmart.pages.mainwebsite.ContactInformationPage;
import com.loanmart.pages.mainwebsite.CustomizeYourOfferPage;
import com.loanmart.pages.mainwebsite.SuccessPage;
import com.loanmart.pages.mainwebsite.VehicleInformationPage;
import com.loanmart.utilities.TestUtil;




public class ApplyForLoan extends TestBase {
	private ContactInformationPage contactInfopage;
	private VehicleInformationPage vehicleInformationPage;
	private AddressInformationPage addressInformationPage;
	private CustomizeYourOfferPage customizeYourOfferPage;
	private SuccessPage successPage;
	private String testURL;
	private String https = "https://";
	
	
	@BeforeTest(groups = "submit_application")
	public void setUpClass() {
		System.out.println("*********************INSIDE setUpClass***************************************");

		testURL = https+devEnvironment+config.getProperty("baseurl_mainwebsite")+ "application/application";
		System.out.println(testURL);

		log.debug("Navigated to => " + testURL);
		contactInfopage = new ContactInformationPage(driver);
		vehicleInformationPage = new VehicleInformationPage(driver);
		addressInformationPage = new AddressInformationPage(driver);
		customizeYourOfferPage = new CustomizeYourOfferPage(driver);
		successPage = new SuccessPage(driver);
	}
	
	@BeforeMethod(groups = "submit_application")
	public void launchBrowser() {
		System.out.println("*****************INSIDE LAUNCHBROWSER******************************************");
		driver.get(testURL);
	}
	
	@Test(groups = "submit_application", enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void ApplyForLoanTest( HashMap<String, String> data ) throws InterruptedException {
		System.out.println("*************************"+System.getProperty("DEV_ENV")+"**********************************");

		contactInformation(data);
		vehicleInformation(data);
		addressInformation(data);
		customizeYourOfferPage();
		Assert.assertNotEquals("", successPage());

	}
	
	public void contactInformation(HashMap<String,String> info) {
		
		log.debug("Inside Input Contact Information");
		
		contactInfopage.setFirstName(info.get("FirstName"));
		contactInfopage.setLastName(info.get("LastName"));
		contactInfopage.setEmail("random-" + UUID.randomUUID().toString() + "@example.com");
		contactInfopage.setStateOfResidence(info.get("State"));
		contactInfopage.setDoYouHaveAnAutomobile(info.get("DoYouHaveAnAutomobile"));
		if(contactInfopage.isESignedConsentVisible() && contactInfopage.isThirdPartiesConsentVisible()) {
			contactInfopage.setESignedConsent(info.get("ESignedConsent"));
			contactInfopage.setThirdPartiesConsent(info.get("ThirdPartiesConsent"));
		}else if(contactInfopage.isESignedConsentVisible()) {
			contactInfopage.setESignedConsent(info.get("ESignedConsent"));
		}else if(contactInfopage.isThirdPartiesConsentVisible()) {
			contactInfopage.setThirdPartiesConsent("ThirdPartiesConsent");
		}

		log.debug("Contact Information successfully Submitted");
		
		contactInfopage.clickNextButton();
		
	}
	
	public void vehicleInformation(HashMap<String,String> info) {
		vehicleInformationPage.waitForVehiclePageLoad();
		log.debug("Inside Input Vehicle Information");
		
		vehicleInformationPage.setCarYear(info.get("CarYear"));
		vehicleInformationPage.setCarMake(info.get("CarMake"));
		vehicleInformationPage.setCarModel(info.get("CarModel"));
		vehicleInformationPage.setCarStyle(info.get("CarStyle"));
		vehicleInformationPage.setCarMileage(info.get("CarMileage"));
		vehicleInformationPage.setMobileNumber(info.get("MobileNumber"));
		vehicleInformationPage.setTerms(info.get("TextTerms"));
		vehicleInformationPage.setActiveMilitary(info.get("ActiveMilitary"));
		
		log.debug("Vehicle Information successfully Submitted");
		
		vehicleInformationPage.clickNextButton();

	}
	
	public void addressInformation(HashMap<String,String> info) throws InterruptedException {
		addressInformationPage.waitForAddressPageLoad();
		log.debug("Inside Input Address Information");
		
		addressInformationPage.setStreetAddress(info.get("StreetAddress"));
		addressInformationPage.setCity(info.get("City"));
		addressInformationPage.setState(info.get("State"));
		addressInformationPage.setZipCode(info.get("ZipCode"));
		addressInformationPage.setOwnership(info.get("Ownership"));
		addressInformationPage.setMortgageRent(info.get("MortgageRent"));
		addressInformationPage.setLivedYears(info.get("LivedYears"));
		addressInformationPage.setLivedMonths(info.get("LivedMonths"));
		addressInformationPage.setGrossIncome(info.get("GrossIncome"));
		if(addressInformationPage.isOtherIncomeVisible())
			addressInformationPage.setOtherIncome(info.get("OtherIncome"));
		addressInformationPage.setHomeNumber(info.get("HomeNumber"));
		addressInformationPage.setDob(info.get("DOB"));
		addressInformationPage.setSsn(info.get("SSN"));
		if(addressInformationPage.isTermsAnConditionsVisible())
			addressInformationPage.setTerms(info.get("Terms"));
		
		log.debug("Address Information successfully Submitted");
		
		if(addressInformationPage.isClickCcbSubmitButtonVisible())
			addressInformationPage.clickCcbSubmitButton();
		else
			addressInformationPage.clickCustomizeMyOfferButton();
		
	}
	
	public void customizeYourOfferPage() {
		
		customizeYourOfferPage.waitForCustomizeYourOfferPageLoad();
		log.debug("Inside Customize Your Offer");
		log.debug("Customize Your Offer successfully Submitted");
		customizeYourOfferPage.clickSubmitAndGetMoneyButton();
	}
	
	public String successPage() {
		successPage.waitForSuccessPageLoad();
		log.debug("Inside Success Page");
		String loanNumber = successPage.getLoanNumber();
		System.out.println("**********LOAN NUMBER "+loanNumber+" ********************************");
		return loanNumber;
	}
	
	/*
	 * @Test(groups = "nothing") public void testTest() {
	 * System.out.println("In test test with no group"); }
	 */

}
