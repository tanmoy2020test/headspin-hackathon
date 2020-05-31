/**
 * 
 */
package com.mmt.test.ui.config.screenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * @author Tanmoy May 31, 2020
 */
public class BrowserScreenShot {

	private static final Logger logger = LogManager.getLogger(BrowserScreenShot.class);
	private static String timeStamp = null;
	private static String screenshotFolderName = new String();

	/**
	 * To generate the screen shot folder
	 */
	static {
		screenshotFolderName = ScreenshotUtils.createFolder(new File("ScreenShots"), ScreenshotUtils.getReportName());
	}

	/**
	 * @author Tanmoy
	 * @description capture screenshot with execution method name and webdriver
	 * @param methodName
	 * @param webDriver
	 * @return screenshot path after store
	 */
	public static String takeScreenShot(String methodName, WebDriver webDriver) {

		File soruceFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			timeStamp = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
			String location = screenshotFolderName + "\\" + methodName + "_" + timeStamp + ".png";
			FileUtils.copyFile(soruceFile, new File(location));
			String path = "<img src=\"file://" + location + "\" alt=\"\"/>";
			return path;
		} catch (IOException e) {
			logger.error(e);
		}
		return methodName + "_exception while capturing screenshot";
	}
}