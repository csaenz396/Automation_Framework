package com.loanmart.pages.LOS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.loanmart.action.Action;
import com.loanmart.base.TestBase;

public class TermsPage {
	WebDriver webDriver;
	Action action;

	public TermsPage(WebDriver webDriver, String testName) {
		this.webDriver = webDriver;
		action = new Action(testName);
	}
	public void waitForTermsPageLoad() {
		//By byLoaading = By.xpath("//*[contains(text(),'Loading')]");
		By byLoaading = By.xpath("//*[@id='mat-tab-content-0-0']/div/lm-account-lookup/lm-spinner/div");
		TestBase.log.debug("Waiting for invisibility of => Loading...");
		TestBase.wait.until(ExpectedConditions.invisibilityOfElementLocated(byLoaading));
		TestBase.log.debug("Is now invisibile => Loading...");
	}			
	
	By byProgramType = By.xpath("//label[contains(text(),'Program Type')]//parent::div//input");
	public String getProgramType() {
		return action.getTextBox(byProgramType);
	}

	By byRepeatCustomer = By.xpath("//label[contains(text(),'RepeatCustomer')]//parent::div//input");
	public String getRepeatCustomer() {
		return action.getTextBox(byRepeatCustomer);
	}
	
	By byFICO = By.xpath("//label[contains(text(),'FICO')]//parent::div//input");
	public String getFICO() {
		return action.getTextBox(byFICO);
	}
	
	By byDeclineReasons = By.xpath("//label[contains(text(),'Decline reasons')]//parent::div//input");
	public String getDeclineReasons() {
		return action.getTextBox(byDeclineReasons);
	}
	
	By byCustomerScore = By.xpath("//label[contains(text(),'Customer Score')]//parent::div//input");
	public String getCustomerScore() {
		return action.getTextBox(byCustomerScore);
	}
	
	By byNeustarAddressScore = By.xpath("//label[contains(text(),'Neustar Address Score')]//parent::div//input");
	public String getNeustarAddressScore() {
		return action.getTextBox(byNeustarAddressScore);
	}
	
	public boolean verifyAssertEquals(String expected, String actual) {
		if(action.verifyEquals(expected, actual))
			return true;
		else
			return false;
	}
	
	public boolean verifyNotEquals(String expected, String actual) {
		if(action.verifyNotEquals(expected, actual))
			return true;
		else
			return false;
	}
	
}
