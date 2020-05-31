/**
 * 
 */
package com.mmt.test.ui.config.screenshot;

import java.io.File;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tanmoy May 31, 2020
 */
public class ScreenshotUtils {

	private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);
	private static SecureRandom random = new SecureRandom();
	private static String reportName = null;

	/**
	 * @author Tanmoy
	 * @description create screenshot folder based on folder name and folder path
	 * @param folderPath
	 * @param folderName
	 * @return string folder path after creating a folder if doesn't exist
	 */
	public static String createFolder(File FolderPath, String folderName) {
		boolean result = false;
		String sFilePath = "";
		try {
			if (!FolderPath.exists()) {
				FolderPath.mkdir();
			}
			if (FolderPath.isDirectory()) {
				File directory = new File(FolderPath.getAbsolutePath() + getOS() + folderName);
				if (directory.isDirectory()) {
					logger.info("Screenshot Folder already exists");
					sFilePath = directory.getAbsolutePath();
				} else {
					result = directory.mkdirs();
					logger.info("Screenshot Folder Path : " + result);
					Thread.sleep(1000L);
					if (result) {
						sFilePath = directory.getAbsolutePath();
					}
				}
			} else {
				logger.info("Directory Path is not found while creating a folder");
				logger.info(FolderPath.toString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sFilePath;
	}

	/**
	 * @author Tanmoy
	 * @description Function to return random String
	 * @param length
	 * @return random string with length equals to the parameter passed
	 */
	public static String getRandomString(int length) {
		String result = "";
		try {
			result = new BigInteger(Long.SIZE * length, random).toString(32);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result.substring(0, length);
	}

	/**
	 * @author Tanmoy
	 * @description function to set the execution report's name
	 */
	public static void setReportName() {
		try {
			if (reportName == null) {
				Calendar cal = Calendar.getInstance();
				reportName = "Screenshot-" + cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
						+ cal.get(Calendar.YEAR) + "_" + cal.get(Calendar.HOUR_OF_DAY) + "-" + cal.get(Calendar.MINUTE)
						+ "-" + cal.get(Calendar.SECOND) + "-" + cal.get(Calendar.MILLISECOND) + "_"
						+ ScreenshotUtils.getRandomString(5).toUpperCase();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * @author Tanmoy
	 * @description function to get the execution report's name
	 * @return String name of execution report
	 */
	public static String getReportName() {
		setReportName();
		return reportName;
	}

	/**
	 * @author Tanmoy
	 * @description function to find out the system os
	 * @return string ancestor
	 */
	public static String getOS() {
		String os = System.getProperty("os.name");
		if (os.equalsIgnoreCase("windows")) {
			return "\\";
		} else {
			return "/";
		}
	}
}