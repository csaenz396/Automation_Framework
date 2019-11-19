package com.loanmart.pages.LOS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.loanmart.action.Action;
import com.loanmart.base.TestBase;
import com.loanmart.listeners.CustomListeners;
import com.relevantcodes.extentreports.LogStatus;

public class VehiclePage{
	
	WebDriver webDriver;
	Action action;

	public VehiclePage(WebDriver driver, String testName) {
		this.webDriver = driver;
		action = new Action(testName);
		PageFactory.initElements(driver, this);
	}

	public void waitForVehiclePageLoad() {
		action.waitForPageLoad(byColor);
	}
	
	@FindBy(xpath = "//div[contains(text(),'Regular')]")
	WebElement regularVehicleType;

	public void clickRegularVehicleType() {
		regularVehicleType.click();
	}

	@FindBy(id = "mat-input-20")
	WebElement vin;

	public void setVin(String value) {
		vin.sendKeys(value);
	}

	By byYear =By.xpath("//*[@id='mat-select-5']//span");
	public void setYear(String value) throws InterruptedException {
		action.setDropDown_ng(byYear, value, "Year");
	}
	public String getYear() {
		return action.getWebElementText(byYear);
	}

	By byMake = By.xpath("//*[@id=\"mat-select-6\"]//span");
	public void setMake(String value) throws InterruptedException {
		action.setDropDown_ng(byMake, value, "Make");
	}
	public String getMake() {
		return action.getWebElementText(byMake);
	}

	By byModel =By.xpath("//*[@id=\"mat-select-7\"]//span");
	public void setModel(String value) throws InterruptedException {
		action.setDropDown_ng(byModel, value, "Model");
	}
	public String getModel() {
		return action.getWebElementText(byModel);
	}

	By byTrim = By.xpath("//*[@id=\"mat-select-8\"]//span");
	public void setTrim(String value) throws InterruptedException {
		action.setDropDown_ng(byTrim, value, "Trim");
	}
	public String getTrim() {
		return action.getWebElementText(byTrim);
	}
	
	By byMilege = By.id("mat-input-22");
	public void setMileage(String value) {
		action.setTextBox(byMilege, value, "Milege");
	}
	public String getMileage() {
		return action.getTextBox(byMilege);
	}

	@FindBy(id = "mat-input-22")
	WebElement license;

	public void setLicense(String value) {
		license.sendKeys(value);
	}

	By byColor = By.xpath("//*[@formcontrolname='vehicleColor']");
	public void setColor(String value) throws InterruptedException {
		action.setDropDown_ng(byColor, value, "Color");
	}

	@FindBy(id = "mat-input-23")
	WebElement vehicleValue;

	public String getVehicleValue() {
		return vehicleValue.getAttribute("value");
	}

	@FindBy(id = "mat-input-24")
	WebElement vehicleResult;

	public String getVehicleResult() {
		return vehicleResult.getAttribute("value");
	}

	@FindBy(xpath = "//span[contains(text(),'Reset')]")
	WebElement resetButton;

	public void clickResetButton() {
		resetButton.click();
	}

	////////////////////////////////////////////////////
	// Vehicle Questions ///////////////////////////////

	@FindBy(xpath = "//label[contains(text(),'Title Status?')]//parent::div//following-sibling::div//span")
	WebElement titleStatus;

	public void setTitleStatus(String value) {
		titleStatus.click();
		//Thread.sleep(2000);
		TestBase.driver.findElement(By.xpath(
				"//div[@class='cdk-overlay-pane'][contains(@style,'pointer-events: auto;')]//span[contains(text(),'"
						+ value + "')]"))
				.click();
		TestBase.log.debug("Set title status to => "+ value);
		CustomListeners.testLocal.log(LogStatus.INFO, "Set title status to => "+value);
	}

	@FindBy(xpath = "//label[contains(text(),'State Title Registered?')]//parent::div//following-sibling::div//span")
	WebElement stateTitleRegistered;

	public void setStateTitleRegistered(String value) throws InterruptedException {
		stateTitleRegistered.click();
		Thread.sleep(2000);
		TestBase.driver.findElement(By.xpath(
				"//div[@class='cdk-overlay-pane'][contains(@style,'pointer-events: auto;')]//span[contains(text(),'"
						+ value + "')]"))
				.click();
		
		TestBase.log.debug("Set state title registered to => "+ value);
		CustomListeners.testLocal.log(LogStatus.INFO,"Set state title registered to => "+ value);
	}

	@FindBy(xpath = "//*[@formcontrolname='stateTitleRegisteredNote']")
	WebElement stateTitleRegisteredNote;

	public void setStateTitleRegisteredNote(String value) throws InterruptedException {
		stateTitleRegisteredNote.sendKeys(value);
	}

	@FindBy(xpath = "//label[contains(text(),'Vehicle Paid Off?')]//parent::div//following-sibling::div//span")
	WebElement vehiclePaidOff;

	public void setVehiclePaidOff(String value) throws InterruptedException {
		vehiclePaidOff.click();
		Thread.sleep(2000);
		TestBase.driver.findElement(By.xpath(
				"//div[@class='cdk-overlay-pane'][contains(@style,'pointer-events: auto;')]//span[contains(text(),'"
						+ value + "')]"))
				.click();
		
		TestBase.log.debug("Set vehicle paid off to => "+ value);
		CustomListeners.testLocal.log(LogStatus.INFO,"Set vehicle paid off to to => "+ value);
	}

