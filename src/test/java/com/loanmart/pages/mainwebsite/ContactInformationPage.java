package com.loanmart.pages.mainwebsite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.loanmart.action.Action;
import com.loanmart.base.TestBase;
import com.loanmart.listeners.CustomListeners;
import com.loanmart.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class ContactInformationPage extends TestBase {
	
	WebDriver driver;
	Action action;
	
	public ContactInformationPage(WebDriver driver, String suiteName) {
		this.driver = driver;
		action = new Action(suiteName);
	}
	
	By byFirstName = By.id("firstName");
	public WebElement firstName() {
		return action.webElement(byFirstName);
	}
	
	public void setFirstName(String value) {
		action.setTextBox(byFirstName, value, "FirstName");
	}
	
	By byInvalidFirstNameMessage = By.xpath("//small[contains(text(),'First Name is not specified.')]");
	public void invalidFirstNameMessageDisplayed() {
		action.isElementVisible(byInvalidFirstNameMessage, "First Name is not specified.");
	}
	
	By byLastName = By.id("lastName");
	public WebElement lastName() {
		return action.webElement(byLastName);
	}
	
	public void setLastName(String value) {
		action.setTextBox(byLastName, value, "LastName");
	}
	
	By byEmail = By.id("email");
	public WebElement email() {
		return action.webElement(byEmail);
	}
	
	public void setEmail(String value) {
		action.setTextBox(byEmail, value, "Email");
	}
	
	By byStateOfResidence = By.xpath("//*[@id='personalState']");
	public WebElement stateOfResidence() {
		return action.webElement(byStateOfResidence);
	}
	
	public void setStateOfResidence(String value) {
		action.setDropDownFailPass(byStateOfResidence, value, "StateOfResidence", "State of Residence dropdown is not populated");
	}
	
	By byCar = By.id("car");
	public WebElement DoYouHaveAnAutomobile() {
		return action.webElement(byCar);
	}
	
	public void setDoYouHaveAnAutomobile(String value ) {
		action.setDropDown(byCar, value, "DoYouHaveAnAutomobile");
	}
	
	By byNextButton = By.id("start-contact-confirm");
	public WebElement nextButton() {
		return action.webElement(byNextButton);
	}
	
	public void clickNextButton() {
		action.clickButton(byNextButton, "NextButton");
	}
	
	By byLoanHeaderText = By.xpath("//*[@id='loan_header']/p");
	
	
	///////////// AEL Elements ///////////////////

	By byAelLoanMartLogo = By.xpath("//img[@src='/assets/images/lm-logo.png']");
	public WebElement aelLoanMartLogo() {
		return action.webElement(byAelLoanMartLogo);
	}
	
	public boolean isAelLoanMartLogoVisible(String testName) {
		return action.isElementVisible(byAelLoanMartLogo, "AEL LoanMart Logo", testName);
	}

	public boolean isNotAelLoanMartLogoVisible() {
		return action.isNotElementVisible(byAelLoanMartLogo, "AEL LoanMart Logo");
	}
		
	String aelLoanHeaderText = "Call 1-855-422-7412 now to complete your application by speaking with a dedicated loan representative or complete this form to get your decision by answering 10 easy questions.";
	public void isAelLoanHeaderVisible() {
		action.isTextVisible(byLoanHeaderText, aelLoanHeaderText);
	}

	public void isNotAelLoanHeaderVisible() {
		action.isNotTextVisible(byLoanHeaderText, aelLoanHeaderText);
	}
	
	By byConsumerAffairs = By.xpath("//img[@src='/application/assets/images/final_ca.png']");
	public WebElement consumerAffairs() {
		return action.webElement(byConsumerAffairs);
	}
	
	public boolean isConsumerAffairsVisible() {
		return action.isElementVisible(byConsumerAffairs, "Consumer Affairs");
	}

	public boolean isNotConsumerAffairsVisible() {
		return action.isNotElementVisible(byConsumerAffairs, "Consumer Affairs");
	}
		
	By byESignedConsent =By.id("eSignedConsent");
	public WebElement eSignedConsent() {
		return action.webElement(byESignedConsent);
	}

	public String getESignedConsent() {
		if (eSignedConsent().isSelected()) {
			return "Yes";
		} else {
			return "No";
		}
	}

	public void setESignedConsent(String value ) {
		if ((getESignedConsent().equals("Yes") && value.equals("No"))
				|| (getESignedConsent().equals("No") && value.equals("Yes"))) {
			eSignedConsent().click();
		}
		log.info("Entered => ESigned Consent = " + value);
	}

	public boolean isESignedConsentVisible() {
		return action.isElementVisible(byESignedConsent, "eSignedConsent");
	}
	
	public boolean isNotESignedConsentVisible() {
		return action.isNotElementVisible(byESignedConsent, "eSignedConsent");
	}
	
	By byThirdPartiesConsent =By.id("thirdPartiesConsent");
	public WebElement thirdPartiesConsent() {
		return action.webElement(byThirdPartiesConsent);
	}

	public String getThirdPartiesConsent() {
		if (thirdPartiesConsent().isSelected()) {
			return "Yes";
		} else {
			return "No";
		}
	}

	public void setThirdPartiesConsent(String value) {
		if ((getThirdPartiesConsent().equals("Yes") && value.equals("No"))
				|| (getThirdPartiesConsent().equals("No") && value.equals("Yes"))) {
			thirdPartiesConsent().click();
		}
		log.info("Entered => Third Parties Consent = " + value);
	}

	public boolean isThirdPartiesConsentVisible() {
		return action.isElementVisible(byThirdPartiesConsent, "Third Parties Consent");
	}

	public boolean isNotThirdPartiesConsentVisible() {
		return action.isNotElementVisible(byThirdPartiesConsent, "Third Parties Consent");
	}
	
	///////////// CCB Elements ///////////////////
	
	By byCcbLoanMartLogo = By.xpath("//img[@src='/assets/images/lm-ccb-logo.png']");
	public WebElement ccbLoanMartLogo() {
		return action.webElement(byCcbLoanMartLogo);
	}

	public boolean isCcbLoanMartLogoVisible() {
		return action.isElementVisible(byCcbLoanMartLogo, "CCB LoanMart Logo");
	}
	
	public boolean isNotCcbLoanMartLogoVisible() {
		return action.isNotElementVisible(byCcbLoanMartLogo, "CCB LoanMart Logo");
	}
	
	String ccbLoanHeaderText = "Call 1-855-422-7410 now to complete your application by speaking with a dedicated loan representative or complete this form to get your decision.";
	public void isCcbLoanHeaderVisible() {
		action.isTextVisible(byLoanHeaderText, ccbLoanHeaderText);
	}
	
	public void isNotCcbLoanHeaderVisible() {
		action.isNotTextVisible(byLoanHeaderText, ccbLoanHeaderText);
	}

	By byCcbBox = By.id("ccb-box");
	
	public WebElement ccbBox() {
		return action.webElement(byCcbBox);
	}
	public boolean isCcbBoxVisible(String state, WebDriverWait wait ) {
		String ccbBoxText="Loans for "+state+" residents are made by Capital Community Bank, a Utah chartered bank, member FDIC. Loans made by Capital Community Bank will be serviced by LoanMart.";
		wait.until(ExpectedConditions.visibilityOfElementLocated(byCcbBox));
		return action.isTextVisible(byCcbBox, ccbBoxText);
	}
	
	public boolean isNotCcbBoxVisible() {
		return action.isNotElementVisible(byCcbBox, "CCB Box");
	}
	
	public By byCcbStates = By.xpath("//*[@id=\"DisclaimerBar\"]/div/div[2]/span/p[2]");
	public WebElement ccbStates() {
		return action.webElement(byCcbStates);
	}
	
	public String getCcbStates() {
		return ccbStates().getText();
	}
	
	public boolean isStateFoundinDisclosureCCB(String state) {
		action.scrollPageDown();
		if(action.verifyContains(state, getCcbStates())) {
			TestUtil.captureScreenshot("CCBDISCLOSURE");
			CustomListeners.testLocal.log(LogStatus.PASS, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
			action.scrollPageUp();
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//**************************************************************
	
}
