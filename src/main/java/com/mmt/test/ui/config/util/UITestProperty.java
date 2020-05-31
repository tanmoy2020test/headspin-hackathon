/**
 * 
 */
package com.mmt.test.ui.config.util;

/**
 * @author Tanmoy May 31, 2020
 */
public enum UITestProperty {

	LOGINURL("loginurl"), LOGINEMAIL("usernamme"), LOGINPASSWORD("password"), TESTDATAPATH("testdata.path");

	public final String label;

	private UITestProperty(String label) {
		this.label = label;
	}
}