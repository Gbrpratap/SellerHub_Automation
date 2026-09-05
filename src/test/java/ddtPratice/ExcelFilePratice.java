package ddtPratice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelFilePratice {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demoapps.qspiders.com/");
	}
	
	@DataProvider(name = "excelData")
	public Object[][] getExcelData() throws IOException{
		String excelPath = System.getProperty("user.dir")+"/src/test/resources/TestData.xlsx";
		FileInputStream fis = new FileInputStream(excelPath);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getLastCellNum();
		
		
		Object[][] data = new Object[rowCount - 1][colCount];
		DataFormatter formatter = new DataFormatter();
		
		for(int i=1; i<rowCount; i++) {
			XSSFRow row = sheet.getRow(i);
			for(int j=0; j<colCount; j++) {
				XSSFCell cell = row.getCell(j);
				data[i-1][j] = formatter.formatCellValue(cell);
			}
		}
		workbook.close();
		fis.close();
		return data;
	}
	
	@Test(dataProvider = "excelData")
	public void registerAndLoginTest(String regName, String regEmail, String regPassword, 
			String loginEmail, String loginPassword) {
		
		//Registration flow
		driver.findElement(By.xpath("//p[text()='UI Testing Concepts']")).click();
		
		driver.findElement(By.id("name")).sendKeys(regName);
		driver.findElement(By.id("email")).sendKeys(regEmail);
		driver.findElement(By.id("password")).sendKeys(regPassword);
		
		driver.findElement(By.xpath("//button[text()='Register']")).click();
		
		//Login flow without break
		driver.findElement(By.xpath("//h1[text()='Login']/parent::div/descendant::input[@name='email']")).sendKeys(loginEmail);
		driver.findElement(By.id("password")).sendKeys(loginPassword);
		driver.findElement(By.xpath("//button[text()='Login']")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}

}

















