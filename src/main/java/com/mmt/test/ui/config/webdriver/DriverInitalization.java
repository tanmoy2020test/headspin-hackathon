/**
 * 
 */
package com.mmt.test.ui.config.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;

import com.mmt.test.ui.config.browser.ChromeDriverDefination;
import com.mmt.test.ui.config.browser.EdgeDriverDefination;
import com.mmt.test.ui.config.browser.FirefoxDriverDefination;
import com.mmt.test.ui.config.browser.IEDriverDefination;
import com.mmt.test.ui.config.system.GetSystemProperty;

/**
 * @author Tanmoy May 30, 2020
 */
public class DriverInitalization {

	/**
	 * @author Tanmoy
	 * @description this function will initialize the webdriver based on the
	 * @return webdriver
	 */
	public static WebDriver getWebDriver() {
		if (BrowserType.CHROME.toString().equalsIgnoreCase(GetSystemProperty.getBrowserName().toString())) {
			ChromeDriverDefination chromeDriverDefination = new ChromeDriverDefination(
					GetSystemProperty.getHeadLessExecution().toString());
			chromeDriverDefination.setWebDriver();
			return chromeDriverDefination.getWebDriver();
		} else if (BrowserType.FIREFOX.toString().equalsIgnoreCase(GetSystemProperty.getBrowserName().toString())) {
			FirefoxDriverDefination firefoxDriverDefination = new FirefoxDriverDefination(
					GetSystemProperty.getHeadLessExecution().toString());
			firefoxDriverDefination.setWebDriver();
			return firefoxDriverDefination.getWebDriver();
		} else if (BrowserType.IE.toString().replace(" ", "")
				.equalsIgnoreCase(GetSystemProperty.getBrowserName().toString())) {
			IEDriverDefination ieDriverDefination = new IEDriverDefination(
					GetSystemProperty.getBrowserName().toString());
			ieDriverDefination.setWebDriver();
			return ieDriverDefination.getWebDriver();
		} else if (BrowserType.EDGE.toString().equalsIgnoreCase(GetSystemProperty.getBrowserName().toString())) {
			EdgeDriverDefination edgeDriverDefination = new EdgeDriverDefination(
					GetSystemProperty.getBrowserName().toString());
			edgeDriverDefination.setWebDriver();
			return edgeDriverDefination.getWebDriver();
		} else {
			ChromeDriverDefination chromeDriverDef = new ChromeDriverDefination(
					GetSystemProperty.getBrowserName().toString());
			chromeDriverDef.setWebDriver();
			return chromeDriverDef.getWebDriver();
		}
	}
}