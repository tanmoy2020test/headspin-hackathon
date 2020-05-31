/**
 * 
 */
package com.mmt.test.ui.pages.homepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.mmt.test.ui.PageHandler.MakeMyTripPage;

/**
 * @author Tanmoy May 31, 2020
 */
public class MMTHomePage extends MakeMyTripPage {

	/**
	 * @author Tanmoy
	 * @description
	 * @param driver
	 */
	public MMTHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@alt='Make My Trip']")
	private WebElement _mmtHomePageIcon;

	@FindBy(xpath = "//li[contains(@class,'menu_Hotels ')]/a")
	private WebElement _hotelMenuBar;

	@FindBy(xpath = "//label[@for='city']/span")
	private WebElement _city;

	@FindBy(xpath = "//input[@placeholder='Enter city/ Hotel/ Area/ Building']")
	private WebElement _cityTextBox;

	@FindBy(xpath = "//div[@class='flexOne']/p[text()='Singapore, Singapore']")
	private WebElement _citySelectFromDropdown;

	@FindBy(id = "checkin")
	private WebElement _checkIn;

	@FindBy(id = "checkout")
	private WebElement _checkOut;

	@FindBy(xpath = "//div[@class='DayPicker-Months']/div[1]/div/div/span")
	private WebElement _datePickerYear;

	@FindBy(xpath = "//div[@class='DayPicker-Months']/div[1]/div/div/span/..")
	private WebElement _datePickerMonth;

	@FindBy(xpath = "//span[@aria-label='Next Month']")
	private WebElement _nextMonthArrow;

	@FindBy(xpath = "//span[contains(text(),'ROOMS & GUESTS')]/..")
	private WebElement _roomsAndGuest;

	@FindBys({ @FindBy(xpath = "//ul[@data-cy='adultCount']/li") })
	private List<WebElement> _adultRow;

	@FindBys({ @FindBy(xpath = "//p[@data-cy='childrenRange']/following-sibling::ul/li") })
	private List<WebElement> _childrenRow;

	@FindBy(className = "btnAddRoom")
	private WebElement _addAnotherRoomButton;

	@FindBy(xpath = "//span[@data-cy='travelForText']")
	private WebElement _travelForReason;

	@FindBys({ @FindBy(xpath = "//ul[@class='travelForPopup']/li") })
	private List<WebElement> _travelForReasonList;

	@FindBy(id = "hsw_search_button")
	private WebElement _searchButton;

	@FindBy(xpath = "//button[contains(text(),'APPLY')]")
	private WebElement _applyButtonGuest;

	public void clickOnHotel() {
		waitForElementToBeClickableInUI(_hotelMenuBar);
		_hotelMenuBar.click();
		waitForElementToBeVisibleInUI(_city);
	}

	public void handleCalender(String expectedyear, String expectedMonth, String expectedDay) {
		if (!_datePickerYear.getText().trim().equals(expectedyear)) {
			do {
				_nextMonthArrow.click();
			} while (!_datePickerYear.getText().trim().equals(expectedyear));
		}

		if (!_datePickerMonth.getText().trim().replaceAll("\"", "").replaceAll("\\d", "")
				.equalsIgnoreCase(expectedMonth)) {
			do {
				_nextMonthArrow.click();
			} while (!_datePickerMonth.getText().trim().replace("\"", "").replaceAll("\\d", "")
					.equalsIgnoreCase(expectedMonth));
		}
		List<WebElement> daylist = driver.findElements(By.xpath(
				"//div[@class='DayPicker-Months']/div[1]/div/div/span[text()='" + expectedyear + "']/../../div[text()='"
						+ expectedMonth + "']/../../div[@class='DayPicker-Body']//div[@class='DayPicker-Day']"));

		for (WebElement ele : daylist) {
			if (ele.getText().trim().equals(expectedDay)) {
				ele.click();
				break;
			}
		}
	}

	public void selectRoom(String roomCount, List<Map<String, String>> roomlist) {
		System.out.println(roomlist);
		int count = 1;
		for (Map<String, String> temp : roomlist) {
			System.out.println(temp);
			if (count > 1) {
				_addAnotherRoomButton.click();
			}
			selectGuests(temp);
			count++;
		}
		_applyButtonGuest.click();
	}

	public void selectGuests(Map<String, String> guests) {
		for (String value : guests.keySet()) {
			if (value.equalsIgnoreCase("A")) {
				for (WebElement ele : _adultRow) {
					if (ele.getText().trim().equals(guests.get(value))) {
						ele.click();
						break;
					}
				}
			} else if (value.equalsIgnoreCase("C")) {
				for (WebElement ele : _childrenRow) {
					if (ele.getText().trim().equals(guests.get(value))) {
						ele.click();
						break;
					}
				}
			}
		}
	}

	public void selectTravelReason(String value) {
		_travelForReason.click();
		waitForElementsToBeVisibleInUI(_travelForReasonList);
		for (WebElement element : _travelForReasonList) {
			if (element.getText().trim().equals(value)) {
				element.click();
				break;
			}
		}
	}

	public void selectCheckInDate(String expectedyear, String expectedMonth, String expectedDay) {
		handleCalender(expectedyear, expectedMonth, expectedDay);
	}

	public void selectCheckOutDate(String expectedyear, String expectedMonth, String expectedDay) {
		handleCalender(expectedyear, expectedMonth, expectedDay);
	}

	public void searchHotel(Map<String, String> hotelData) {
		List<Map<String, String>> guestList = new ArrayList<Map<String, String>>();
		Map<String, String> room1 = new HashMap<String, String>();
		room1.put("A", hotelData.get("adultinroom1"));
		room1.put("C", hotelData.get("childinroom1"));
		Map<String, String> room2 = new HashMap<String, String>();
		room2.put("A", hotelData.get("adultinroom2"));
		room2.put("C", hotelData.get("childinroom2"));
		guestList.add(room1);
		guestList.add(room2);
		waitForElementToBeClickableInUI(_city);
		_city.click();
		waitForElementToBeClickableInUI(_cityTextBox);
		_cityTextBox.sendKeys(hotelData.get("city"));
		waitForElementToBeClickableInUI(_citySelectFromDropdown);
		_citySelectFromDropdown.click();
		selectCheckInDate(hotelData.get("checkinyear"), hotelData.get("checkinmonth"), hotelData.get("checkindate"));
		selectCheckOutDate(hotelData.get("checkoutyear"), hotelData.get("checkoutmonth"),
				hotelData.get("checkoutdate"));
		waitForElementToBeClickableInUI(_roomsAndGuest);
		_roomsAndGuest.click();
		selectRoom(hotelData.get("roomcount"), guestList);
		waitForElementToBeClickableInUI(_travelForReason);
		_travelForReason.click();
		waitForElementsToBeVisibleInUI(_travelForReasonList);
		selectTravelReason(hotelData.get("travellingfor"));
		_searchButton.click();
	}
}
