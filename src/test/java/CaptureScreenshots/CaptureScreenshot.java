package CaptureScreenshots;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class CaptureScreenshot {
    WebDriver driver;

    public CaptureScreenshot(WebDriver driver){
        this.driver = driver;
    }

    public void capturingscreenshots(String testname){
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File("./Screenshots/" + testname + ".png");
        try {
            FileHandler.copy(source, destination);
            System.out.println("Screenshot saved: " + destination.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
    }

