package org.example.WebDriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverFactory {
    //creating constructor.
    public DriverFactory(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver,this);     // Automatically initialize webelement, @Findby making code cleaner and structurer
    }
    //initializing webdriver.
    public WebDriver driver;

    // setting web browsers using switch case.
    public WebDriver InitializeBrowser(String browserName){
        String web = browserName.toLowerCase();
        switch (web){
            case  "chrome":
               // WebDriverManager.chromedriver().setup();      //Autoupdating the driver.
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

                break;
            case  "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

                break;
            default:
               System.out.println("invalid browser" + browserName);
               throw new IllegalArgumentException();
        }
        return driver;


    }
}
