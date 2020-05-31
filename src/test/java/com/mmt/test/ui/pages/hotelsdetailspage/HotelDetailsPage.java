/**
 * 
 */
package com.mmt.test.ui.pages.hotelsdetailspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mmt.test.ui.PageHandler.MakeMyTripPage;

/**
 * @author Tanmoy May 31, 2020
 */
public class HotelDetailsPage extends MakeMyTripPage {

	/**
	 * @author Tanmoy
	 * @description
	 * @param driver
	 */
	public HotelDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@id='detpg_room_includes_options']/../div[2]/div[@class='roomRight']/div[1]//li")
	private WebElement _breakfastFree;

	@FindBy(xpath = "//ul[@id='detpg_room_includes_options']/../div[2]/div[@class='roomRight']/div[1]//div[@class='tooltipAlignment']/..")
	private WebElement _totalAmount;

	@FindBy(xpath = "//ul[@id='detpg_room_includes_options']/../div[2]/div[@class='roomRight']/div[1]//a[contains(text(),'SELECT ROOM')]")
	private WebElement _selectRoomButton;

	@FindBy(xpath = "//input[@placeholder='Enter First Name']")
	private WebElement _firstName;

	@FindBy(xpath = "//input[@placeholder='Enter Last Name']")
	private WebElement _lastName;

	@FindBy(xpath = "//input[@placeholder='Enter Email ID']")
	private WebElement _email;

	@FindBy(id = "mNo")
	private WebElement _mobileNumber;

	@FindBy(xpath = "//select[@id='title']")
	private WebElement _titleName;

	@FindBy(xpath = "//select[@id='title']/option")
	private WebElement _titleNameDropDown;

	@FindBy(xpath = "//label[contains(text(),'Smoking room')]")
	private WebElement _smokingRoomCheckBox;

	@FindBy(xpath = "//label[contains(text(),'Twin beds')]")
	private WebElement _twinBedCheckBox;

	@FindBy(xpath = "//label[contains(text(),'You are donating ')]")
	private WebElement _donationCheckBox;

	@FindBy(xpath = "//div[contains(text(),'Pay Now')]")
	private WebElement _payNowButton;

}
