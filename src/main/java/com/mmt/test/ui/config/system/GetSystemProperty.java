/**
 * 
 */
package com.mmt.test.ui.config.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mmt.test.ui.config.propertyloader.TestProperties;
import com.mmt.test.ui.config.util.UITestProperty;

/**
 * @author Tanmoy May 30, 2020
 */
public class GetSystemProperty {

	private static final Logger logger = LogManager.getLogger(GetSystemProperty.class);

	GetSystemProperty() {
		// Do nothing
	}

	public static StringBuffer getBrowserName() {
		String browser = System.getProperty(SystemProperties.BROWSER_NAME.toString());
		if (browser == null) {
			return new StringBuffer("chrome");
		}
		logger.info("Browser name is set to " + browser);
		return new StringBuffer(browser);
	}

	public static StringBuffer getRemoteURL() {
		String remoteUrl = System.getProperty(SystemProperties.REMOTE_URL.toString());
		if (remoteUrl == null || remoteUrl.isEmpty()) {
			return null;
		}
		logger.info("URL name is set to " + remoteUrl);
		return new StringBuffer(remoteUrl);
	}

	public static StringBuffer getLoginURL() {
		String loginUrl = System.getProperty(SystemProperties.LOGIN_URL.toString());
		if (loginUrl == null || loginUrl.isEmpty()) {
			return new StringBuffer(TestProperties.getPropertyValue(UITestProperty.LOGINURL.label));
		}
		logger.info("URL name is set to " + loginUrl);
		return new StringBuffer(loginUrl);
	}

	public static StringBuffer getUserName() {
		String userName = System.getProperty(SystemProperties.USER_NAME.toString());
		if (userName == null) {
			return new StringBuffer(TestProperties.getPropertyValue(UITestProperty.LOGINEMAIL.label));
		}
		logger.info("Username is set to " + userName);
		return new StringBuffer(userName);
	}

	public static StringBuffer getPassword() {
		String password = System.getProperty(SystemProperties.PASSWORD.toString());
		if (password == null) {
			return new StringBuffer(TestProperties.getPropertyValue(UITestProperty.LOGINPASSWORD.label));
		}
		logger.info("Password is set to " + password);
		return new StringBuffer(password);
	}

	public static StringBuffer getHeadLessExecution() {
		String headLessExecution = System.getProperty(SystemProperties.HEADLESS_EXECUTION.toString());
		if (headLessExecution == null) {
			return new StringBuffer("NO");
		}
		logger.info("Headless Execution status is set to " + headLessExecution);
		return new StringBuffer(headLessExecution);
	}
}
