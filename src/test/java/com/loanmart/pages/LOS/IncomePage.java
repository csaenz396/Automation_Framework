package com.loanmart.pages.LOS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.loanmart.action.Action;

public class IncomePage {
	WebDriver webDriver;
	Action action;
	
	public IncomePage(WebDriver webDriver, String testName) {
		this.webDriver = webDriver;
		action = new Action(testName);
	}
	
	public void waitForIncomePageLoad() {
		action.waitForPageLoad(byIncomeType);
	}
	
	////////////////////////////////////////////////////////
	// Application Primary Income Information //////////////
	
	By byIncomeType = By.xpath("//*[@formcontrolname='sourceOfIncome']");
	public void setIncometype(String value) throws InterruptedException {
		action.setDropDown_ng(byIncomeType, value, "IncomeType");
	}
	public String getIncomeType() {
		return action.getWebElementText(byIncomeType);
	}

	By byGrossMonthlyIncome = By.xpath("//*[@formcontrolname='grossMonthlyIncome']");
	public void setGrossMonthlyIncome(String value) {
		action.setTextBox(byGrossMonthlyIncome, value, "GrossMonthlyIncome");
	}
	public String getGrossMonthlyIncome() {
		return action.getTextBox(byGrossMonthlyIncome);
	}
	
	By byEmployerName =By.xpath("//*[@formcontrolname='employerName']");
	public void setEmployerName(String value) {
		action.setTextBox(byEmployerName, value, "EmployerName");
	}
	public String getEmployerName() {
		return action.getTextBox(byEmployerName);
	}

	By byEmployeeTitle = By.xpath("//*[@formcontrolname='jobTitle']");
	public void setEmployeeTitle(String value) {
		action.setTextBox(byEmployeeTitle, value, "EmployeeTitle");
	}
	public String getEmployeeTitle() {
		return action.getTextBox(byEmployeeTitle);
	}
		
	By byYearsAtEmployer = By.xpath("//*[@formcontrolname='yearsAtEmployer']");
	public void setYearsAtEmployer(String value) throws InterruptedException {
		action.setDropDown_ng(byYearsAtEmployer, value, "YearsAtEmployer");
	}
	public void getYearsAtEmployer() {
		action.getWebElementText(byYearsAtEmployer);
	}
	
	By byMonthsAtEmployer = By.xpath("//*[@formcontrolname='monthsAtEmployer']");
	public void setMonthsAtEmployer(String value) throws InterruptedException {
		action.setDropDown_ng(byMonthsAtEmployer, value, "MonthsAtEmployer");
	}
	public void getMonthsAtEmployer() {
		action.getWebElementText(byMonthsAtEmployer);
	}
	public boolean verifyAssertEquals(String expected, String actual) {
		return action.verifyEquals(expected, actual);
	}
}
