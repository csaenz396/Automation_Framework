package com.loanmart.mainwebsite;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class ApplyForLoanVTWO extends TestBase {
	
	@Test(groups="the_only_one")
	public void ApplyForLoanTest() {
		System.out.println("test on a different script");
	}

}
