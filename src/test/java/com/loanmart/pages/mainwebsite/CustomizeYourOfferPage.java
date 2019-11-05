package com.loanmart.pages.mainwebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.loanmart.action.Action;
import com.loanmart.base.TestBase;

public class CustomizeYourOfferPage extends TestBase {

	WebDriver driver;
	Action action;

	public CustomizeYourOfferPage(WebDriver driver, String testName) {
		this.driver = driver;
		action = new Action(testName);
	}
	
	public void waitForCustomizeYourOfferPageLoad() {
		action.waitForPageLoad(bySubmitButton);
	}
	
	By bySubmitButton = By.id("lm-submit-app");

	///////////// AEL Elements ///////////////////
	
	public void clickSubmitAndGetMoneyButton() {
		
		By byLoaading = By.xpath("//*[contains(text(),'Loading')]");
		log.debug("Waiting for invisibility of => Loading...");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byLoaading));
		log.debug("Is now invisibile => Loading...");

		action.clickButtonWhenClickable(bySubmitButton, "Submit And Get Money");
	}
			
	public By byAelText1 = By.xpath("//*[@id=\"loan_header\"]/h2");
	public String aelText1="Call your personal loan Officer to get your cash!";
	
	public By byAelText2 = By.xpath("//*[@id=\"lm-app-slider\"]/div/h3/span[1]");
	public String aelText2="Customize Your Offer";
	
	public By byAelText3 = By.xpath("//*[@id=\"lm-app-slider\"]/div/p/span[1]");
	public String aelText3="Please utilize the sliders below to customize your LoanMart Auto Title Loan. Remember, this is based on the equity in your car.";
	
	public By byAelText4 = By.xpath("//*[@id=\"pricetext\"]/p/span[1]");
	public String aelText4="Estimated Prices**";
	
	public By byAelText5 = By.xpath("//*[@id=\"peace-of-mind-box\"]/div/span");
	public String aelText5="PEACE OF MIND GUARANTEE";
	
	public By byAelText6 = By.xpath("//*[@id=\"peace-of-mind-box\"]/div/div/p");
	public String aelText6="We at LoanMart want you to be happy with your loan! Feel free to try our products for 3 days with absolutely no charges or obligation. Click here for details."; 
	
	public By byAelPhoneText = By.xpath("//*[@id=\"peace-of-mind-box\"]/div/div/div[1]/span[1]");
	public String aelPhoneText="1-855-422-7412";
	
	public By byPeaceOfMindBox = By.id("peace-of-mind-box");
	
	public By byFasterPayoffButton = By.id("lm-fast-payment");
	public String fasterPayoffButtonText ="Faster Payoff";
	
	public By byLowestPaymentButton = By.id("lm-low-payment");
	public String lowestPaymentButtonText ="Lowest Payment";

	public By byLocalParticipatingStoreButton = By.xpath("//*[@id=\"peace-of-mind-box\"]/div/div/div[2]");
	public String localParticipatingStoreButtonText ="FIND A LOCAL PARTICIPATING STORE";

	public By byNeedMoreThanButton = By.xpath("//*[@id=\"lmSlider\"]/div/div[4]/button[1]");
	public String needMoreThanButtonText ="need more than "+"?";

	///////////// CCB Elements ///////////////////

	public By byCcbText1 = By.xpath("//*[@id='lm-app-slider']/div/h3/span[2]");
	public String ccbText1="Select Your Loan Amount";
	
	public By byCcbText2 = By.xpath("//*[@id=\"lm-app-slider\"]/div/p/span[2]");
	public String ccbText2="Please use the slider below to customize your loan offer.";
	
	public By byCcbText3 = By.xpath("//*[@id=\"termspan\"]");
	public String ccbText3="36";
	
	public By byCcbText4 = By.xpath("//*[@id=\"pricetext\"]/p/span[2]");
	public String ccbText4="Estimated monthly payment**";
		
	public void clickSubmitButton() {
		action.clickButton(bySubmitButton, "Submit");
	}
}
