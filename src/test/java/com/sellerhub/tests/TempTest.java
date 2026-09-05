package com.sellerhub.tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TempTest {
	
	//page title : Seller Login
	
	@Test
	public void sellerHubLoginTest() {
		
		//Lanuch the browser
		WebDriver driver = new ChromeDriver();
		
		//maximize the window
		driver.manage().window().maximize();
		
		//implicit wait for this temp test
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//navigate to the application
		driver.get("https://opencartdemo.purpletreesoftware.com/");
		
		//locate login option
		WebElement login = driver.findElement(By.xpath("//span[text()=' Seller Login']"));
		
		//click no login option to navigate to seller login page
		login.click();
		
		//locate email field
		WebElement emailField = driver.findElement(By.id("seller-email"));
		
		//enter email address based on the current test case either positive or negative
		emailField.sendKeys("seller123@gmail.com");
		
		//locate password field
		WebElement passwordField = driver.findElement(By.id("seller-password"));
		
		//enter password based on the current test case and email field input either positive or negative
		passwordField.sendKeys("seller123");
		
		//Find the login button
		WebElement loginButton = driver.findElement(By.id("seller-login-button"));
		
		//click on login button
		loginButton.click();
		
		//capture the error
		
		//Creating date and time format string
		String dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		
		//casting driver to screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		//Capture the active browser viewport and store in temp file and point it with a variable
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		//Dynamically get the file root path and construct a path
		String destinationPath = System.getProperty("user.dir") + "/screenshots/seller_Login" + "_" + dateTime + ".png";
		
		//Declare destination
		File destination = new File(destinationPath);
		
		try {
			//check if screenshot folder is not null and if the folder not exist if true make a folder
			if(destination.getParentFile() != null && !destination.getParentFile().exists()) {
				destination.getParentFile().mkdirs();
			}
			//copy the temp image to destination and print successful message
			FileHandler.copy(source, destination);
			System.out.println("Screenshot saved scuessfully at: " + destinationPath);
		}catch(IOException e) {
			System.out.println("Failed to capture screenshot: " + e.getMessage());
		}
		
		//get the page current title after login attempt
		String pageTitle = driver.getTitle();
		
		//verify asserting
		Assert.assertTrue(pageTitle.contains("Seller Login"), "Seller Login invalid attempt test pass!");
		
		//quite the browser
		driver.quit();
		
		
		
	}
	

}
