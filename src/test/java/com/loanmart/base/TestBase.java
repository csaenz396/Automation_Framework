package com.loanmart.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
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
import com.loanmart.utilities.ExtentManager;
import com.loanmart.utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
@Listeners(com.loanmart.listeners.CustomListeners.class)
public class TestBase {

	public static WebDriver driver;
	public static ChromeOptions chromeOptions = new ChromeOptions();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Properties config = new Properties();
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static String devEnvironment = System.getProperty("DEV_ENV");
	public static Actions act;


	@BeforeSuite(alwaysRun = true)
	public void setUpSuite() {
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
			js = (JavascriptExecutor) driver;
			act = new Actions(driver);
		}

	}
	
	@AfterSuite
	public void tearDownSuite() {
		//if (driver != null)
			//driver.quit();
		log.debug("Class Test Execution completed");
	}

}
