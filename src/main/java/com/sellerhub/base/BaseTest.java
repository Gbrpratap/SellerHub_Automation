package com.sellerhub.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {
		ChromeOptions options = new ChromeOptions();

		// Disable Chrome Password Manager & Breach Alert dialogs
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);
		options.setExperimentalOption("prefs", prefs);

		// Disable popups, notifications, and save password bubble
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-save-password-bubble");

		// Pass options here:
		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.get("https://opencartdemo.purpletreesoftware.com/");
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}