package com.loanmart.pages.LOS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.loanmart.action.Action;
import com.loanmart.base.TestBase;

public class AdditionalInfoPage {
	
	WebDriver webDriver;
	Action action;
	
	public AdditionalInfoPage(WebDriver webDriver, String testName) {
		this.webDriver = webDriver;
		this.action = new Action(testName);
	}
	
	public void savingAdditionalInfoPageLoad() {
		By byLoaading = By.xpath("//*[@id='mat-tab-content-0-0']/div/lm-account-lookup/lm-spinner/div");
		TestBase.log.debug("Waiting for invisibility of => Loading...");
		TestBase.wait.until(ExpectedConditions.invisibilityOfElementLocated(byLoaading));
		TestBase.log.debug("Is now invisibile => Loading...");
	}
	
	public void waitForAdditionalInfoPageLoad() {
		action.waitForPageLoad(byReasonForLoan);
	}
			
	//By byReasonForLoan = By.xpath("//*[@formcontrolname='loanReason']");
	By byReasonForLoan =  By.xpath("//form[@id='additionalInfoForm']/div/div[1]/p-panel/div/div[@role='region']/div/div/div[1]/mat-form-field//mat-select[@role='listbox']");
	public void setReasonForLoan(String value ) {
		action.setDropDown_ng(byReasonForLoan, value, "ReasonForLoan");
	}
	public String getReasonForLoan() {
		return action.getWebElementText(byReasonForLoan);
	}

	By byRequestedAmount = By.xpath("//*[@formcontrolname='requestedAmount']");
	public void setRequestedAmount(String value ) {
		action.setTextBox(byRequestedAmount, value, "RequestedAmount");
	}
	public String getRequestedAmount() {
		return action.getTextBox(byRequestedAmount);
	}
	
	By byRenting = By.xpath("//*[@formcontrolname='renting']");
	public void setRenting(String value ) {
		action.setTextBox(byRenting, value, "Renting");
	}
	public String getRenting() {
		return action.getTextBox(byRenting);
	}
	
	By byMortgage = By.xpath("//*[@formcontrolname='mortgage']");
	public void setMortgage(String value ) {
		action.setTextBox(byMortgage, value, "Mortgage");
	}
	public String getMortgage() {
		return action.getTextBox(byMortgage);
	}
	
	By byAlimony = By.xpath("//*[@formcontrolname='alimony']");
	public void setAlimony(String value ) {
		action.setTextBox(byAlimony, value, "Alimony");
	}
	public String getAlimony() {
		return action.getTextBox(byAlimony);
	}
	
	By byChildSupport = By.xpath("//*[@formcontrolname='childSupport']");
	public void setChildSupport(String value ) {
		action.setTextBox(byChildSupport, value, "ChildSupport");
	}
	public String getChildSupport() {
		return action.getTextBox(byChildSupport);
	}
	
	By byDriverLicenseNumber = By.xpath("//*[@formcontrolname='driverLicenseNumber']");
	public void setDriverLicenseNumber(String value ) {
		action.setTextBox(byDriverLicenseNumber, value, "DriverLicenseNumber");
	}
	public String getDriverLicenseNumber() {
		return action.getTextBox(byDriverLicenseNumber);
	}
	
	By byDriverLicenseState = By.xpath("//*[@formcontrolname='driverLicenseState']");
	public void setDriverLicenseState(String value ) {
		action.setDropDown_ng(byDriverLicenseState, value, "DriverLicenseState");
	}
	public String getDriverLicenseState() {
		return action.getWebElementText(byDriverLicenseState);
	}
	
	By byEmployerPhone = By.xpath("//*[@formcontrolname='employerPhone']");
	public void setEmployerPhone(String value ) {
		action.setTextBox(byEmployerPhone, value, "EmployerPhone");
	}
	public String getEmployerPhone() {
		return action.getTextBox(byEmployerPhone);
	}
	
	By byEmployerPhoneExtension = By.xpath("//*[@formcontrolname='employerPhoneExtension']");
	public void setEmployerPhoneExtension(String value ) {
		action.setTextBox(byEmployerPhoneExtension, value, "EmployerPhoneExtension");
	}
	public String getEmployerPhoneExtension() {
		return action.getTextBox(byEmployerPhoneExtension);
	}
	
	By byEmployerAddress = By.xpath("//*[@formcontrolname='employerAddress']");
	public void setEmployerAddress(String value ) {
		action.setTextBox(byEmployerAddress, value, "EmployerAddress");
	}
	public String getEmployerAddress() {
		return action.getTextBox(byEmployerAddress);
	}
	
	By byEmployerAddress2 = By.xpath("//*[@formcontrolname='employerAddress2']");
	public void setemployerAddress2(String value ) {
		action.setTextBox(byEmployerAddress2, value, "EmployerAddress2");
	}
	public String getEmployerAddress2() {
		return action.getTextBox(byEmployerAddress2);
	}
	
	By byEmployerCity = By.xpath("//*[@formcontrolname='employerCity']");
	public void setEmployerCity(String value ) {
		action.setTextBox(byEmployerCity, value, "EmployerCity");
	}
	public String getEmployerCity() {
		return action.getTextBox(byEmployerCity);
	}
	
	By byEmployerState = By.xpath("//*[@formcontrolname='employerState']");
	public void setEmployerState(String value ) {
		action.setDropDown_ng(byEmployerState, value, "EmployerState");
	}
	public String getEmployerState() {
		return action.getWebElementText(byEmployerState);
	}
	
	By byEmployerZip = By.xpath("//*[@formcontrolname='employerZip']");
	public void setEmployerZip(String value ) {
		action.setTextBox(byEmployerZip, value, "EmployerZip");
	}
	public String getEmployerZip() {
		return action.getTextBox(byEmployerZip);
	}
	
	public boolean verifyAssertEquals(String expected, String actual) {
		
		if(action.verifyEquals(expected, actual))
			return true;
		else
			return false;
	}

}
