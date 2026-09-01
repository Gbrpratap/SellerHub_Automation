package com.sellerhub.tests;

import com.sellerhub.base.BaseTest;
import com.sellerhub.utils.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SellerHubSmokeTest extends BaseTest {

    @Test
    public void verifyPortalTitleAndCaptureScreenshot() {
        // Open a target page (using standard practice URL for domain testing)
        driver.get("https://example.com");

        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);

        // Take a screenshot using our FileHandler utility
        ScreenshotUtil.captureScreenshot(driver, "SmokeTest_Homepage");

        // Verify assertion
        Assert.assertTrue(pageTitle.contains("Example"), "Page title verification failed!");
    }
}