	@FindBy(xpath = "//*[@formcontrolname='vehiclePaidOffNote']")
	WebElement vehiclePaidOffNote;

	public void setVehiclePaidOffNote(String value) throws InterruptedException {
		vehiclePaidOffNote.sendKeys(value);
	}

	@FindBy(xpath = "//label[contains(text(),'Running and No Damage?')]//parent::div//following-sibling::div//span")
	WebElement runningAndNoDamage;

	public void setRunningAndNoDamage(String value) throws InterruptedException {
		runningAndNoDamage.click();
		Thread.sleep(2000);
		TestBase.driver.findElement(By.xpath(
				"//div[@class='cdk-overlay-pane'][contains(@style,'pointer-events: auto;')]//span[contains(text(),'"
						+ value + "')]"))
				.click();
		
		TestBase.log.debug("Set running and no damage to => "+ value);
		CustomListeners.testLocal.log(LogStatus.INFO,"Set running and no damage to => "+ value);
	}

	@FindBy(xpath = "//*[@formcontrolname='noDamageNote']")
	WebElement runningAndNoDamageNote;

	public void setRunningAndNoDamageNote(String value) throws InterruptedException {
		runningAndNoDamageNote.sendKeys(value);
	}

	////////////////////////////////////////////////////
	// Low Vehicle Value Additional Questions //////////

	@FindBy(xpath = "//label[contains(text(),'1. How long have you owned this car?')]//parent::div//following-sibling::div//span")
	WebElement howLongHaveYouOwnedThisCar;

	public void setHowLongHaveYouOwnedThisCar(String value) throws InterruptedException {
		howLongHaveYouOwnedThisCar.click();
		Thread.sleep(2000);
		TestBase.driver.findElement(By.xpath(
				"//div[@class='cdk-overlay-pane'][contains(@style,'pointer-events: auto;')]//span[contains(text(),'"
						+ value + "')]"))
				.click();
	}

	@FindBy(xpath = "//label[contains(text(),'2. Do you drive this car to work?')]//parent::div//following-sibling::div//span")
	WebElement doYouDriveThisCarToWork;

	public void setDoYouDriveThisCarToWork(String value) throws InterruptedException {
		doYouDriveThisCarToWork.click();
		Thread.sleep(2000);
		TestBase.driver.findElement(By.xpath(
				"//div[@class='cdk-overlay-pane'][contains(@style,'pointer-events: auto;')]//span[contains(text(),'"
						+ value + "')]"))
				.click();
	}

	@FindBy(xpath = "//label[contains(text(),'3. Are you able to provide proof of insurance for your car?')]//parent::div//following-sibling::div//span")
	WebElement areYouAbleToProvideProofOfInsurance;

	public void setAreYouAbleToProvideProofOfInsurance(String value) throws InterruptedException {
		areYouAbleToProvideProofOfInsurance.click();
		Thread.sleep(2000);
		TestBase.driver.findElement(By.xpath(
				"//div[@class='cdk-overlay-pane'][contains(@style,'pointer-events: auto;')]//span[contains(text(),'"
						+ value + "')]"))
				.click();
	}

	@FindBy(xpath = "//label[contains(text(),'4. How long have you been in your current job?')]//parent::div//following-sibling::div//span")
	WebElement howLongHaveYouBeenInYourCurrentJob;

	public void setHowLongHaveYouBeenInYourCurrentJob(String value) throws InterruptedException {
		howLongHaveYouBeenInYourCurrentJob.click();
		Thread.sleep(2000);
		TestBase.driver.findElement(By.xpath(
				"//div[@class='cdk-overlay-pane'][contains(@style,'pointer-events: auto;')]//span[contains(text(),'"
						+ value + "')]"))
				.click();
	}

	@FindBy(xpath = "//label[contains(text(),'5. Will you be able to drive your car')]//parent::div//following-sibling::div//span")
	WebElement willYouBeAbleToDriveYourCar;

	public void setWillYouBeAbleToDriveYourCar(String value) throws InterruptedException {
		willYouBeAbleToDriveYourCar.click();
		Thread.sleep(2000);
		TestBase.driver.findElement(By.xpath(
				"//div[@class='cdk-overlay-pane'][contains(@style,'pointer-events: auto;')]//span[contains(text(),'"
						+ value + "')]"))
				.click();
	}

	@FindBy(xpath = "//label[contains(text(),'6. Are you able to provide proof that')]//parent::div//following-sibling::div//span")
	WebElement areYouAbleToProvideProof;

	public void setAreYouAbleToProvideProof(String value) throws InterruptedException {
		areYouAbleToProvideProof.click();
		Thread.sleep(2000);
		TestBase.driver.findElement(By.xpath(
				"//div[@class='cdk-overlay-pane'][contains(@style,'pointer-events: auto;')]//span[contains(text(),'"
						+ value + "')]"))
				.click();
	}

	@FindBy(xpath = "//label[contains(text(),'7. Do you have another car in your household?')]//parent::div//following-sibling::div//span")
	WebElement doYouHaveAnotherCar;

	public void setDoYouHaveAnotherCar(String value) throws InterruptedException {
		doYouHaveAnotherCar.click();
		Thread.sleep(2000);
		TestBase.driver.findElement(By.xpath(
				"//div[@class='cdk-overlay-pane'][contains(@style,'pointer-events: auto;')]//span[contains(text(),'"
						+ value + "')]"))
				.click();
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
