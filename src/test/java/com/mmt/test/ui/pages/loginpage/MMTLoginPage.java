/**
 * 
 */
package com.mmt.test.ui.pages.loginpage;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mmt.test.ui.PageHandler.MakeMyTripPage;

/**
 * @author Tanmoy May 31, 2020
 */
public class MMTLoginPage extends MakeMyTripPage {

	private static final Logger logger = LogManager.getLogger(MMTLoginPage.class);

	/**
	 * @author Tanmoy
	 * @description
	 * @param driver
	 */
	public MMTLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//li[@data-cy='account']")
	private WebElement _loginOrCreateAccountButton;

	@FindBy(xpath = "// p[@data-cy='commonModal']/span[contains(text(),'Login')]")
	private WebElement _loginScreenMainText;

	@FindBy(id = "username")
	private WebElement _emailOrMobileNumberTextBox;

	@FindBy(css = "button[data-cy='continueBtn']>span")
	private WebElement _continueButton;

	@FindBy(id = "password")
	private WebElement _passwordTextBox;

	@FindBy(xpath = "//button[@data-cy='login']")
	private WebElement _loginButton;

	@FindBy(xpath = "//p[contains(text(),'Verify Mobile Number')]")
	private WebElement _verifyMobileNumberScreen;

	@FindBy(xpath = "//p[contains(text(),'Verify Your E-mail ID')]")
	private WebElement _verifyEmailScreen;

	@FindBy(xpath = "//span[@data-cy='modalClose']")
	private WebElement _verifyMobileScreenClose;

	@FindBy(xpath = "//li[@data-cy='account']//div/p[contains(text(),'You are viewing your personal profile')]")
	private WebElement _loginConfirationText;

	public void clickContinueButton() {
		try {
			while (_continueButton.isDisplayed()) {
				_continueButton.click();
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void handleVerifyMobileNumberScreen() {
		try {
			if (_verifyMobileNumberScreen.isDisplayed()) {
				_verifyMobileScreenClose.click();
			}
		} catch (NoSuchElementException e) {
			logger.info(e.toString());
		}
	}

	public void performLogin(String email, String password) {
		try {
			waitForElementToBeClickableInUI(_loginOrCreateAccountButton);
			_loginOrCreateAccountButton.click();
			waitForElementToBeClickableInUI(_emailOrMobileNumberTextBox);
			_emailOrMobileNumberTextBox.sendKeys(email);
			waitForElementToBeClickableInUI(_continueButton);
			clickContinueButton();
			waitForElementToBeClickableInUI(_passwordTextBox);
			_passwordTextBox.sendKeys(password);
			waitForElementToBeClickableInUI(_loginButton);
			_loginButton.click();
			handleVerifyMobileNumberScreen();
			_loginOrCreateAccountButton.click();
			Assert.assertEquals(_loginConfirationText.getText().replace("[\r\n]+", ""),
					"You are viewing your personal profile " + email);
			logger.info("Successfully Loginto MakeMyTrip application");
		} catch (NoSuchElementException e) {
			logger.info(e.toString());
		}
	}
}