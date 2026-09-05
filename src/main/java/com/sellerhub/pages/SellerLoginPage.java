package com.sellerhub.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SellerLoginPage {

	private WebDriver driver;
	private WebDriverWait wait;

	private By email = By.xpath("//input[@name='email']");
	private By password = By.xpath("//input[@name='password']");
	private By submit = By.id("seller-login-button");
	private By sellerLoginLink = By.xpath("//span[text()=' Seller Login']");
	private By errorAlert = By.cssSelector(".alert-danger");
	private By logout = By.xpath("//span[text()='Logout']");
	private By logoutContinue = By.xpath("//a[text()='Continue']");

	public SellerLoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void enterEmail(String email) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.email)).sendKeys(email);
	}

	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.password)).sendKeys(password);
	}

	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
	}

	public void clickSellerLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(sellerLoginLink)).click();
	}

	public void navigateToSellerLogin() {
		clickSellerLogin();
	}

	public void performSellerLogin(String email, String password) {
		enterEmail(email);
		enterPassword(password);
		clickLogin();
	}

	public String getErrorMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(errorAlert)).getText();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void sellerLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
	}

	public void sellerLogoutContinue() {
		wait.until(ExpectedConditions.elementToBeClickable(logoutContinue)).click();
	}

}
