/**
 * 
 */
package com.mmt.test.ui.bookingflow;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mmt.test.ui.MMTBaseUITest;
import com.mmt.test.ui.config.csvreader.CSVFileHandler;
import com.mmt.test.ui.config.propertyloader.TestProperties;
import com.mmt.test.ui.config.screenshot.BrowserScreenShot;
import com.mmt.test.ui.config.util.UITestProperty;
import com.mmt.test.ui.config.webdriver.DriverInitalization;
import com.mmt.test.ui.pages.homepage.MMTHomePage;
import com.mmt.test.ui.pages.hotelsdetailspage.HotelDetailsPage;
import com.mmt.test.ui.pages.hotelslistpage.HotelsListPage;
import com.mmt.test.ui.pages.loginpage.MMTLoginPage;

/**
 * @author Tanmoy May 30, 2020
 */
public class MMTHotelBookingTest extends MMTBaseUITest {
	private static final Logger logger = LogManager.getLogger(MMTHotelBookingTest.class);

	private MMTLoginPage mmtLoginPage;
	private MMTHomePage mmtHomePage;
	HotelsListPage hotelsListPage;
	HotelDetailsPage hotelDetailsPage;
	private final String csvFileName = "mmt-hotel-booking-data.csv";
	Map<String, String> hotelBookingData = new HashMap<String, String>();
	private String rootDir = System.getProperty("user.dir");
	private String bankCreationPath = rootDir
			.concat(TestProperties.getPropertyValue(UITestProperty.TESTDATAPATH.label) + csvFileName);

	@BeforeClass
	public void Initialization() {
		driver = DriverInitalization.getWebDriver();
		mmtLoginPage = new MMTLoginPage(driver);
		mmtHomePage = new MMTHomePage(driver);
		hotelsListPage  = new HotelsListPage(driver);
		hotelDetailsPage = new HotelDetailsPage(driver);
		hotelBookingData = CSVFileHandler.readCSVInputFile(bankCreationPath);
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().get(getLoginUrl());
		mmtLoginPage.performLogin(getUserName(), getPassword());
	}

	@Test(description = "Book Hotel and Verify booking details in payment page")
	public void bookHotel() {
		logger.info("Hello Booking started");
		mmtHomePage.clickOnHotel();
		mmtHomePage.searchHotel(hotelBookingData);
		hotelsListPage.selectHotel();
		
	}

	@AfterMethod
	public void resultAnalysys(ITestResult result) throws AWTException {
		if (result.getStatus() == ITestResult.FAILURE) {
			Reporter.setCurrentTestResult(result);
			Reporter.log(BrowserScreenShot.takeScreenShot(result.getMethod().getMethodName(), driver));
		}
	}

}
