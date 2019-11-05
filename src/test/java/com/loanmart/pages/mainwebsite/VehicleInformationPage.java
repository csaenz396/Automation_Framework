package com.loanmart.pages.mainwebsite;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.loanmart.action.Action;
import com.loanmart.base.TestBase;
import com.loanmart.listeners.CustomListeners;
import com.loanmart.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class VehicleInformationPage extends TestBase{
	
	WebDriver driver;
	Action action;
	
	public VehicleInformationPage(WebDriver driver, String testName) {
		this.driver = driver;
		action = new Action(testName);
	}

	public void waitForVehiclePageLoad() {
		action.waitForPageLoad(byCarYear);
	}
	
	By byCarYear = By.id("car_year");
	public WebElement carYear() {
		return action.webElement(byCarYear);
	}
	
	public void setCarYear(String value) {
		action.setDropDown(byCarYear, value, "CarYear");
	}

	By byCarMake = By.id("car_make");
	public WebElement carMake() {
		return action.webElement(byCarMake);
	}
	
	public void setCarMake(String value) {
		//Thread.sleep(1000);
		action.setDropDown(byCarMake, value, "CarMake");
	}
	
	By byCarModel = By.id("car_model");
	public WebElement carModel() {
		return action.webElement(byCarModel);
	}
	
	public void setCarModel(String value) {
		//Thread.sleep(500);
		action.setDropDown(byCarModel, value, "CarModel");
	}
	
	By byCarStyle = By.id("car_style");
	public WebElement carStyle() {
		return action.webElement(byCarStyle);
	}
	
	public void setCarStyle(String value) {
		//Thread.sleep(500);
		action.setDropDown(byCarStyle, value, "CarStyle");
	}
	
	By byCarMileage = By.id("car_mileage");
	public WebElement carMileage() {
		return action.webElement(byCarMileage);
	}
	
	public void setCarMileage(String value) {
		//this.carMileage.sendKeys(value);
		action.setTextBox(byCarMileage, value, "CarMileage");
	}
	
	By byMobileNumber = By.id("mobile_number");
	public WebElement mobileNumber() {
		return action.webElement(byMobileNumber);
	}
	
	public void setMobileNumber(String value) {
		action.setTextBox(byMobileNumber, value, "MobileNumber");
	}
	
	By byTerms = By.id("text_terms");
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
	
	public boolean isTermsCheckBoxvisible() {
		return action.isElementVisible(byTerms, "Terms");
	}

	By byActiveMilitary = By.id("text_active_military");
	public WebElement activeMilitary() {
		return action.webElement(byActiveMilitary);
	}
	
	public String getActiveMilitary() {
		if (activeMilitary().isSelected()) {
			return "Yes";
		} else {
			return "No";
		}
	}

	public void setActiveMilitary(String value) {
		if ((getActiveMilitary().equals("Yes") && value.equals("No")) || (getActiveMilitary().equals("No") && value.equals("Yes"))) {
			activeMilitary().click();
		}
		log.info("Entered => Active Military = " + value);
		/*
		 * testQA.log(LogStatus.INFO, "Entered => Active Military = " + value);
		 * testLocal.log(LogStatus.INFO, "Entered => Active Military = " + value);
		 */
	}
	
	public boolean isMilitaryCheckBoxVisible() {
		return action.isElementVisible(byActiveMilitary, "Military");
	}
	
	By byNextButton = By.id("ael-step-1-confirm");
	public WebElement nextButton() {
		return action.webElement(byNextButton);
	}

	public void clickNextButton() {
		action.clickButton(byNextButton, "NextButton");
	}
	
	By byLoanHeaderText = By.xpath("//*[@id='loan_header']/p");

	///////////// AEL Elements ///////////////////
	String aelLoanHeaderText = "Call 1-855-422-7412 now to complete your application by speaking with a dedicated loan representative or complete this form to get your decision by answering 10 easy questions.";
	public void isAelLoanHeaderVisible() {
		action.isTextVisible(byLoanHeaderText, aelLoanHeaderText);
	}

	public void isNotAelLoanHeaderVisible() {
		action.isNotTextVisible(byLoanHeaderText, aelLoanHeaderText);
	}
	
	By byAelStep1Text = By.xpath("//*[@id='ael-step-1']/div[1]/h3");
	String aelStep1Text = "LoanMart Auto Title Loan";
	public void isAelStep1TextVisible() {
		action.isTextVisible(byAelStep1Text,aelStep1Text);
	}
	
	public void isNotAelStep1TextVisible() {
		action.isNotElementVisible(byAelStep1Text,aelStep1Text);
	}
		
	By byAelStep1Text2 = By.xpath("//*[@id='ael-step-1']/div[1]/p");
	String aelStep1Text2 = "Based on the provided information, a LoanMart Auto Title Loan is your perfect fit loan. Get cash fast and keep your car! Please provide the following details so our team can create your custom rate.";
	public void isAelStep1Text2Visible() {
		action.isTextVisible(byAelStep1Text2,aelStep1Text2);
	}
	
	public void isNotAelStep1Text2Visible() {
		action.isNotTextVisible(byAelStep1Text2,aelStep1Text2);
	}
		
	By byAelPerfectFitBoxText1 = By.xpath("//*[@id='perfect-fit-box']/div/div[1]/p");
	String aelPerfectFitBoxText1="You have been matched with a LoanMart Auto Title Loan for the following reasons:";
	public void isAelPerfectFitBoxText1Visible() {
		action.isTextVisible(byAelPerfectFitBoxText1,aelPerfectFitBoxText1);
	}
	
	public void isNotAelPerfectFitBoxText1Visible() {
		action.isNotTextVisible(byAelPerfectFitBoxText1,aelPerfectFitBoxText1);
	}
		
	By byAelPerfectFitBoxText2 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[1]/div[1]/ul/li[1]");
	String aelPerfectFitBoxText2="Get cash in 24 hours or less";
	public void isAelPerfectFitBoxText2Visible() {
		action.isTextVisible(byAelPerfectFitBoxText2,aelPerfectFitBoxText2);
	}
	
	public void isNotAelPerfectFitBoxText2Visible() {
		action.isNotTextVisible(byAelPerfectFitBoxText2,aelPerfectFitBoxText2);
	}
		
	By byAelPerfectFitBoxText3 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[1]/div[1]/ul/li[2]");
	String aelPerfectFitBoxText3="Keep your car and get up to 120% of its equity";
	public void isAelPerfectFitBoxText3Visible() {
		action.isTextVisible(byAelPerfectFitBoxText3,aelPerfectFitBoxText3);
	}
	
	public void isNotAelPerfectFitBoxText3Visible() {
		action.isNotTextVisible(byAelPerfectFitBoxText3,aelPerfectFitBoxText3);
	}
	
	By byAelPerfectFitBoxText4 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[1]/div[1]/ul/li[3]");
	String aelPerfectFitBoxText4="Comfortable repayment terms";
	public void isAelPerfectFitBoxText4Visible() {
		action.isTextVisible(byAelPerfectFitBoxText4,aelPerfectFitBoxText4);
	}
	
	public void isNotAelPerfectFitBoxText4Visible() {
		action.isNotTextVisible(byAelPerfectFitBoxText4,aelPerfectFitBoxText4);
	}
		
	By byAelPerfectFitBoxText5 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[1]/div[2]/button/span[1]");
	String aelPerfectFitBoxText5="1-855-422-7412";
	public void isAelPerfectFitBoxText5Visible() {
		action.isTextVisible(byAelPerfectFitBoxText5,aelPerfectFitBoxText5);
	}
	
	public void isNotAelPerfectFitBoxText5Visible() {
		action.isNotTextVisible(byAelPerfectFitBoxText5,aelPerfectFitBoxText5);
	}
	
	
	By byAelPerfectFitBoxText6 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[1]/div[3]/button");
	String aelPerfectFitBoxText6="FIND A LOCAL PARTICIPATING STORE";
	public void isAelPerfectFitBoxText6Visible() {
		action.isTextVisible(byAelPerfectFitBoxText6,aelPerfectFitBoxText6);
	}
	
	public void isNotAelPerfectFitBoxText6Visible() {
		action.isNotTextVisible(byAelPerfectFitBoxText6,aelPerfectFitBoxText6);
	}
	
	///////////// CCB Elements ///////////////////
	By byCCBHereConsentLink = By.linkText("here");
	
	public WebElement consentLink() {
		return action.webElement(byCCBHereConsentLink);
	}
	
	public boolean CheckCcbConsent() {
		if(clickCcbConsentLink())
			return true;
		else
			return false;
	}
	public boolean clickCcbConsentLink() {

		String winHandleBefore = action.getCurrentWindowHandle();
		
		if(action.openLinkInNewTab(byCCBHereConsentLink, "CCB Concent to Receive Calls Link")) {
			ArrayList<String> list = action.getWindowHandles();
			if(list.size()>0) {
				action.switchWindowHandles(list.get(2));
				String url = action.getCurrentURL();
				action.closeCurrentWindowHandle();
				action.switchWindowHandles(list.get(1));
				action.navigateToAnotherPage(url);
				if(action.verifyContains("Capital Community Bank", ccbConsent().getText())) {
					action.takeScreentShot("pass", "CCB Consent to Receive Calls", "ccbConsent");
					action.closeCurrentWindowHandle();
					action.switchWindowHandles(winHandleBefore);
					return true;
				}else
					return false;	
			}
			else
				return false;
		}else
			return false;
	}
	
	By ccbConsent = By.xpath("/html/body/p[1]");
	
	public WebElement ccbConsent() {
		return action.webElement(ccbConsent);
	}
	String ccbLoanHeaderText = "Call 1-855-422-7410 now to complete your application by speaking with a dedicated loan representative or complete this form to get your decision.";
	public void isCcbLoanHeaderVisible() {
		action.isTextVisible(byLoanHeaderText, ccbLoanHeaderText);
	}
	
	public void isNotCcbLoanHeaderVisible() {
		action.isNotTextVisible(byLoanHeaderText, ccbLoanHeaderText);
	}
	
	By byCcbStep1Text = By.xpath("//*[@id='ael-step-1']/div[2]/h3");
	String ccbStep1Text="Please tell us about your vehicle";
	public void isCcbStep1TextVisible() {
		action.isTextVisible(byCcbStep1Text,ccbStep1Text);
	}
	
	public void isNotCcbStep1TextVisible() {
		action.isNotTextVisible(byCcbStep1Text,ccbStep1Text);
	}
		
	By byCcbPerfectFitBoxText1 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[2]/p");
	String ccbPerfectFitBoxText1="A vehicle secured loan offers you:";
	public void isCcbPerfectFitBoxText1Visible() {
		action.isTextVisible(byCcbPerfectFitBoxText1,ccbPerfectFitBoxText1);
	}
	
	public void isNotCcbPerfectFitBoxText1Visible() {
		action.isNotTextVisible(byCcbPerfectFitBoxText1,ccbPerfectFitBoxText1);
	}

	By byCcbPerfectFitBoxText2 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[2]/div[1]/ul/li[1]");
	String ccbPerfectFitBoxText2="Fast funding";
	public void isCcbPerfectFitBoxText2Visible() {
		action.isTextVisible(byCcbPerfectFitBoxText2,ccbPerfectFitBoxText2);
	}
	
	public void isNotCcbPerfectFitBoxText2Visible() {
		action.isNotTextVisible(byCcbPerfectFitBoxText2,ccbPerfectFitBoxText2);
	}


	By byCcbPerfectFitBoxText3 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[2]/div[1]/ul/li[2]");
	String ccbPerfectFitBoxText3="Competitive terms";
	public void isCcbPerfectFitBoxText3Visible() {
		action.isTextVisible(byCcbPerfectFitBoxText3,ccbPerfectFitBoxText3);
	}
	
	public void isNotCcbPerfectFitBoxText3Visible() {
		action.isNotTextVisible(byCcbPerfectFitBoxText3,ccbPerfectFitBoxText3);
	}
	
	By byCcbPerfectFitBoxText4 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[2]/div[1]/ul/li[3]");
	String ccbPerfectFitBoxText4="Comfortable monthly payments";
	public void isCcbPerfectFitBoxText4Visible() {
		action.isTextVisible(byCcbPerfectFitBoxText4,ccbPerfectFitBoxText4);
	}
	
	public void isNotCcbPerfectFitBoxText4Visible() {
		action.isNotTextVisible(byCcbPerfectFitBoxText4,ccbPerfectFitBoxText4);
	}

	By byCcbPerfectFitBoxText5 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[2]/div[2]/button/span[2]");
	String ccbPerfectFitBoxText5="1-855-422-7410";
	public void isCcbPerfectFitBoxText5Visible() {
		action.isTextVisible(byCcbPerfectFitBoxText5,ccbPerfectFitBoxText5);
	}
	
	public void isNotCcbPerfectFitBoxText5Visible() {
		action.isNotTextVisible(byCcbPerfectFitBoxText5,ccbPerfectFitBoxText5);
	}
}
