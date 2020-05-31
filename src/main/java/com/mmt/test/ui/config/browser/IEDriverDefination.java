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
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.mmt.test.ui.config.driver.AbstractWebDriver;
import com.mmt.test.ui.config.system.GetSystemProperty;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.stqa.selenium.factory.WebDriverPool;

/**
 * @author Tanmoy May 30, 2020
 */
public class IEDriverDefination extends AbstractWebDriver {

	private static final Logger logger = LogManager.getLogger(IEDriverDefination.class);
	private ChromeOptions chromeOptions = new ChromeOptions();
	private InternetExplorerOptions internetExplorerOptions;

	public IEDriverDefination(String headLessExecution) {
		if (headLessExecution != null && headLessExecution.equalsIgnoreCase("YES")) {
			chromeOptions.setHeadless(true);
			chromeOptions.addArguments("--disable-gpu");
		}
		chromeOptions.addArguments("--window-size=1920,1280");
		internetExplorerOptions = new InternetExplorerOptions().merge(chromeOptions);
	}

	@Override
	public void setWebDriver() {
		try {
			if (null == GetSystemProperty.getRemoteURL()) {
				WebDriverManager.chromedriver().setup();
				_webDriver = WebDriverPool.DEFAULT.getDriver(null, internetExplorerOptions);
			} else {
				_webDriver = WebDriverPool.DEFAULT.getDriver(new URL(GetSystemProperty.getRemoteURL().toString()),
						internetExplorerOptions);
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
