/**
 * 
 */
package com.mmt.test.ui.config.propertyloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Tanmoy May 30, 2020
 */
public class TestProperties {

	private static final Logger logger = LogManager.getLogger(TestProperties.class);
	private final static Properties prop = new Properties();

	static {
		loadProperties();
	}

	private static void loadProperties() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				TestProperties.class.getClassLoader().getResourceAsStream("ui-test.properties")))) {
			prop.load(br);
		} catch (IOException ex) {
			logger.error(ex);
		}
	}

	public static String getPropertyValue(String key) {
		return prop.getProperty(key);
	}
}
