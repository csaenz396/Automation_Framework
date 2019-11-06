package com.loanmart.pages.LOS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.loanmart.action.Action;
import com.loanmart.base.TestBase;

public class MainPage {
	
	
	WebDriver webDriver;
	Action action;

	public MainPage(WebDriver driver, String testName) {
		this.webDriver = driver;
		this.action = new Action(testName);
	}
	
	//@FindBy(xpath = "//label[contains(text(),'Title Status?')]//parent::div//following-sibling::div//span")
	
	By byVehicleTab = By.xpath("//*[@id=\"mat-tab-label-1-1\"]/div");
	public void clickVehicleTab() {
		action.webElement(byVehicleTab).click();
	}
	
	By byNextButton = By.xpath("//span[contains(text(),'Next')]/parent::button");
	public void clickNextButton() {
		action.clickButtonWhenClickable(byNextButton,"NextButton");
	}
	
	By byNextButtonVisibility = By.xpath("//app-root/div[@class='layout-wrapper ng-star-inserted']/div[@class='layout-main']/div[@class='layout-content']/ng-component[@class='ng-star-inserted']/div/div[3]/div[5]/button[@type='button']");
	public void waitForNextButttonVisibility() {
		TestBase.log.debug("Waiting for availability of => Next...");
		TestBase.wait.until(ExpectedConditions.elementToBeClickable(byNextButton));
		TestBase.log.debug("Is now visible => Next...");
	}

	By byPullCreditButton = By.xpath("//span[contains(text(),'Pull Credit')]");
	public void clickPullCreditButton() {
		action.clickButton(byPullCreditButton,"PullCreditButton");
	}
	
	public void wiatForPullCreditButtonVisibility() {
		TestBase.log.debug("Waiting for availability of => PULL CREDIT...");
		TestBase.wait.until(ExpectedConditions.elementToBeClickable(byPullCreditButton));
		TestBase.log.debug("Is now visible => PULL CREDIT...");
	}
	
	By bySalesPitchButton = By.xpath("//form[@id='decisionForm']/div/div/p-panel/div/div[@role='region']//button[@label='Sales Pitch Tool']");
	public void waitForSalePitchButtonVisibility() {
		TestBase.wait.until(ExpectedConditions.elementToBeClickable(bySalesPitchButton));
	}

	By bySave = By.xpath("//span[contains(text(),'Save')]");
	public void clickSaveButton() {
		action.clickButton(bySave,"Save");
	}

}
