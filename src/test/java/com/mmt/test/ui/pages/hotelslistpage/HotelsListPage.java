/**
 * 
 */
package com.mmt.test.ui.pages.hotelslistpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mmt.test.ui.PageHandler.MakeMyTripPage;

/**
 * @author Tanmoy May 31, 2020
 */
public class HotelsListPage extends MakeMyTripPage {

	/**
	 * @author Tanmoy
	 * @description
	 * @param driver
	 */
	public HotelsListPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private String hotelName = "";

	@FindBy(xpath = "//label[contains(text(),'4 & above (Very Good)')]")
	private WebElement _veryGoodUserRating;

	@FindBy(xpath = "//div[@id='hotelListingContainer']/div/div/div[5]")
	private WebElement _HotelFifth;

	@FindBy(xpath = "//div[@id='hotelListingContainer']/div/div/div[5]//p[@id='hlistpg_hotel_name']")
	private WebElement _hotelName;

	public void selectRating() {
		waitForElementToBeVisibleInUI(_veryGoodUserRating);
		_veryGoodUserRating.clear();
	}

	public String getHotelName() {
		return hotelName;
	}

	public void selectHotel() {
		hotelName = _hotelName.getText();
		scrollToElement(_HotelFifth);
		waitForElementToBeClickableInUI(_HotelFifth);
		_HotelFifth.click();
	}

}
