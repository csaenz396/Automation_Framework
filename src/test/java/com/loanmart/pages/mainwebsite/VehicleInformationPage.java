package com.loanmart.pages.mainwebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.loanmart.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class VehicleInformationPage extends TestBase{
	
	WebDriver driver;
	
	public VehicleInformationPage(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForVehiclePageLoad() {
		waitForPageLoad(byCarYear);
	}
	
	By byCarYear = By.id("car_year");
	public WebElement carYear() {
		return webElement(byCarYear);
	}
	
	public void setCarYear(String value) {
		setDropDown(byCarYear, value, "CarYear");
	}

	By byCarMake = By.id("car_make");
	public WebElement carMake() {
		return webElement(byCarMake);
	}
	
	public void setCarMake(String value) {
		//Thread.sleep(1000);
		setDropDown(byCarMake, value, "CarMake");
	}
	
	By byCarModel = By.id("car_model");
	public WebElement carModel() {
		return webElement(byCarModel);
	}
	
	public void setCarModel(String value) {
		//Thread.sleep(500);
		setDropDown(byCarModel, value, "CarModel");
	}
	
	By byCarStyle = By.id("car_style");
	public WebElement carStyle() {
		return webElement(byCarStyle);
	}
	
	public void setCarStyle(String value) {
		//Thread.sleep(500);
		setDropDown(byCarStyle, value, "CarStyle");
	}
	
	By byCarMileage = By.id("car_mileage");
	public WebElement carMileage() {
		return webElement(byCarMileage);
	}
	
	public void setCarMileage(String value) {
		//this.carMileage.sendKeys(value);
		setTextBox(byCarMileage, value, "CarMileage");
	}
	
	By byMobileNumber = By.id("mobile_number");
	public WebElement mobileNumber() {
		return webElement(byMobileNumber);
	}
	
	public void setMobileNumber(String value) {
		setTextBox(byMobileNumber, value, "MobileNumber");
	}
	
	By byTerms = By.id("text_terms");
	public WebElement terms() {
		return webElement(byTerms);
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

	By byActiveMilitary = By.id("text_active_military");
	public WebElement activeMilitary() {
		return webElement(byActiveMilitary);
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
	
	By byNextButton = By.id("ael-step-1-confirm");
	public WebElement nextButton() {
		return webElement(byNextButton);
	}

	public void clickNextButton() {
		clickButton(byNextButton, "NextButton");
	}
	
	By byLoanHeaderText = By.xpath("//*[@id='loan_header']/p");

	///////////// AEL Elements ///////////////////
	String aelLoanHeaderText = "Call 1-855-422-7412 now to complete your application by speaking with a dedicated loan representative or complete this form to get your decision by answering 10 easy questions.";
	public void isAelLoanHeaderVisible() {
		isTextVisible(byLoanHeaderText, aelLoanHeaderText);
	}

	public void isNotAelLoanHeaderVisible() {
		isNotTextVisible(byLoanHeaderText, aelLoanHeaderText);
	}
	
	By byAelStep1Text = By.xpath("//*[@id='ael-step-1']/div[1]/h3");
	String aelStep1Text = "LoanMart Auto Title Loan";
	public void isAelStep1TextVisible() {
		isTextVisible(byAelStep1Text,aelStep1Text);
	}
	
	public void isNotAelStep1TextVisible() {
		isNotElementVisible(byAelStep1Text,aelStep1Text);
	}
		
	By byAelStep1Text2 = By.xpath("//*[@id='ael-step-1']/div[1]/p");
	String aelStep1Text2 = "Based on the provided information, a LoanMart Auto Title Loan is your perfect fit loan. Get cash fast and keep your car! Please provide the following details so our team can create your custom rate.";
	public void isAelStep1Text2Visible() {
		isTextVisible(byAelStep1Text2,aelStep1Text2);
	}
	
	public void isNotAelStep1Text2Visible() {
		isNotTextVisible(byAelStep1Text2,aelStep1Text2);
	}
		
	By byAelPerfectFitBoxText1 = By.xpath("//*[@id='perfect-fit-box']/div/div[1]/p");
	String aelPerfectFitBoxText1="You have been matched with a LoanMart Auto Title Loan for the following reasons:";
	public void isAelPerfectFitBoxText1Visible() {
		isTextVisible(byAelPerfectFitBoxText1,aelPerfectFitBoxText1);
	}
	
	public void isNotAelPerfectFitBoxText1Visible() {
		isNotTextVisible(byAelPerfectFitBoxText1,aelPerfectFitBoxText1);
	}
		
	By byAelPerfectFitBoxText2 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[1]/div[1]/ul/li[1]");
	String aelPerfectFitBoxText2="Get cash in 24 hours or less";
	public void isAelPerfectFitBoxText2Visible() {
		isTextVisible(byAelPerfectFitBoxText2,aelPerfectFitBoxText2);
	}
	
	public void isNotAelPerfectFitBoxText2Visible() {
		isNotTextVisible(byAelPerfectFitBoxText2,aelPerfectFitBoxText2);
	}
		
	By byAelPerfectFitBoxText3 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[1]/div[1]/ul/li[2]");
	String aelPerfectFitBoxText3="Keep your car and get up to 120% of its equity";
	public void isAelPerfectFitBoxText3Visible() {
		isTextVisible(byAelPerfectFitBoxText3,aelPerfectFitBoxText3);
	}
	
	public void isNotAelPerfectFitBoxText3Visible() {
		isNotTextVisible(byAelPerfectFitBoxText3,aelPerfectFitBoxText3);
	}
	
	By byAelPerfectFitBoxText4 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[1]/div[1]/ul/li[3]");
	String aelPerfectFitBoxText4="Comfortable repayment terms";
	public void isAelPerfectFitBoxText4Visible() {
		isTextVisible(byAelPerfectFitBoxText4,aelPerfectFitBoxText4);
	}
	
	public void isNotAelPerfectFitBoxText4Visible() {
		isNotTextVisible(byAelPerfectFitBoxText4,aelPerfectFitBoxText4);
	}
		
	By byAelPerfectFitBoxText5 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[1]/div[2]/button/span[1]");
	String aelPerfectFitBoxText5="1-855-422-7412";
	public void isAelPerfectFitBoxText5Visible() {
		isTextVisible(byAelPerfectFitBoxText5,aelPerfectFitBoxText5);
	}
	
	public void isNotAelPerfectFitBoxText5Visible() {
		isNotTextVisible(byAelPerfectFitBoxText5,aelPerfectFitBoxText5);
	}
	
	
	By byAelPerfectFitBoxText6 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[1]/div[3]/button");
	String aelPerfectFitBoxText6="FIND A LOCAL PARTICIPATING STORE";
	public void isAelPerfectFitBoxText6Visible() {
		isTextVisible(byAelPerfectFitBoxText6,aelPerfectFitBoxText6);
	}
	
	public void isNotAelPerfectFitBoxText6Visible() {
		isNotTextVisible(byAelPerfectFitBoxText6,aelPerfectFitBoxText6);
	}
	
	///////////// CCB Elements ///////////////////
	String ccbLoanHeaderText = "Call 1-855-422-7410 now to complete your application by speaking with a dedicated loan representative or complete this form to get your decision.";
	public void isCcbLoanHeaderVisible() {
		isTextVisible(byLoanHeaderText, ccbLoanHeaderText);
	}
	
	public void isNotCcbLoanHeaderVisible() {
		isNotTextVisible(byLoanHeaderText, ccbLoanHeaderText);
	}
	
	By byCcbStep1Text = By.xpath("//*[@id='ael-step-1']/div[2]/h3");
	String ccbStep1Text="Please tell us about your vehicle";
	public void isCcbStep1TextVisible() {
		isTextVisible(byCcbStep1Text,ccbStep1Text);
	}
	
	public void isNotCcbStep1TextVisible() {
		isNotTextVisible(byCcbStep1Text,ccbStep1Text);
	}
		
	By byCcbPerfectFitBoxText1 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[2]/p");
	String ccbPerfectFitBoxText1="A vehicle secured loan offers you:";
	public void isCcbPerfectFitBoxText1Visible() {
		isTextVisible(byCcbPerfectFitBoxText1,ccbPerfectFitBoxText1);
	}
	
	public void isNotCcbPerfectFitBoxText1Visible() {
		isNotTextVisible(byCcbPerfectFitBoxText1,ccbPerfectFitBoxText1);
	}

	By byCcbPerfectFitBoxText2 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[2]/div[1]/ul/li[1]");
	String ccbPerfectFitBoxText2="Fast funding";
	public void isCcbPerfectFitBoxText2Visible() {
		isTextVisible(byCcbPerfectFitBoxText2,ccbPerfectFitBoxText2);
	}
	
	public void isNotCcbPerfectFitBoxText2Visible() {
		isNotTextVisible(byCcbPerfectFitBoxText2,ccbPerfectFitBoxText2);
	}


	By byCcbPerfectFitBoxText3 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[2]/div[1]/ul/li[2]");
	String ccbPerfectFitBoxText3="Competitive terms";
	public void isCcbPerfectFitBoxText3Visible() {
		isTextVisible(byCcbPerfectFitBoxText3,ccbPerfectFitBoxText3);
	}
	
	public void isNotCcbPerfectFitBoxText3Visible() {
		isNotTextVisible(byCcbPerfectFitBoxText3,ccbPerfectFitBoxText3);
	}
	
	By byCcbPerfectFitBoxText4 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[2]/div[1]/ul/li[3]");
	String ccbPerfectFitBoxText4="Comfortable monthly payments";
	public void isCcbPerfectFitBoxText4Visible() {
		isTextVisible(byCcbPerfectFitBoxText4,ccbPerfectFitBoxText4);
	}
	
	public void isNotCcbPerfectFitBoxText4Visible() {
		isNotTextVisible(byCcbPerfectFitBoxText4,ccbPerfectFitBoxText4);
	}

	By byCcbPerfectFitBoxText5 = By.xpath("//*[@id=\"perfect-fit-box\"]/div/div[2]/div[2]/button/span[2]");
	String ccbPerfectFitBoxText5="1-855-422-7410";
	public void isCcbPerfectFitBoxText5Visible() {
		isTextVisible(byCcbPerfectFitBoxText5,ccbPerfectFitBoxText5);
	}
	
	public void isNotCcbPerfectFitBoxText5Visible() {
		isNotTextVisible(byCcbPerfectFitBoxText5,ccbPerfectFitBoxText5);
	}
}
