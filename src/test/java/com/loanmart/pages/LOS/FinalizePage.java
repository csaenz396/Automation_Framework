package com.loanmart.pages.LOS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.loanmart.action.Action;
import com.loanmart.base.TestBase;

public class FinalizePage {
	
	
	WebDriver webDriver;
	Action action;

	public FinalizePage(WebDriver driver, String testName) {
		this.webDriver = driver;
		action = new Action(testName);
	}
	
	public void waitForFinalizePageLoad() {
		action.waitForPageLoad(byDateOfBirth);
	}
			
	By byDateOfBirth = By.xpath("//*[@formcontrolname='dateOfBirth']");
	public void setDateOfBirth(String value) {
		action.setTextBox(byDateOfBirth, value, "DateOfBirth");
	}
	public String getDateOfBirth() {
		return action.getTextBox(byDateOfBirth);
	}

	By bySsn = By.xpath("//*[@formcontrolname='ssn']");
	public void setSsn(String value) {
		action.setTextBox(bySsn, value, "SSN");
	}
	public String getSsn() {
		return action.getTextBox(bySsn);
	}
	
	By byInitial = By.xpath("//*[@formcontrolname='initial']");
	public void setInitial() {
		WebElement init = TestBase.driver.findElement(By.xpath("//ul/li/span"));
		String initial = init.getAttribute("data-letters");
		action.setTextBox(byInitial, initial, "Initial");
	}
	public String getInitial() {
		return action.getTextBox(byInitial);
	}
	
	//NEW CODE!!!!!  Checks the checkbox for credit pull
	
	By byCreditPull = By.xpath("//*[@formcontrolname='creditPullAgreement']");
	By byCreditPullChecked = By.xpath("//form[@id='finalizeForm']/div//p-panel/div/div[@role='region']/div/div[4]/mat-checkbox//input[@type='checkbox']");
	public void setCreditPull() {
		if(!action.isCheckBoxChecked(byCreditPullChecked, "Credit Pull"))
			action.checkCheckBox(byCreditPull, "Credit Pull");
	}
	
	By byNotMilitaryActiveDuty = By.xpath("//*[@formcontrolname='notActiveMilitaryAgreement']");
	By byNotMilitaryActiveDutyChecked = By.xpath("//form[@id='finalizeForm']/div//p-panel/div/div[@role='region']/div/div[7]/mat-checkbox//input[@type='checkbox']");
	public void setMilitaryActive() {
		if(!action.isCheckBoxChecked(byNotMilitaryActiveDutyChecked, "Not on Military Active Duty"))
			action.checkCheckBox(byNotMilitaryActiveDuty, "Not on Military Active Duty");
	}
	
	public boolean verifyAssertEqual(String expected, String actual) {
		return action.verifyEquals(expected, actual);
	}
	
	

}
