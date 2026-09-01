package com.sellerhub.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String dateName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destinationPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + dateName + ".png";
        File destination = new File(destinationPath);

        try {
            // Automatically create the /screenshots directory if it doesn't exist yet
            if (destination.getParentFile() != null && !destination.getParentFile().exists()) {
                destination.getParentFile().mkdirs();
            }
            
            FileHandler.copy(source, destination);
            System.out.println("Screenshot saved successfully at: " + destinationPath);
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }

        return destinationPath;
    }
}