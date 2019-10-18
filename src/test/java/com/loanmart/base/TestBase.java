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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.loanmart.utilities.ExcelReader;
import com.loanmart.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	
	public static WebDriver driver;
	private ChromeOptions chromeOptions = new ChromeOptions();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static Properties config = new Properties();
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader();
	
	@BeforeSuite
	public void setUpSuite() {
		System.out.println("************************INSIDE BEFORE CLASS******************************");
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
				chromeOptions.addArguments("disable-infobars");
				chromeOptions.addArguments("--incognito");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("--no-sandbox");
				chromeOptions.addArguments("--allow-insecure-localhost");
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

		if (isElementPresent(by, message,"noDisplayPassVerified")) {
			if (driver.findElement(by).isDisplayed()) {
				if (noDisplayPassVerified.length == 0) {
					log.debug("Verified is visible: " + message);
				}
				return true;
			} else {
				TestUtil.captureScreenshot();
				log.debug("FAIL=> is not visible : " + message);
				// Extent Reports
				return false;
			}
		} else
			return false;
	}
	
	public static boolean isElementPresent(By by, String message, String... noDisplayPassVerified) {

		try {
			driver.findElement(by);
			if (noDisplayPassVerified.length == 0) { //don't know what this does??
				log.debug("Verified is present: " + message);

			}
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {

			TestUtil.captureScreenshot();

			log.debug("FAIL=> is not present: " + message);
			// Extent Reports
			return false;
		}
	}
	
	public void setTextBox(By by, String value,String textBoxName) {
		if (isElementPresent(by, textBoxName, "noDisplayPassVerified")) {
			if(textBoxName.equals("MobileNumber") || textBoxName.equals("HomeNumber") || textBoxName.equals("SSN")){//this is new code, added to the phone number fields.
				webElement(by).click();
				webElement(by).sendKeys(value);
			}else {
				webElement(by).sendKeys(value);
			}
			log.info("Entered => " + textBoxName + " = " + value);

		}
	}
	
	public void setDropDownFailPass(By by, String value, String dropDownName, String failMessage) {
		WebElement dropdown = webElement(by);
		Select select = new Select(dropdown);

		try {
			select.selectByVisibleText(value);
			log.debug("Selected from Dropdown => " + dropDownName + " = " + value);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			TestUtil.captureScreenshot();
			// Extent Reports
			log.debug("FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			Assert.fail(failMessage);
		}

	}
	
	public void setDropDown(By by, String value, String dropDownName ) {
		WebElement dropdown;
		dropdown = webElement(by);

		Select select = new Select(dropdown);
		try {
			select.selectByVisibleText(value);
			log.info("Selected from Dropdown => " + dropDownName + " = " + value);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			TestUtil.captureScreenshot();
			log.debug("FAIL=> Cannot locate : " + value + " in Dropdown : " + dropDownName);
			// Extent Reports
			
		}

	}
	
	
	public void clickButton(By by, String buttonName) {
		if (isElementPresent(by, buttonName, "noDisplayPassVerified")) {
			webElement(by).click();
			log.info("Clicked Button => " + buttonName);
		}
	}
	
	public static boolean isNotElementVisible(By by, String message, String... noDisplayPassVerified) {

		try {
			if (driver.findElement(by).isDisplayed()) {
				TestUtil.captureScreenshot();

				log.debug("FAIL=> is visible : " + message);
				// Extent Reports

				return false;
			} else {
				if (noDisplayPassVerified.length == 0) {
					log.debug("Verified is NOT visible: " + message);

				}
				return true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			if (noDisplayPassVerified.length == 0) {
				log.debug("Verified is NOT visible: " + message);
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
					TestUtil.captureScreenshot();
					log.debug("FAIL=> is visible : " + text);
					// Extent Reports
					return false;
				} else {
					if (noDisplayPassVerified.length == 0) {
						log.debug("Verified is NOT visible: " + text);
					}
					return true;
				}
			} else {
				if (noDisplayPassVerified.length == 0) {
					log.debug("Verified is NOT visible: " + text);
				}
				return true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			if (noDisplayPassVerified.length == 0) {
				log.debug("Verified is NOT visible: " + text);
			}
			return true;
		}
	}
	
	public static String getWebElementText(By by) {
		return webElement(by).getText();
	}
	
	public static Boolean verifyEquals(String expected, String actual ) {
		if (!actual.equals(expected)) {
			TestUtil.captureScreenshot();

			log.debug("Verification failure : Expected= '" + expected + "' Actual= '" + actual);
			// Assert.fail(" Assertion failure => Expected=" + expected + " but found=" +
			// actual);
			return false;
		} else {
			log.debug("Verified Expected and Actual are the same => " + actual);
			// test.log(LogStatus.PASS, test.addScreenCapture(TestUtil.screenshotName));
			return true;
		}

	}

}
