package com.loanmart.actions;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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

public class Actions {

	
	//since these are static I might be able to just access them via the TestBase.static variable
	private String rptName;

	
	public Actions(String rptName) {
		this.rptName = rptName;
	}
	
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
	
	public boolean isElementVisible(By by, String message, String... noDisplayPassVerified) {

		if (isElementPresent(by, message, "noDisplayPassVerified")) {
			if (TestBase.driver.findElement(by).isDisplayed()) {
				if (noDisplayPassVerified.length == 0) {
					TestBase.log.debug("Verified is visible: " + message);
				}
				return true;
			} else {
				TestUtil.captureScreenshot(rptName);
				TestBase.log.debug("FAIL=> is not visible : " + message);
				// Extent Reports
				return false;
			}
		} else
			return false;
	}
	
	public void clickLink(By by, String linkText) {
		if (isTextVisible(by, linkText)) {
			webElement(by).click();
			TestBase.log.info("Clicked Link => " + linkText);
			CustomListeners.testLocal.log(LogStatus.INFO, "Clicked Link => " + linkText);
		}
	}
	

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

	public void clickButton(By by, String buttonName) {
		if (isElementPresent(by, buttonName, "noDisplayPassVerified")) {
			webElement(by).click();
			TestBase.log.info("Clicked Button => " + buttonName);
			CustomListeners.testLocal.log(LogStatus.INFO, "Clicked Button => " + buttonName);
		}
	}

	public void waitForPageLoad(By by) {
		TestBase.log.debug("Waiting for visibility of => " + by.toString());
		TestBase.wait.until(ExpectedConditions.elementToBeClickable(by));
		TestBase.log.debug("Is now visibile => " + by.toString());
	}

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

	public boolean isTextVisible(By by, String text) {
		if (isElementVisible(by, text, "noDisplayPassVerified")) {
			return verifyEquals(text, getWebElementText(by));
		} else
			return false;
	}

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

	public String getWebElementText(By by) {
		return webElement(by).getText();
	}
	
	public void clickButtonWhenClickable(By by, String buttonName) {
		TestBase.log.info("Waiting to be clickable => " + buttonName);
		TestBase.wait.until(ExpectedConditions.elementToBeClickable(by));
		TestBase.log.info("Is now clickable => " + buttonName);
		webElement(by).click();
		TestBase.log.info("Clicked Button => " + buttonName);
		CustomListeners.testLocal.log(LogStatus.INFO, "Clicked Button => " + buttonName);
	}

	public Boolean verifyEquals(String expected, String actual) {
		if (!actual.equals(expected)) {
			TestUtil.captureScreenshot(rptName);

			TestBase.log.debug("Verification failure : Expected= '" + expected + "' Actual= '" + actual);
			CustomListeners.testLocal.log(LogStatus.WARNING, "Verification failure :<br>Expected=" + expected
					+ "<br>Actual=&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + actual);
			CustomListeners.testLocal.log(LogStatus.WARNING, CustomListeners.testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
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
}
