package com.loanmart.action;

import java.util.ArrayList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.loanmart.utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.loanmart.base.TestBase;
import com.loanmart.listeners.CustomListeners;

/*##############################################################################*/
/*
 * This class performs all actions needed to interact with webpage.
 */
/*###############################################################################*/
public class Action {

	
	//since these are static I might be able to just access them via the TestBase.static variable
	private String rptName;

	
	public Action(String rptName) {
		this.rptName = rptName;
	}
	
	//returns webElement of By path
	public WebElement webElement(By by) {
		try {
			if (isElementVisible(by, "" + by, "noDisplayPassVerified")) {
				return TestBase.driver.findElement(by);
			} else
				return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	//checks to see if an element if visible
	public boolean isElementVisible(By by, String message, String... noDisplayPassVerified) {

		if (isElementPresent(by, message, "noDisplayPassVerified")) {
			if (TestBase.driver.findElement(by).isDisplayed()) {
				if (noDisplayPassVerified.length == 0) {
					TestBase.log.debug("Verified is visible: " + message);
					CustomListeners.testLocal.log(LogStatus.PASS, message);
				}
				return true;
			} else {
				TestUtil.captureScreenshot(rptName);
				TestBase.log.debug("FAIL=> is not visible : " + message);
				CustomListeners.testLocal.log(LogStatus.FAIL, message);
				CustomListeners.testLocal.log(LogStatus.FAIL, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
				// Extent Reports
				return false;
			}
		} else
			return false;
	}
	
	//clicks a link
	public void clickLink(By by, String linkText) {
		if (isTextVisible(by, linkText)) {
			webElement(by).click();
			TestBase.log.info("Clicked Link => " + linkText);
			CustomListeners.testLocal.log(LogStatus.INFO, "Clicked Link => " + linkText);
		}
	}
	
	//checks to see if an element is present
	public boolean isElementPresent(By by, String message, String... noDisplayPassVerified) {

		try {
			TestBase.driver.findElement(by);
			if (noDisplayPassVerified.length == 0) { // don't know what this does??
				TestBase.log.debug("Verified is present: " + message);
				CustomListeners.testLocal.log(LogStatus.PASS, "Verified is present: " + message);

			}
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {

			TestUtil.captureScreenshot(rptName);

			TestBase.log.debug("FAIL=> is not present: " + message);
			// Extent Reports

			
			CustomListeners.testLocal.log(LogStatus.FAIL, "FAIL=> is not present: " + message);
			CustomListeners.testLocal.log(LogStatus.FAIL, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
			return false;
		}
	}
	
	//enters data in a textbox field
	public void setTextBox(By by, String value, String textBoxName) {
		if (isElementPresent(by, textBoxName, "noDisplayPassVerified")) {
			if (textBoxName.equals("MobileNumber") || textBoxName.equals("HomeNumber") || textBoxName.equals("SSN")) {
				webElement(by).click();
				webElement(by).sendKeys(value);
			} else {
				webElement(by).sendKeys(value);
			}
			TestBase.log.info("Entered => " + textBoxName + " = " + value);
			CustomListeners.testLocal.log(LogStatus.INFO, "Entered => " + textBoxName + " = " + value);

		}
	}

	//selects an item from a drop down list.  If not found it throws a Assert. fail and stops the testing suite
	public void setDropDownFailPass(By by, String value, String dropDownName, String failMessage) {
		WebElement dropdown = webElement(by);
		Select select = new Select(dropdown);

		try {
			select.selectByVisibleText(value);
			TestBase.log.debug("Selected from Dropdown => " + dropDownName + " = " + value);
			CustomListeners.testLocal.log(LogStatus.INFO, "Selected from Dropdown => " + dropDownName + " = " + value);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			TestUtil.captureScreenshot(rptName);
			// Extent Reports
			TestBase.log.debug("FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			CustomListeners.testLocal.log(LogStatus.FAIL, "FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			CustomListeners.testLocal.log(LogStatus.FAIL, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
			Assert.fail(failMessage);
		}

	}

	//selects an item from a drop down list.  If not found it does not throw an exception but reports as a fail step in the report
	public void setDropDown(By by, String value, String dropDownName) {
		WebElement dropdown;
		dropdown = webElement(by);

		Select select = new Select(dropdown);
		try {
			select.selectByVisibleText(value);
			TestBase.log.info("Selected from Dropdown => " + dropDownName + " = " + value);
			CustomListeners.testLocal.log(LogStatus.INFO, "Selected from Dropdown => " + dropDownName + " = " + value);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			TestUtil.captureScreenshot(rptName);
			TestBase.log.debug("FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			// Extent Reports
			
			CustomListeners.testLocal.log(LogStatus.FAIL, "FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			CustomListeners.testLocal.log(LogStatus.FAIL, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));

		}

	}

	//click a button
	public void clickButton(By by, String buttonName) {
		if (isElementPresent(by, buttonName, "noDisplayPassVerified")) {
			webElement(by).click();
			TestBase.log.info("Clicked Button => " + buttonName);
			CustomListeners.testLocal.log(LogStatus.INFO, "Clicked Button => " + buttonName);
		}
	}

	//waits for a page to load based on the element to be clickable By path
	public void waitForPageLoad(By by) {
		TestBase.log.debug("Waiting for visibility of => " + by.toString());
		TestBase.wait.until(ExpectedConditions.elementToBeClickable(by));
		TestBase.log.debug("Is now visibile => " + by.toString());
	}

	//checks if an element is not visible
	public boolean isNotElementVisible(By by, String message, String... noDisplayPassVerified) {

		try {
			if (TestBase.driver.findElement(by).isDisplayed()) {
				TestUtil.captureScreenshot(rptName);

				TestBase.log.debug("FAIL=> is visible : " + message);
				// Extent Reports

				
				CustomListeners.testLocal.log(LogStatus.FAIL, "FAIL=> is visible : " + message);
				CustomListeners.testLocal.log(LogStatus.FAIL, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));

				return false;
			} else {
				if (noDisplayPassVerified.length == 0) {
					TestBase.log.debug("Verified is NOT visible: " + message);
					CustomListeners.testLocal.log(LogStatus.PASS, "Verified is NOT visible: " + message);

				}
				return true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			if (noDisplayPassVerified.length == 0) {
				TestBase.log.debug("Verified is NOT visible: " + message);
				CustomListeners.testLocal.log(LogStatus.PASS, "Verified is NOT visible: " + message);
			}
			return true;
		}
	}
	
	//check if text is visible
	public boolean isTextVisible(By by, String text) {
		if (isElementVisible(by, text, "noDisplayPassVerified")) {
			return verifyEquals(text, getWebElementText(by));
		} else
			return false;
	}

	//checks if text is not visible
	public boolean isNotTextVisible(By by, String text, String... noDisplayPassVerified) {
		try {
			if (TestBase.driver.findElement(by).isDisplayed()) {
				if (getWebElementText(by).equalsIgnoreCase(text)) {
					TestUtil.captureScreenshot(rptName);
					TestBase.log.debug("FAIL=> is visible : " + text);
					// Extent Reports
					
					CustomListeners.testLocal.log(LogStatus.FAIL, "FAIL=> is visible : " + text);
					CustomListeners.testLocal.log(LogStatus.FAIL, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
					return false;
				} else {
					if (noDisplayPassVerified.length == 0) {
						TestBase.log.debug("Verified is NOT visible: " + text);
						CustomListeners.testLocal.log(LogStatus.PASS, "Verified is NOT visible: " + text);
					}
					return true;
				}
			} else {
				if (noDisplayPassVerified.length == 0) {
					TestBase.log.debug("Verified is NOT visible: " + text);
					CustomListeners.testLocal.log(LogStatus.PASS, "Verified is NOT visible: " + text);
				}
				return true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			if (noDisplayPassVerified.length == 0) {
				TestBase.log.debug("Verified is NOT visible: " + text);
				CustomListeners.testLocal.log(LogStatus.PASS, "Verified is NOT visible: " + text);
			}
			return true;
		}
	}

	//returns the text of a webelement 
	public String getWebElementText(By by) {
		return webElement(by).getText();
	}
	
	//clicks on a button as soon as it becomes clickable.
	public void clickButtonWhenClickable(By by, String buttonName) {
		TestBase.log.info("Waiting to be clickable => " + buttonName);
		TestBase.wait.until(ExpectedConditions.elementToBeClickable(by));
		TestBase.log.info("Is now clickable => " + buttonName);
		webElement(by).click();
		TestBase.log.info("Clicked Button => " + buttonName);
		CustomListeners.testLocal.log(LogStatus.INFO, "Clicked Button => " + buttonName);
	}

	//checks if two strings are equal or not.
	public Boolean verifyEquals(String expected, String actual) {
		if (!actual.equals(expected)) {
			TestUtil.captureScreenshot(rptName);

			TestBase.log.debug("Verification failure : Expected= '" + expected + "' Actual= '" + actual);
			CustomListeners.testLocal.log(LogStatus.FAIL, "Verification failure :<br>Expected=" + expected
					+ "<br>Actual=&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + actual);
			CustomListeners.testLocal.log(LogStatus.FAIL, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
			// Assert.fail(" Assertion failure => Expected=" + expected + " but found=" +
			// actual);
			return false;
		} else {
			TestBase.log.debug("Verified Expected and Actual are the same => " + actual);
			CustomListeners.testLocal.log(LogStatus.PASS, "Verified Expected and Actual are the same => " + actual);
			// test.log(LogStatus.PASS, test.addScreenCapture(TestUtil.screenshotName));
			return true;
		}

	}
	
	//checks if two strings are NOT equal
	public boolean verifyNotEquals(String expected, String actual) {
		if(actual.equals(expected)) {
			TestUtil.captureScreenshot(rptName);

			TestBase.log.debug("Verification failure : Expected= '" + expected + " is the same as ' Actual= '" + actual);
			CustomListeners.testLocal.log(LogStatus.WARNING, "Verification failure :<br>Expected=" + expected
					+ "<br> is the same as Actual=&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + actual);
			CustomListeners.testLocal.log(LogStatus.WARNING, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
			return false;
		}else {
			TestBase.log.debug("Verified Expected and Actual are NOT the same => " + actual);
			CustomListeners.testLocal.log(LogStatus.PASS, "Verified Expected and Actual are NOT the same => " + actual);
			return true;
		}
	}
	
	//checks to see if string contains substring
	public boolean verifyContains(String subString, String completeString) {
		if(completeString.contains(subString)) {
			TestBase.log.debug("Verified "+completeString+" --contains => "+subString);
			CustomListeners.testLocal.log(LogStatus.PASS, "Verified "+completeString+" --contains => "+subString);
			return true;
		}else {
			TestBase.log.debug("Verificaiton failure : "+completeString+" --DOES NOT contain => "+subString);
			TestUtil.captureScreenshot(rptName);
			CustomListeners.testLocal.log(LogStatus.FAIL, "Verificaiton failure : "+completeString+" --DOES NOT contain => "+subString);
			CustomListeners.testLocal.log(LogStatus.FAIL, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
			return false;
		}
	}
	
	//clicks on a dropdown list and then selects one item from the list
	//simulating the human approach to selecting an item from a dropdown
	public void setDropDown_ng(By by, String value, String dropDownName) {
		try {
			Thread.sleep(2000);
			webElement(by).click();
			Thread.sleep(100);
			String path = "//div[@class='cdk-overlay-pane']/div//span[contains(text(),'"+value+"')]";
			System.out.println(path);
			//TestBase.driver.findElement(By.xpath("//div[@class='cdk-overlay-pane']//span[contains(text(),'" + value + "')]"))
					//.click();
			TestBase.driver.findElement(By.xpath(path)).click();
			TestBase.log.info("Selected from Dropdown => " + dropDownName + " = " + value);
			CustomListeners.testLocal.log(LogStatus.INFO, "Selected from Dropdown => " + dropDownName + " = " + value);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			TestUtil.captureScreenshot(rptName);
			TestBase.log.debug("FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			// Extent Reports
			
			CustomListeners.testLocal.log(LogStatus.FAIL, "FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			CustomListeners.testLocal.log(LogStatus.FAIL, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	//gets the string value of a textbox
	public String getTextBox(By by) {
		String value = webElement(by).getAttribute("value");
		TestBase.log.info("Got Textbox value => " + by.toString() + " = " + value);
		return value;
	}
	
	//scrolls page all the way down
	public void scrollPageDown() {
		
		TestBase.js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
	}
	
	//scrolls page all the way up
	public void scrollPageUp() {
		TestBase.js.executeScript("window.scrollTo(0, 0)");
	}
	
	//this will open a link in a new tab as if user was pressing Ctrl+ left_click on mouse
	public boolean openLinkInNewTab(By by, String message, String...strings ) {
		if (isElementVisible(by, message, "noDisplayPassVerified")) {
			TestBase.act.keyDown(Keys.CONTROL).click(TestBase.driver.findElement(by)).keyUp(Keys.CONTROL).perform();
			if(strings.length == 0) {	
				CustomListeners.testLocal.log(LogStatus.PASS, "Opened in new tab ==> "+message);
			}
			return true;
		}else {
			return false;
		}
	}
	
	//this will make the driver switch to a new window handle
	public void switchWindowHandles(String windHandle) {
		TestBase.driver.switchTo().window(windHandle);
	}
	
	//this will get all the current open winHandles
	public ArrayList<String> getWindowHandles() {
		ArrayList<String> handles = new ArrayList<String> (TestBase.driver.getWindowHandles());
		return handles;
	}
	
	//returns the current window handle
	public String getCurrentWindowHandle() {
		return TestBase.driver.getWindowHandle();
	}
	
	//navigates to another url
	public void navigateToAnotherPage(String url) {
		TestBase.driver.navigate().to(url);
	}
	
	//gets the URL of the current page
	public String getCurrentURL() {
		return TestBase.driver.getCurrentUrl();
	}
	
	//closes the current window handle
	public void closeCurrentWindowHandle() {
		TestBase.driver.close();
	}
	
	//screenshot function that takes status, message and the screenshot name. 
	//Uses testutil class to capture screenshot
	public void takeScreentShot(String status, String message, String screenShotName) {
		
		switch(status.toLowerCase()) {
		
		case "pass":
			TestUtil.captureScreenshot(screenShotName);
			CustomListeners.testLocal.log(LogStatus.PASS, message);
			CustomListeners.testLocal.log(LogStatus.PASS, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
			break;
			
		case "fail":
			TestUtil.captureScreenshot(screenShotName);
			CustomListeners.testLocal.log(LogStatus.FAIL, message);
			CustomListeners.testLocal.log(LogStatus.FAIL, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
			break;
			
		case "info":
			TestUtil.captureScreenshot(screenShotName);
			CustomListeners.testLocal.log(LogStatus.INFO, message);
			CustomListeners.testLocal.log(LogStatus.INFO, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
			break;
		
		default:
			CustomListeners.testLocal.log(LogStatus.ERROR, "INVALID STATUS ARGUMENT");
		}
	}
	
	//resizes browser window
	public void resizeWin(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.resizeTo(1980,1080)");
		//TestBase.js.executeScript("window.resizeTo(1980,1080)");
	}
	
	//checks a checkbox
	public void checkCheckBox(By by, String checkboxName) {
		try {
			webElement(by).click();
			TestBase.log.debug("Checked option => " + checkboxName);
		}catch(org.openqa.selenium.NoSuchElementException e) {
			TestUtil.captureScreenshot(rptName);

			TestBase.log.debug("FAIL=> is not present: " + checkboxName);
			// Extent Reports
			
			CustomListeners.testLocal.log(LogStatus.FAIL, "FAIL=> is not present: " + checkboxName);
			CustomListeners.testLocal.log(LogStatus.FAIL, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
			
		}
	}
	
	//checks to see if a checbox is selected or not
	public boolean isCheckBoxChecked(By by, String checkBoxName) {
		if(webElement(by).isSelected()) {
			return true;
		}
		else {
			return false;
		}
	}
}

