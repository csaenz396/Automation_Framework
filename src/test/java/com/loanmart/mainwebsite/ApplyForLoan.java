package com.loanmart.mainwebsite;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.loanmart.base.TestBase;
import com.loanmart.pages.mainwebsite.ContactInformationPage;
import com.loanmart.utilities.TestUtil;

public class ApplyForLoan extends TestBase {
	private ContactInformationPage contactInfopage;
	private String testURL;
	
	@BeforeTest
	public void setUpClass() {
		System.out.println("*********************INSIDE setUpClass***************************************");

		testURL = config.getProperty("baseurl_mainwebsite")+ "application/application";
		System.out.println(testURL);

		log.debug("Navigated to => " + testURL);
		contactInfopage = new ContactInformationPage(driver);
	}
	
	@BeforeMethod
	public void launchBrowser() {
		System.out.println("*****************INSIDE LAUNCHBROWSER******************************************");
		driver.get(testURL);
	}
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void ApplyForLoanTest(HashMap<String, String> data) {
		log.debug("Inside Input Contact Information");
		
		contactInfopage.setFirstName(data.get("FirstName"));
		contactInfopage.setLastName(data.get("LastName"));
		contactInfopage.setEmail("random-" + UUID.randomUUID().toString() + "@example.com");
		contactInfopage.setStateOfResidence(data.get("State"));
		contactInfopage.setDoYouHaveAnAutomobile(data.get("DoYouHaveAnAutomobile"));
		contactInfopage.setESignedConsent(data.get("ESignedConsent"));
		contactInfopage.setThirdPartiesConsent(data.get("ThirdPartiesConsent"));
		log.debug("Contact Information successfully Submitted");
	}

}
