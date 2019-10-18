package com.loanmart.mainwebsite;

import java.util.HashMap;
import java.util.UUID;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
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
	
	
	@BeforeTest
	public void setUpClass() {
		System.out.println("*********************INSIDE setUpClass***************************************");

		testURL = config.getProperty("baseurl_mainwebsite")+ "application/application";
		System.out.println(testURL);

		log.debug("Navigated to => " + testURL);
		contactInfopage = new ContactInformationPage(driver);
		vehicleInformationPage = new VehicleInformationPage(driver);
		addressInformationPage = new AddressInformationPage(driver);
		customizeYourOfferPage = new CustomizeYourOfferPage(driver);
		successPage = new SuccessPage(driver);
	}
	
	@BeforeMethod
	public void launchBrowser() {
		System.out.println("*****************INSIDE LAUNCHBROWSER******************************************");
		driver.get(testURL);
	}
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void ApplyForLoanTest( HashMap<String, String> data ) throws InterruptedException {
		log.debug("Inside Input Contact Information");
		
		contactInfopage.setFirstName(data.get("FirstName"));
		contactInfopage.setLastName(data.get("LastName"));
		contactInfopage.setEmail("random-" + UUID.randomUUID().toString() + "@example.com");
		contactInfopage.setStateOfResidence(data.get("State"));
		contactInfopage.setDoYouHaveAnAutomobile(data.get("DoYouHaveAnAutomobile"));
		contactInfopage.setESignedConsent(data.get("ESignedConsent"));
		contactInfopage.setThirdPartiesConsent(data.get("ThirdPartiesConsent"));
		log.debug("Contact Information successfully Submitted");
		
		contactInfopage.clickNextButton();
		vehicleInformationPage.waitForVehiclePageLoad();
		log.debug("Inside Input Vehicle Information");
		
		vehicleInformationPage.setCarYear(data.get("CarYear"));
		vehicleInformationPage.setCarMake(data.get("CarMake"));
		vehicleInformationPage.setCarModel(data.get("CarModel"));
		vehicleInformationPage.setCarStyle(data.get("CarStyle"));
		vehicleInformationPage.setCarMileage(data.get("CarMileage"));
		vehicleInformationPage.setMobileNumber(data.get("MobileNumber"));
		vehicleInformationPage.setTerms(data.get("TextTerms"));
		vehicleInformationPage.setActiveMilitary(data.get("ActiveMilitary"));
		
		log.debug("Vehicle Information successfully Submitted");
		
		vehicleInformationPage.clickNextButton();
		addressInformationPage.waitForAddressPageLoad();
		log.debug("Inside Input Address Information");
		
		addressInformationPage.setStreetAddress(data.get("StreetAddress"));
		addressInformationPage.setCity(data.get("City"));
		addressInformationPage.setState(data.get("State"));
		addressInformationPage.setZipCode(data.get("ZipCode"));
		addressInformationPage.setOwnership(data.get("Ownership"));
		addressInformationPage.setMortgageRent(data.get("MortgageRent"));
		addressInformationPage.setLivedYears(data.get("LivedYears"));
		addressInformationPage.setLivedMonths(data.get("LivedMonths"));
		addressInformationPage.setGrossIncome(data.get("GrossIncome"));
		addressInformationPage.setOtherIncome(data.get("OtherIncome"));
		addressInformationPage.setHomeNumber(data.get("HomeNumber"));
		addressInformationPage.setDob(data.get("DOB"));
		addressInformationPage.setSsn(data.get("SSN"));
		//addressInformationPage.setTerms(data.get("Terms"));  there is no terms to accept on this test, this produces a nullpointer exception
		
		log.debug("Address Information successfully Submitted");
		
		addressInformationPage.clickCustomizeMyOfferButton();
		customizeYourOfferPage.waitForCustomizeYourOfferPageLoad();
		log.debug("Inside Customize Your Offer");


		log.debug("Customize Your Offer successfully Submitted");
		
		customizeYourOfferPage.clickSubmitAndGetMoneyButton();
		successPage.waitForSuccessPageLoad();
		log.debug("Inside Success Page");
		System.out.println("**********LOAN NUMBER"+successPage.getLoanNumber()+"********************************");
	}

}
