package com.sellerhub.tests;

import com.sellerhub.base.BaseTest;
import com.sellerhub.pages.SellerLoginPage;
import com.sellerhub.utils.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SellerHubLoginTest extends BaseTest {
	@Test(description = "Verify invalid seller login error handling")
	public void testInvalidSellerLogin() {

		SellerLoginPage slp = new SellerLoginPage(driver);

		slp.navigateToSellerLogin();
		slp.performSellerLogin("seller123@gmail.com", "seller123");

		ScreenshotUtil.captureScreenshot(driver, "Seller_Login");

		String actualError = slp.getErrorMessage();
		Assert.assertTrue(actualError.contains("No match"), "Validation message did not match expected error.");
	}

	@Test(description = "Verify valid seller login error handling")
	public void testValidSellerLogin() {

		SellerLoginPage slp = new SellerLoginPage(driver);

		slp.navigateToSellerLogin();
		slp.performSellerLogin("seller@purpletreesoftware.com", "demo123");

        ScreenshotUtil.captureScreenshot(driver, "Seller_Dashboard");

		// Assertion 1: Verify successful login
	    String actualPageTitle = slp.getPageTitle();
	    Assert.assertTrue(actualPageTitle.contains("Dashboard"), "Failed to reach Seller Dashboard.");

	    // Action 2: Logout flow
	    slp.sellerLogout();
	    slp.sellerLogoutContinue();
	    
	    ScreenshotUtil.captureScreenshot(driver, "Seller_Logout");

	    // Assertion 2: Verify successful logout
	    String actualPageTitleLogout = slp.getPageTitle();
	    Assert.assertTrue(actualPageTitleLogout.contains("Purpletree Demo"), "Logout process was unsuccessful.");

	}

}