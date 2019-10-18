package com.loanmart.pages.mainwebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.loanmart.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class SuccessPage extends TestBase{

	WebDriver driver;

	public SuccessPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitForSuccessPageLoad() {
		waitForPageLoad(byLoanNumber);
	}
	
	By byLoanNumber = By.id("lm-app-number");
	public WebElement loanNumber() {
		return webElement(byLoanNumber);
	}
	
	public String getLoanNumber() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(byLoanNumber));
		String loanNumber = loanNumber().getText();
		/*
		 * testQA.log(LogStatus.PASS, "Created Loan Number => "+loanNumber);
		 * testLocal.log(LogStatus.PASS, "Created Loan Number => "+loanNumber);
		 */
		log.debug("Created Loan Number => "+loanNumber);
		return loanNumber;
	}
	
	///////////// AEL Elements ///////////////////
	public By byAelText1 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[1]/div/h2/span");
	public String aelText1="Success! ";
	
	public By byAelText2 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[1]/div/div[1]/div[1]/span/em/strong");
	public String aelText2="or";
	
	public By byAelText3 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[1]/div/h3[2]/span[1]");
	public String aelText3="Offer";
	
	public By byAelText4 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[1]/div/h3[2]/span[1]");
	public String aelText4="Thank you for choosing LoanMart! We look forward to working with you. Your loan application details are confirmed below:";
	
	public By byAelText5 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[1]/div/div[2]/table/tbody/tr[4]/td[1]/strong/span[1]");
	public String aelText5="Car";
	
	public By byAelText6 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[2]/div/div[2]/p/span[2]");
	public String aelText6="LoanMart understands";
	
	public By byAelPhone1 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[1]/div/div[1]/div[1]/button[2]/span[1]");
	public String aelPhone1="1-855-422-7410";
	
	public By byAelPhone2 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[2]/div/div[2]/p/a/span[1]");
	public String aelPhone2="1-855-422-7410";
	
	public By byAelPhone3 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[2]/div/div[2]/div/button/span[1]");
	public String aelPhone3="1-855-422-7410";
	
	public By byAelFooterPhone = By.xpath("//*[@id=\"ContactInfoBar\"]/div/div/span/p/a[2]/strong");
	public String aelFooterPhoneText="1-855-422-7410";
	
	public By byAelHaveUsCallYouButton = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[1]/div/span/p");
	public String aelHaveUsCallYouButtonText="Have us call you";
	
	///////////// CCB Elements ///////////////////
	public By byCcbText1 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[1]/div/h3[2]/span[2]");
	public String ccbText1="Application";
	
	public By byCcbText2 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[1]/div/div[2]/table/tbody/tr[4]/td[1]/strong/span[2]");
	public String ccbText2="Vehicle";
	
	public By byCcbText3 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[2]/div/div[2]/p/span[1]");
	public String ccbText3="We understand";
	
	public By byCcbPhone1 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[1]/div/div[1]/div[1]/button[2]/span[2]");
	public String ccbPhone1Text="1-855-422-7410";
	
	public By byCcbPhone2 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[2]/div/div[2]/p/a/span[2]");
	public String ccbPhone2Text="1-855-422-7410";
	
	public By byCcbPhone3 = By.xpath("//*[@id=\"lm-loan-container\"]/div/div[2]/div[2]/div/div[2]/div/button/span[2]");
	public String ccbPhone3Text="1-855-422-7410";
	
	public By byCcbFooterPhone = By.xpath("//*[@id=\"ContactInfoBar\"]/div/div/span/p/a[3]/strong");
	public String ccbFooterPhoneText="1-855-422-7410";
	
}
