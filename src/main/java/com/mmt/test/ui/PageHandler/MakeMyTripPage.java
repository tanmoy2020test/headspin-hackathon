/**
 * 
 */
package com.mmt.test.ui.PageHandler;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Tanmoy May 31, 2020
 */
public class MakeMyTripPage {

	protected static final long explicitWaitTime = 60;

	protected WebDriver driver;
	protected Actions actions;
	protected WebDriverWait wait;

	public MakeMyTripPage(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
	}

	public void waitForElementToBeVisibleInUI(WebElement element) {
		wait = new WebDriverWait(driver, explicitWaitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickableInUI(WebElement element) {
		wait = new WebDriverWait(driver, explicitWaitTime);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementsToBeVisibleInUI(List<WebElement> elements) {
		wait = new WebDriverWait(driver, explicitWaitTime);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public void clickOnElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
}