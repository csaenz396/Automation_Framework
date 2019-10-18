package com.loanmart.pages.mainwebsite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.loanmart.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class ContactInformationPage extends TestBase {
	
	WebDriver driver;
	
	public ContactInformationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By byFirstName = By.id("firstName");
	public WebElement firstName() {
		return webElement(byFirstName);
	}
	
	public void setFirstName(String value) {
		setTextBox(byFirstName, value, "FirstName");
	}
	
	By byInvalidFirstNameMessage = By.xpath("//small[contains(text(),'First Name is not specified.')]");
	public void invalidFirstNameMessageDisplayed() {
		isElementVisible(byInvalidFirstNameMessage, "First Name is not specified.");
	}
	
	By byLastName = By.id("lastName");
	public WebElement lastName() {
		return webElement(byLastName);
	}
	
	public void setLastName(String value) {
		setTextBox(byLastName, value, "LastName");
	}
	
	By byEmail = By.id("email");
	public WebElement email() {
		return webElement(byEmail);
	}
	
	public void setEmail(String value) {
		setTextBox(byEmail, value, "Email");
	}
	
	By byStateOfResidence = By.xpath("//*[@id='personalState']");
	public WebElement stateOfResidence() {
		return webElement(byStateOfResidence);
	}
	
	public void setStateOfResidence(String value) {
		setDropDownFailPass(byStateOfResidence, value, "StateOfResidence", "State of Residence dropdown is not populated");
	}
	
	By byCar = By.id("car");
	public WebElement DoYouHaveAnAutomobile() {
		return webElement(byCar);
	}
	
	public void setDoYouHaveAnAutomobile(String value ) {
		setDropDown(byCar, value, "DoYouHaveAnAutomobile");
	}
	
	By byNextButton = By.id("start-contact-confirm");
	public WebElement nextButton() {
		return webElement(byNextButton);
	}
	
	public void clickNextButton() {
		clickButton(byNextButton, "NextButton");
	}
	
	By byLoanHeaderText = By.xpath("//*[@id='loan_header']/p");
	
	
	///////////// AEL Elements ///////////////////

	By byAelLoanMartLogo = By.xpath("//img[@src='/assets/images/lm-logo.png']");
	public WebElement aelLoanMartLogo() {
		return webElement(byAelLoanMartLogo);
	}
	
	public boolean isAelLoanMartLogoVisible(String testName) {
		return isElementVisible(byAelLoanMartLogo, "AEL LoanMart Logo", testName);
	}

	public boolean isNotAelLoanMartLogoVisible() {
		return isNotElementVisible(byAelLoanMartLogo, "AEL LoanMart Logo");
	}
		
	String aelLoanHeaderText = "Call 1-855-422-7412 now to complete your application by speaking with a dedicated loan representative or complete this form to get your decision by answering 10 easy questions.";
	public void isAelLoanHeaderVisible() {
		isTextVisible(byLoanHeaderText, aelLoanHeaderText);
	}

	public void isNotAelLoanHeaderVisible() {
		isNotTextVisible(byLoanHeaderText, aelLoanHeaderText);
	}
	
	By byConsumerAffairs = By.xpath("//img[@src='/application/assets/images/final_ca.png']");
	public WebElement consumerAffairs() {
		return webElement(byConsumerAffairs);
	}
	
	public boolean isConsumerAffairsVisible() {
		return isElementVisible(byConsumerAffairs, "Consumer Affairs");
	}

	public boolean isNotConsumerAffairsVisible() {
		return isNotElementVisible(byConsumerAffairs, "Consumer Affairs");
	}
		
	By byESignedConsent =By.id("eSignedConsent");
	public WebElement eSignedConsent() {
		return webElement(byESignedConsent);
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
		return isElementVisible(byESignedConsent, "eSignedConsent");
	}
	
	public boolean isNotESignedConsentVisible() {
		return isNotElementVisible(byESignedConsent, "eSignedConsent");
	}
	
	By byThirdPartiesConsent =By.id("thirdPartiesConsent");
	public WebElement thirdPartiesConsent() {
		return webElement(byThirdPartiesConsent);
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
		return isElementVisible(byThirdPartiesConsent, "Third Parties Consent");
	}

	public boolean isNotThirdPartiesConsentVisible() {
		return isNotElementVisible(byThirdPartiesConsent, "Third Parties Consent");
	}
	
	///////////// CCB Elements ///////////////////
	
	By byCcbLoanMartLogo = By.xpath("//img[@src='/assets/images/lm-ccb-logo.png']");
	public WebElement ccbLoanMartLogo() {
		return webElement(byCcbLoanMartLogo);
	}

	public boolean isCcbLoanMartLogoVisible() {
		return isElementVisible(byCcbLoanMartLogo, "CCB LoanMart Logo");
	}
	
	public boolean isNotCcbLoanMartLogoVisible() {
		return isNotElementVisible(byCcbLoanMartLogo, "CCB LoanMart Logo");
	}
	
	String ccbLoanHeaderText = "Call 1-855-422-7410 now to complete your application by speaking with a dedicated loan representative or complete this form to get your decision.";
	public void isCcbLoanHeaderVisible() {
		isTextVisible(byLoanHeaderText, ccbLoanHeaderText);
	}
	
	public void isNotCcbLoanHeaderVisible() {
		isNotTextVisible(byLoanHeaderText, ccbLoanHeaderText);
	}

	By byCcbBox = By.id("ccb-box");
	public void isCcbBoxVisible(String state ) {
		String ccbBoxText="Loans for "+state+" residents are made by Capital Community Bank, a Utah chartered bank, member FDIC. Loans made by Capital Community Bank will be serviced by LoanMart.";
		isTextVisible(byCcbBox, ccbBoxText);
	}
	
	public boolean isNotCcbBoxVisible() {
		return isNotElementVisible(byCcbBox, "CCB Box");
	}
	
	public By byCcbStates = By.xpath("//*[@id=\"DisclaimerBar\"]/div/div[2]/span/p[2]");
	public WebElement ccbStates() {
		return webElement(byCcbStates);
	}
	
	public String getCcbStates() {
		return ccbStates().getText();
	}
}
