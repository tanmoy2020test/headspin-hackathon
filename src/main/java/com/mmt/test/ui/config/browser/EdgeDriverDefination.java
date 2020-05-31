/**
 * 
 */
package com.mmt.test.ui.config.browser;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

import com.mmt.test.ui.config.driver.AbstractWebDriver;
import com.mmt.test.ui.config.system.GetSystemProperty;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.stqa.selenium.factory.WebDriverPool;

/**
 * @author Tanmoy May 30, 2020
 */
public class EdgeDriverDefination extends AbstractWebDriver {

	private static final Logger logger = LogManager.getLogger(EdgeDriverDefination.class);
	private ChromeOptions chromeOptions = new ChromeOptions();
	private EdgeOptions edgeOptions;

	public EdgeDriverDefination(String headLessExecution) {
		if (headLessExecution != null && headLessExecution.equalsIgnoreCase("YES")) {
			chromeOptions.setHeadless(true);
			chromeOptions.addArguments("--disable-gpu");
		}
		chromeOptions.addArguments("--window-size=1920,1280");
		edgeOptions = new EdgeOptions().merge(chromeOptions);
	}

	@Override
	public void setWebDriver() {
		try {
			if (null == GetSystemProperty.getRemoteURL()) {
				WebDriverManager.chromedriver().setup();
				_webDriver = WebDriverPool.DEFAULT.getDriver(null, edgeOptions);
			} else {
				_webDriver = WebDriverPool.DEFAULT.getDriver(new URL(GetSystemProperty.getRemoteURL().toString()),
						edgeOptions);
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