package com.loanmart.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.loanmart.utilities.ExcelReader;
import com.loanmart.utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
@Listeners(com.loanmart.listeners.CustomListeners.class)
public class TestBase {

	public static WebDriver driver;//
	private ChromeOptions chromeOptions = new ChromeOptions();
	public static FileInputStream fis;
	public static WebDriverWait wait;//
	public static Properties config = new Properties();//
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader();
	public static String rptName;
	
	public ExtentReports repQA;
	public ExtentReports repLocal;
	public static ExtentTest testQA;
	public static ExtentTest testLocal;
	public static String mileStone = System.getProperty("MILE_STONE");
	public static String devEnvironment = System.getProperty("DEV_ENV");

	@BeforeSuite(alwaysRun = true)
	public void setUpSuite(ITestContext testName) {
		//System.out.println("************************INSIDE BEFORE CLASS******************************");
		rptName = testName.getName();
		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded.");
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("Firefox Lunched.");
			} else if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				//chromeOptions.addArguments("disable-infobars");
				//chromeOptions.addArguments("--incognito");
				//chromeOptions.addArguments("--disable-gpu");
				//chromeOptions.addArguments("--no-sandbox");
				//chromeOptions.addArguments("--allow-insecure-localhost");
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--window-size=1980,1080");
				driver = new ChromeDriver(chromeOptions);
				log.debug("Chrome Lunched.");
			} else if (config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.debug("Internet Explorer Lunched.");
			}

			driver.manage().deleteAllCookies();
			System.out.println(driver.manage().window().getSize());
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 240);
		}

	}
	
	@AfterSuite
	public void tearDownSuite() {
		if (driver != null)
			driver.quit();
		log.debug("Class Test Execution completed");
	}

	public static WebElement webElement(By by) {
		try {
			if (isElementVisible(by, "" + by, "noDisplayPassVerified")) {
				return driver.findElement(by);
			} else
				return null;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isElementVisible(By by, String message, String... noDisplayPassVerified) {

		if (isElementPresent(by, message, "noDisplayPassVerified")) {
			if (driver.findElement(by).isDisplayed()) {
				if (noDisplayPassVerified.length == 0) {
					log.debug("Verified is visible: " + message);
				}
				return true;
			} else {
				TestUtil.captureScreenshot(rptName);
				log.debug("FAIL=> is not visible : " + message);
				// Extent Reports
				return false;
			}
		} else
			return false;
	}

	public void clickLink(By by, String linkText) {
		if (isTextVisible(by, linkText)) {
			webElement(by).click();
			log.info("Clicked Link => " + linkText);
			/*
			 * testQA.log(LogStatus.INFO, "Clicked Link => " + linkText);
			 * testLocal.log(LogStatus.INFO, "Clicked Link => " + linkText);
			 */
		}
	}

	public static boolean isElementPresent(By by, String message, String... noDisplayPassVerified) {

		try {
			driver.findElement(by);
			if (noDisplayPassVerified.length == 0) { // don't know what this does??
				log.debug("Verified is present: " + message);
				testQA.log(LogStatus.PASS, "Verified is present: " + message);
				testLocal.log(LogStatus.PASS, "Verified is present: " + message);

			}
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {

			TestUtil.captureScreenshot(rptName);

			log.debug("FAIL=> is not present: " + message);
			// Extent Reports
			testQA.log(LogStatus.FAIL, "FAIL=> is not present: " + message);
			testQA.log(LogStatus.FAIL, testQA.addScreenCapture(TestUtil.screenshotPathQA+TestUtil.screenshotName));
			
			testLocal.log(LogStatus.FAIL, "FAIL=> is not present: " + message);
			testLocal.log(LogStatus.FAIL, testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
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
			log.info("Entered => " + textBoxName + " = " + value);
			testQA.log(LogStatus.INFO, "Entered => " + textBoxName + " = " + value);
			testLocal.log(LogStatus.INFO, "Entered => " + textBoxName + " = " + value);

		}
	}

	public void setDropDownFailPass(By by, String value, String dropDownName, String failMessage) {
		WebElement dropdown = webElement(by);
		Select select = new Select(dropdown);

		try {
			select.selectByVisibleText(value);
			log.debug("Selected from Dropdown => " + dropDownName + " = " + value);
			testQA.log(LogStatus.INFO, "Selected from Dropdown => " + dropDownName + " = " + value);
			testLocal.log(LogStatus.INFO, "Selected from Dropdown => " + dropDownName + " = " + value);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			TestUtil.captureScreenshot(rptName);
			// Extent Reports
			log.debug("FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			testQA.log(LogStatus.FAIL, "FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			testQA.log(LogStatus.FAIL, testQA.addScreenCapture(TestUtil.screenshotPathQA+TestUtil.screenshotName));
			testLocal.log(LogStatus.FAIL, "FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			testLocal.log(LogStatus.FAIL, testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
			Assert.fail(failMessage);
		}

	}

	public void setDropDown(By by, String value, String dropDownName) {
		WebElement dropdown;
		dropdown = webElement(by);

		Select select = new Select(dropdown);
		try {
			select.selectByVisibleText(value);
			log.info("Selected from Dropdown => " + dropDownName + " = " + value);
			testQA.log(LogStatus.INFO, "Selected from Dropdown => " + dropDownName + " = " + value);
			testLocal.log(LogStatus.INFO, "Selected from Dropdown => " + dropDownName + " = " + value);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			TestUtil.captureScreenshot(rptName);
			log.debug("FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			// Extent Reports
			testQA.log(LogStatus.FAIL, "FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			testQA.log(LogStatus.FAIL, testQA.addScreenCapture(TestUtil.screenshotPathQA+TestUtil.screenshotName));
			
			testLocal.log(LogStatus.FAIL, "FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			testLocal.log(LogStatus.FAIL, testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));

		}

	}

	public void clickButton(By by, String buttonName) {
		if (isElementPresent(by, buttonName, "noDisplayPassVerified")) {
			webElement(by).click();
			log.info("Clicked Button => " + buttonName);
			testQA.log(LogStatus.INFO, "Clicked Button => " + buttonName);
			testLocal.log(LogStatus.INFO, "Clicked Button => " + buttonName);
		}
	}

	public void waitForPageLoad(By by) {
		log.debug("Waiting for visibility of => " + by.toString());
		wait.until(ExpectedConditions.elementToBeClickable(by));
		log.debug("Is now visibile => " + by.toString());
	}

	public static boolean isNotElementVisible(By by, String message, String... noDisplayPassVerified) {

		try {
			if (driver.findElement(by).isDisplayed()) {
				TestUtil.captureScreenshot(rptName);

				log.debug("FAIL=> is visible : " + message);
				// Extent Reports
				testQA.log(LogStatus.FAIL, "FAIL=> is visible : " + message);
				testQA.log(LogStatus.FAIL, testQA.addScreenCapture(TestUtil.screenshotPathQA+TestUtil.screenshotName));
				
				testLocal.log(LogStatus.FAIL, "FAIL=> is visible : " + message);
				testLocal.log(LogStatus.FAIL, testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));

				return false;
			} else {
				if (noDisplayPassVerified.length == 0) {
					log.debug("Verified is NOT visible: " + message);
					testQA.log(LogStatus.PASS, "Verified is NOT visible: " + message);
					testLocal.log(LogStatus.PASS, "Verified is NOT visible: " + message);

				}
				return true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			if (noDisplayPassVerified.length == 0) {
				log.debug("Verified is NOT visible: " + message);
				testQA.log(LogStatus.PASS, "Verified is NOT visible: " + message);
				testLocal.log(LogStatus.PASS, "Verified is NOT visible: " + message);
			}
			return true;
		}
	}

	public static boolean isTextVisible(By by, String text) {
		if (isElementVisible(by, text, "noDisplayPassVerified")) {
			return verifyEquals(text, getWebElementText(by));
		} else
			return false;
	}

	public static boolean isNotTextVisible(By by, String text, String... noDisplayPassVerified) {
		try {
			if (driver.findElement(by).isDisplayed()) {
				if (getWebElementText(by).equalsIgnoreCase(text)) {
					TestUtil.captureScreenshot(rptName);
					log.debug("FAIL=> is visible : " + text);
					// Extent Reports
					testQA.log(LogStatus.FAIL, "FAIL=> is visible : " + text);
					testQA.log(LogStatus.FAIL, testQA.addScreenCapture(TestUtil.screenshotPathQA+TestUtil.screenshotName));
					
					testLocal.log(LogStatus.FAIL, "FAIL=> is visible : " + text);
					testLocal.log(LogStatus.FAIL, testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
					return false;
				} else {
					if (noDisplayPassVerified.length == 0) {
						log.debug("Verified is NOT visible: " + text);
						testQA.log(LogStatus.PASS, "Verified is NOT visible: " + text);
						testLocal.log(LogStatus.PASS, "Verified is NOT visible: " + text);
					}
					return true;
				}
			} else {
				if (noDisplayPassVerified.length == 0) {
					log.debug("Verified is NOT visible: " + text);
					testQA.log(LogStatus.PASS, "Verified is NOT visible: " + text);
					testLocal.log(LogStatus.PASS, "Verified is NOT visible: " + text);
				}
				return true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			if (noDisplayPassVerified.length == 0) {
				log.debug("Verified is NOT visible: " + text);
				testQA.log(LogStatus.PASS, "Verified is NOT visible: " + text);
				testLocal.log(LogStatus.PASS, "Verified is NOT visible: " + text);
			}
			return true;
		}
	}

	public static String getWebElementText(By by) {
		return webElement(by).getText();
	}
	
	public void clickButtonWhenClickable(By by, String buttonName) {
		log.info("Waiting to be clickable => " + buttonName);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		log.info("Is now clickable => " + buttonName);
		webElement(by).click();
		log.info("Clicked Button => " + buttonName);
		 testQA.log(LogStatus.INFO, "Clicked Button => " + buttonName);
		 testLocal.log(LogStatus.INFO, "Clicked Button => " + buttonName);
	}

	public static Boolean verifyEquals(String expected, String actual) {
		if (!actual.equals(expected)) {
			TestUtil.captureScreenshot(rptName);

			log.debug("Verification failure : Expected= '" + expected + "' Actual= '" + actual);
			testQA.log(LogStatus.WARNING, "Verification failure :<br>Expected=" + expected
					+ "<br>Actual=&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + actual);
			testQA.log(LogStatus.WARNING, testQA.addScreenCapture(TestUtil.screenshotPathQA+TestUtil.screenshotName));
			testLocal.log(LogStatus.WARNING, "Verification failure :<br>Expected=" + expected
					+ "<br>Actual=&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + actual);
			testLocal.log(LogStatus.WARNING, testLocal.addScreenCapture(TestUtil.screenshotPathLocal+TestUtil.screenshotName));
			// Assert.fail(" Assertion failure => Expected=" + expected + " but found=" +
			// actual);
			return false;
		} else {
			log.debug("Verified Expected and Actual are the same => " + actual);
			testQA.log(LogStatus.PASS, "Verified Expected and Actual are the same => " + actual);
			testLocal.log(LogStatus.PASS, "Verified Expected and Actual are the same => " + actual);
			// test.log(LogStatus.PASS, test.addScreenCapture(TestUtil.screenshotName));
			return true;
		}

	}

}
