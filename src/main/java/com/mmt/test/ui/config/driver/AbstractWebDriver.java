/**
 * 
 */
package com.mmt.test.ui.config.driver;

import org.openqa.selenium.WebDriver;

/**
 * @author Tanmoy
 * May 30, 2020
 */
public abstract class AbstractWebDriver {
	protected WebDriver _webDriver;

	protected AbstractWebDriver(){
		//Do nothing
	}

	abstract protected void setWebDriver();

	abstract protected WebDriver getWebDriver();

	public boolean isNull(){
		if(_webDriver!=null){
			return true;
		}
		return false;
	}
}
