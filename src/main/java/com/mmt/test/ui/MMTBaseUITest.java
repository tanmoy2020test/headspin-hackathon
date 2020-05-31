/**
 * 
 */
package com.mmt.test.ui;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;

import com.mmt.test.ui.config.system.GetSystemProperty;

import ru.stqa.selenium.factory.WebDriverPool;

/**
 * @author Tanmoy May 30, 2020
 */
public class MMTBaseUITest {

	protected WebDriver driver;
	private String loginUrl;
	private String userName;
	private String password;

	/**
	 * @author Tanmoy
	 * @description
	 */
	public MMTBaseUITest() {
		this.loginUrl = GetSystemProperty.getLoginURL().toString();
		this.userName = GetSystemProperty.getUserName().toString();
		this.password = GetSystemProperty.getPassword().toString();
	}

	/**
	 * @author Tanmoy
	 * @description this function will return the webdriver
	 * @return webdriver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * @author Tanmoy
	 * @description
	 * @return
	 */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * @author Tanmoy
	 * @description
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @author Tanmoy
	 * @description
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @author Tanmoy
	 * @description this function will close browser and quit active session
	 */
	@AfterClass
	public void quitDriver() {
		WebDriverPool.DEFAULT.dismissAll();
	}

}
