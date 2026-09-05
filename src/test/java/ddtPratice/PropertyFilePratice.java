package ddtPratice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PropertyFilePratice {
	
	WebDriver driver;
	Properties prop;
	
	@BeforeClass
	public void setup() throws IOException {
		
		//Initlize the properties object
		prop = new Properties();
		
		//Locate file dynamically using project path
		String filePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
		
		//Load the file into memory via FileInputStream
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		
		//Launch browser using URL from properties file
		String browser = prop.getProperty("browser");
		
		//cross browser testing logic
		if (browser == null || browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser specified in config: " + browser);
        }
		
		//navigate to the application
		driver.get(prop.getProperty("url"));
		
		//Maximize the window
		driver.manage().window().maximize();
		
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@Test(priority = 1)
	public void registerTest() {
		//Retrieve data from properties file using prop.getProperty("key")
		String name = prop.getProperty("reg_name");
		String email = prop.getProperty("reg_email");
		String password = prop.getProperty("reg_password");
		
		//navigate to the register page
		driver.findElement(By.xpath("//p[text()='UI Testing Concepts']")).click();
		
		//Locating the text field and button and providing the config input
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[text()='Register']")).click();
	}
	
	@Test(priority = 2)
	public void loginTest() {
		//Retrieve login credentials from properties file
		String email = prop.getProperty("login_email");
		String password = prop.getProperty("login_password");
		
		/*Adding Explicit wait for this specific case due to StaleElementReferenceException due to race condition during DOM reload 
		 * and also cause ElementNotInteractableException but not both at the same time. 
		 * Detail breakdown in oneNote path Manual Tesing/Selenium/Error breakdown/demo app DDT property file
		 */
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
//		emailField.sendKeys(email);
		
		//solved the race condition by using different locator for the start of the driver reference
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Login']")).click();
	}
	
	@AfterClass
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}

}





















