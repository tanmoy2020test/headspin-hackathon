/**
 * 
 */
package com.mmt.test.ui.config.browser;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.mmt.test.ui.config.driver.AbstractWebDriver;
import com.mmt.test.ui.config.system.GetSystemProperty;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.stqa.selenium.factory.WebDriverPool;

/**
 * @author Tanmoy May 30, 2020
 */
public class FirefoxDriverDefination extends AbstractWebDriver {

	private static final Logger logger = LogManager.getLogger(FirefoxDriverDefination.class);
	private FirefoxOptions firefoxOptions = new FirefoxOptions();

	public FirefoxDriverDefination(String headLessExecution) {
		if (headLessExecution != null && headLessExecution.equalsIgnoreCase("YES")) {
			firefoxOptions.setHeadless(true);
			firefoxOptions.addArguments("--disable-gpu");
		}
		firefoxOptions.addArguments("--window-size=1920,1280");
	}

	@Override
	public void setWebDriver() {
		try {
			if (null == GetSystemProperty.getRemoteURL()) {
				WebDriverManager.chromedriver().setup();
				_webDriver = WebDriverPool.DEFAULT.getDriver(null, firefoxOptions);
			} else {
				_webDriver = WebDriverPool.DEFAULT.getDriver(new URL(GetSystemProperty.getRemoteURL().toString()),
						firefoxOptions);
			}
		} catch (MalformedURLException e) {
			logger.error(e);
		}
	}

	@Override
	public WebDriver getWebDriver() {
		if (isNull() == true) {
			return _webDriver;
		}
		return null;
	}
}