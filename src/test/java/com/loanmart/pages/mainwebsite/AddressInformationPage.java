package com.loanmart.pages.mainwebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.loanmart.actions.Actions;
import com.loanmart.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class AddressInformationPage extends TestBase{

	WebDriver webDriver;
	Actions action;
	
	public AddressInformationPage(WebDriver driver, String testName) {
		this.webDriver = driver;
		action = new Actions(testName);
	}
	
	public void waitForAddressPageLoad() {
		action.waitForPageLoad(byStreetAddress);
	}
	
	By byStreetAddress =By.id("street_address");
	public WebElement streetAddress() {
		return action.webElement(byStreetAddress);
	}

	public void setStreetAddress(String value) {
		action.setTextBox(byStreetAddress, value, "StreetAddress");
	}

	By byCity =By.id("city");
	public WebElement city() {
		return action.webElement(byCity);
	}

	public void setCity(String value ) {
		action.setTextBox(byCity, value, "City");
	}
	
	By byState = By.id("state");
	public WebElement state() {
		return action.webElement(byState);
	}

	public void setState(String value) {
		action.setDropDown(byState, value, "State");
	}
	
	By byZipCode = By.id("zip_code");
	public WebElement zipCode() {
		return action.webElement(byZipCode);
	}

	public void setZipCode(String value) {
		action.setTextBox(byZipCode, value, "ZipCode");
	}
	
	By byOwnership = By.id("ownership");
	public WebElement ownership() {
		return action.webElement(byOwnership);
	}

	public void setOwnership(String value) {
		action.setDropDown(byOwnership, value, "RentOrOwn");
	}
	
	By byMortgageRent = By.id("mortgage_rent");
	public WebElement mortgageRent() {
		return action.webElement(byMortgageRent);
	}

	public void setMortgageRent(String value) {
		action.setTextBox(byMortgageRent, value, "Mortgage/Rent");
	}
	
	By byLivedYears = By.id("lived_years");
	public WebElement livedYears() {
		return action.webElement(byLivedYears);
	}

	public void setLivedYears(String value) {
		action.setDropDown(byLivedYears, value, "LivedYears");
	}
	
	By byLivedMonths = By.id("lived_months");
	public WebElement livedMonths() {
		return action.webElement(byLivedMonths);
	}

	public void setLivedMonths(String value) {
		action.setDropDown(byLivedMonths, value, "LivedMonths");
	}
	
	By byGrossIncome = By.id("gross_income");
	public WebElement grossIncome() {
		return action.webElement(byGrossIncome);
	}

	public void setGrossIncome(String value) {
		action.setTextBox(byGrossIncome, value, "GrossIncome");
		
	}
	
	By byOtherIncome = By.id("other_income");
	public WebElement otherIncome() {
		return action.webElement(byOtherIncome);
	}

	public void setOtherIncome(String value) {
		action.setTextBox(byOtherIncome, value, "OtherIncome");
	}
	
	By byHomeNumber = By.id("home_number");
	public WebElement homeNumber() {
		return action.webElement(byHomeNumber);
	}

	public void setHomeNumber(String value) {
		action.setTextBox(byHomeNumber, value, "HomeNumber");
	}
	
	By byDob = By.id("dob");
	public WebElement dob() {
		return action.webElement(byDob);
	}

	public void setDob(String value) throws InterruptedException {
		Thread.sleep(1000);
		action.setTextBox(byDob, value, "DateOfBirth");
		Thread.sleep(1000);
	}
	
	By bySsn = By.id("ssn_itin");
	public WebElement ssn() {
		return action.webElement(bySsn);
	}

	public void setSsn(String value) {
		action.setTextBox(bySsn, value, "SSN");
	}
	
	By byTerms =By.id("terms2");
	public WebElement terms() {
		return action.webElement(byTerms);
	}
	
	public String getTerms() {
		if (terms().isSelected()) {
			return "Yes";
		} else {
			return "No";
		}
	}

	public void setTerms(String value) {
		if ((getTerms().equals("Yes") && value.equals("No")) || (getTerms().equals("No") && value.equals("Yes"))) {
			terms().click();
		}
		log.info("Entered => Terms = " + value);
		/*
		 * testQA.log(LogStatus.INFO, "Entered => Terms = " + value);
		 * testLocal.log(LogStatus.INFO, "Entered => Terms = " + value);
		 */
	}
	
	public boolean isOtherIncomeVisible() {
		return action.isElementVisible(byOtherIncome, "Other Income");
	}
	
	public boolean isTermsAnConditionsVisible() {
		return action.isElementVisible(byTerms, "Application Terms and Conditions");
	}
		
	///////////// AEL Elements ///////////////////
	
	By byAelAppPhoneNumber1 = By.xpath("//*[@id=\"prefer-to-call-box\"]/div/div/p/a/span[1]");
	String aelAppPhoneNumber1 = "1-855-422-7412";
	public void isAelAppPhoneNumber1Visible() {
		action.isTextVisible(byAelAppPhoneNumber1, aelAppPhoneNumber1);
	}
	
	public void isNotAelAppPhoneNumber1Visible() {
		action.isNotTextVisible(byAelAppPhoneNumber1, aelAppPhoneNumber1);
	}
		
	By byAelAppPhoneNumber2 = By.xpath("//*[@id=\"prefer-to-call-box\"]/div/div/div/button/span[1]");
	String aelAppPhoneNumber2 = "1-855-422-7412";
	public void isAelAppPhoneNumber2Visible() {
		action.isTextVisible(byAelAppPhoneNumber2, aelAppPhoneNumber2);
	}
	
	public void isNotAelAppPhoneNumber2Visible() {
		action.isNotTextVisible(byAelAppPhoneNumber2, aelAppPhoneNumber2);
	}
								    
	By byAelTerms = By.xpath("//*[@id=\"ael-step-2\"]/div[13]/div/p/span[1]");
	String aelTerms="By checking this box, you agree to the general terms and conditions, wireless policy and online policies and agreements.";
	public void isAelTermsVisible() {
		action.isTextVisible(byAelTerms,aelTerms);
	}
	
	public void isNotAelTermsVisible() {
		action.isNotTextVisible(byAelTerms,aelTerms);
	}
	
	
	By byCustomizeMyOfferButton = By.xpath("//*[contains(text(),'Customize my offer')]");
	public void clickCustomizeMyOfferButton() {
		action.clickButton(byCustomizeMyOfferButton, "Customize my offer");
	}
	
	///////////// CCB Elements ///////////////////
	
	By byCcbAppPhoneNumber1 = By.xpath("//*[@id=\"prefer-to-call-box\"]/div/div/p/a/span[2]");
	String ccbAppPhoneNumber1="1-855-422-7410";
	public void isCcbAppPhoneNumber1Visible() {
		action.isTextVisible(byCcbAppPhoneNumber1, ccbAppPhoneNumber1);
	}
	
	public void isNotCcbAppPhoneNumber1Visible() {
		action.isNotTextVisible(byCcbAppPhoneNumber1, ccbAppPhoneNumber1);
	}
	
	By byCcbAppPhoneNumber2 = By.xpath("//*[@id=\"prefer-to-call-box\"]/div/div/div/button/span[2]");
	String ccbAppPhoneNumber2="1-855-422-7410";
	public void isCcbAppPhoneNumber2Visible() {
		action.isTextVisible(byCcbAppPhoneNumber2, ccbAppPhoneNumber2);
	}
	
	public void isNotCcbAppPhoneNumber2Visible() {
		action.isNotTextVisible(byCcbAppPhoneNumber2, ccbAppPhoneNumber2);
	}
	
	By byCcbTerms = By.xpath("//*[@id=\"ael-step-2\"]/div[13]/div/p/span[2]");
	String ccbTerms = "By checking this box, you agree to the Application Terms and Conditions, Capital Community Bank’s Privacy Policy and LoanMart’s Privacy Policy";
	public void isCcbTermsVisible() {
		action.isTextVisible(byCcbTerms,ccbTerms);
	}
	
	public void isNotCcbTermsVisible() {
		action.isNotTextVisible(byCcbTerms,ccbTerms);
	}
		
	By byCcbIncomeText = By.xpath("//*[@id=\"ael-step-2\"]/div[8]/span");
	String ccbIncomeText="Alimony, child support, or separate maintenance income need not be revealed if you do not wish to have it considered as a basis for repaying this obligation";
	public void isCcbIncomeTextVisible() {
		action.isTextVisible(byCcbIncomeText,ccbIncomeText);
	}
	
	public void isNotCcbIncomeTextVisible() {
		action.isNotTextVisible(byCcbIncomeText,ccbIncomeText);
	}
		
	By byCcbSubmitButton = By.xpath("//*[@id='ael-step-2-confirm']/span[2]");
	public void clickCcbSubmitButton() {
		action.clickButton(byCcbSubmitButton, "SUBMIT");
	}
	
	public boolean isClickCcbSubmitButtonVisible() {
		return action.isElementVisible(byCcbSubmitButton, "CCB Submit Button");
	}
		
	By byCcbTermsLink = By.xpath("//*[@id=\"ael-step-2\"]/div[13]/div/p/span[2]/a[1]");
	String ccbTermsLink = "Application Terms and Conditions";
	public void verifyStateInTermsAndConditions(String state) {
		String winHandleBefore = driver.getWindowHandle();
		action.clickLink(byCcbTermsLink, ccbTermsLink);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		String pageSource=driver.getPageSource();
		if (pageSource.contains(state)) {
			System.out.println("Verified");
			/*
			 * testQA.log(LogStatus.PASS, " Verified " +
			 * state+" included in Terms and Conditions"); testLocal.log(LogStatus.PASS,
			 * " Verified " + state+" included in Terms and Conditions");
			 */
		} else {
			System.out.println("Not included in Terms and Conditions");
			/*
			 * testQA.log(LogStatus.WARNING,
			 * state+" is NOT included in Terms and Conditions");
			 * testLocal.log(LogStatus.WARNING,
			 * state+" is NOT included in Terms and Conditions");
			 */
		}
		driver.close();
		driver.switchTo().window(winHandleBefore);
	}
}
