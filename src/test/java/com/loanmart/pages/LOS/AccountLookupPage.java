package com.loanmart.pages.LOS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.loanmart.action.Action;
import com.loanmart.base.TestBase;

public class AccountLookupPage {
	
	WebDriver webDriver;
	Action action;

	public AccountLookupPage(WebDriver driver, String testName) {
		this.webDriver = driver;
		action = new Action(testName);
	}
	
	public void waitForPageLoad() {
		By byLoaading = By.xpath("//*[contains(text(),'Loading')]");
		//By byLoaading = By.xpath("//*[@id='mat-tab-content-0-0']/div/lm-account-lookup/lm-spinner/div");
		TestBase.log.debug("Waiting for invisibility of => Loading...");
		TestBase.wait.until(ExpectedConditions.invisibilityOfElementLocated(byLoaading));
		TestBase.log.debug("Is now invisibile => Loading...");
	}	
	
	By byCreateNewApplicationButton = By.xpath("//mat-icon[contains(text(),'add')]");
	public WebElement createNewApplicationButton() {
		return action.webElement(byCreateNewApplicationButton);
	}
	public void clickCreateNewApplicationButton() {
		action.clickButton(byCreateNewApplicationButton,"CreateNewApplicationButton");
	}
	
	By bySearchApplicationsButton = By.xpath("//*[@title=\"Search Applications\"]");
	public WebElement searchApplicationsButton() {
		return action.webElement(bySearchApplicationsButton);
	}
	public void clickSearchApplicationsButton() {
		action.clickButton(bySearchApplicationsButton,"SearchApplicationsButton");
	}
	
	By byOpenApplicationButton = By.xpath("//*[@title=\"Open Application\"]");
	public WebElement openApplicationButton() {
		return action.webElement(byOpenApplicationButton);
	}
	public void clickOpenApplicationButton() {
		action.clickButton(byOpenApplicationButton,"OpenApplicationButton");
	}
	
	// Search Fields
	By bySearchLoanNumber = By.id("mat-input-1");
	public void setSearchLoanNumber(String value) {
		action.setTextBox(bySearchLoanNumber, value, "LoanNumber");
	}
	
	// Search Result
	By byPhoneNumbers = By.xpath("//table/tbody/tr/td[1]");
	public String getPhoneNumbers() {
		return action.getWebElementText(byPhoneNumbers);
	}
	
	By byLoanNumber =  By.xpath("//table/tbody/tr/td[2]");
	public String getLoanNumber() {
		return action.getWebElementText(byLoanNumber);
	}
	
	By byName =  By.xpath("//table/tbody/tr/td[3]");
	public String getName() {
		return action.getWebElementText(byName);
	}
	
	By byDOB =  By.xpath("//table/tbody/tr/td[4]");
	public String getDOB() {
		return action.getWebElementText(byDOB);
	}
	
	By bySSN =  By.xpath("//table/tbody/tr/td[5]");
	public String getSSN() {
		return action.getWebElementText(bySSN);
	}
	
	By byStatus =  By.xpath("//table/tbody/tr/td[6]");
	public String getStatus() {
		return action.getWebElementText(byStatus);
	}
	
	By byAppDate =  By.xpath("//table/tbody/tr/td[7]");
	public String getAppDate() {
		return action.getWebElementText(byAppDate);
	}
	
	By byTDReason =  By.xpath("//table/tbody/tr/td[8]");
	public String getTDReason() {
		return action.getWebElementText(byTDReason);
	}
	
	By byProduct =  By.xpath("//table/tbody/tr/td[9]");
	public String getProduct() {
		return action.getWebElementText(byProduct);
	}
	
	By byLoanOffice =  By.xpath("//table/tbody/tr/td[10]");
	public String getLoanOffice() {
		return action.getWebElementText(byLoanOffice);
	}
	
	By byPayoff =  By.xpath("//table/tbody/tr/td[11]");
	public String getPayoff() {
		return action.getWebElementText(byPayoff);
	}
	
	By byReloanEligible =  By.xpath("//table/tbody/tr/td[12]");
	public String getReloanEligible() {
		return action.getWebElementText(byReloanEligible);
	}
	
	public boolean verifyAssertEquals(String expected, String actual) {
		
		if(action.verifyEquals(expected, actual))
			return true;
		else
			return false;
	}
	
	public boolean verifyAssertContains(String subString, String completeString) {
		if(action.verifyContains(subString, completeString))
			return true;
		else
			return false;
	}
}
