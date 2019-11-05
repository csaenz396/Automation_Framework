package com.loanmart.pages.LOS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.loanmart.action.Action;
import com.loanmart.base.TestBase;

public class AddressPage {
	
	WebDriver webDriver;
	Action action;
	
	public AddressPage(WebDriver webDriver, String testName) {
		this.webDriver = webDriver;
		this.action = new Action(testName);
		PageFactory.initElements(webDriver, this);
	}
	
	////////////////////////////////////////////////////////
	// Physical Address ////////////////////////////////////
	
	By byAddress1 = By.xpath("//*[@formcontrolname='address1']");
	public void setAddress1(String value) {
		action.setTextBox(byAddress1, value, "Address1");
	}
	public String getAddress1() {
		return action.getTextBox(byAddress1);
	}
	
	By byAddress2 = By.xpath("//*[@formcontrolname='address2']");
	public void setAddress2(String value) {
		action.setTextBox(byAddress2, value, "Address2");
	}
	public String getAddress2() {
		return action.getTextBox(byAddress2);
	}
			
	By byCity = By.xpath("//*[@formcontrolname='city']");
	public void setCity(String value) {
		action.setTextBox(byCity, value, "City");
	}
	public String getCity() {
		return action.getTextBox(byCity);
	}
	
	By byState = By.xpath("//*[@formcontrolname='state']");
	public void setState(String value) throws InterruptedException {
		action.setDropDown_ng(byState, value, "State");
	}
	public String getState() {
		return action.getWebElementText(byState);
	}
	
	By byZip = By.xpath("//*[@formcontrolname='zip']");
	public void setZip(String value) {
		action.setTextBox(byZip, value, "Zip");
	}
	public String getZip() {
		return action.getTextBox(byZip);
	}
	
	By byYearsAtAddress = By.xpath("//*[@formcontrolname='yearsAtAddress']");
	public void setYearsAtAddress(String value) throws InterruptedException {
		action.setDropDown_ng(byYearsAtAddress, value, "Select Years At Address");
	}
	public String getYearsAtAddress() {
		return action.getWebElementText(byYearsAtAddress);
	}
	
	By byMonthsAtAddress = By.xpath("//*[@formcontrolname='monthsAtAddress']");
	public void setMonthsAtAddress(String value) throws InterruptedException {
		action.setDropDown_ng(byMonthsAtAddress, value, "Select Months At Address");
	}
	public String getMonthsAtAddress() {
		return action.getWebElementText(byMonthsAtAddress);
	}

	////////////////////////////////////////////////////////
	// Mailing Address /////////////////////////////////////
	
	@FindBy(xpath="//*[@formcontrolname='mailingAddress1']")
	WebElement mailingAddress1;

	public void setMailingAddress1(String value) {
		mailingAddress1.sendKeys(value);
	}
	
	@FindBy(xpath="//*[@formcontrolname='mailingAddress2']")
	WebElement mailingAddress2;

	public void setMailingAddress2(String value) {
		mailingAddress2.sendKeys(value);
	}
			
	@FindBy(xpath = "//*[@formcontrolname='mailingCity']")
	WebElement mailingCity;

	public void setMailingCity(String value) {
		mailingCity.sendKeys(value);
	}
	
	@FindBy(xpath = "//*[@formcontrolname='mailingState']")
	WebElement mailingState;

	public void setMailingState(String value) throws InterruptedException {
		mailingState.click();
		Thread.sleep(2000);
		TestBase.driver.findElement(By.xpath("//div[@class='cdk-overlay-pane']//span[contains(text(),'" + value + "')]")).click();
	}
	
	@FindBy(xpath = "//*[@formcontrolname='mailingZip']")
	WebElement mailingZip;

	public void setMailingZip(String value) {
		mailingZip.sendKeys(value);
	}
	
	By byCopyPhysicalAddressButton = By.xpath("//*[@id='ui-panel-9-content']//button");
	public void copyPhysicalAddressButtonCLick() {
		action.clickButton(byCopyPhysicalAddressButton, "CopyPhysicalAddressButton");
	}

}
