package org.example.OurDailyBread;

import com.sun.net.httpserver.Authenticator;
import org.example.WebDriverFactory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Helperbread {
    //initializing web driver and web driverwait.
    WebDriver driver;
    WebDriverWait wait;


    //creating constructor and passing parameter
    public Helperbread(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver,this);     //improving
    }

    //creating webelements.
    private By Shop = By.xpath("//li[@class='group relative flex items-center overflow-hidden']/a[@aria-label='Shop']");
    private By Books = By.xpath("//span[text()='Books']");
    private By Devotional = By.xpath("//ul[@class='uaccordion uaccordion-style1 sideacco']/li/a/span[contains(text(),'Devotionals')]");
    private By Addcart = By.xpath("//a[@title='The Family Bible Devotional ']/ancestor::div[contains(@class,'product details product-item-details')]/div[contains(@class,'product-item-inner')]/div/div/form/button");
    private By cartnumber = By.xpath("//span[@class='counter-number']");
    private By checkoutbtn = By.id("top-cart-btn-checkout");
    private By quantityCheck = By.xpath("//*[@type='number']");
    @FindBy (xpath ="//span[@class='count']/parent::div" )
    WebElement cartitem;
    @FindBy (xpath= "//a[@title='The Family Bible Devotional ']")
    WebElement bibleText;
    private By updatebtn = By.xpath("//span[text()='Update']");


    //writing methods.
    public boolean ClickShop() {
       try {
           WebElement shopEle = wait.until(ExpectedConditions.elementToBeClickable(Shop));
           shopEle.click();
           System.out.println("Button clicked successfully");
       }
       catch (NoSuchElementException e){
           System.out.println("button is not clicked");
       }
            String parentWindow = driver.getWindowHandle();    //getting current window.
        Set<String> windowHandles = driver.getWindowHandles(); // Get all window handles

        for (String handle : windowHandles) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle); // Switch to new tab
                System.out.println("Switched to new tab.");
                return true;
            }
        }
        return false;
    }

    public String SelectBook()throws Exception{
        try{
            WebElement bookele = wait.until(ExpectedConditions.visibilityOfElementLocated(Books));    //wait for element to be visible and then click like as explicit wait.
            bookele.click();
            WebElement deveotionele = wait.until(ExpectedConditions.elementToBeClickable(Devotional));
            deveotionele.click();
            if(bibleText ==null){
                throw new NullPointerException();
            }
            return bibleText.getText();
        }
        catch (TimeoutException e){
            System.out.println("button is not clicked");
            return null;
        }

    }
    public void AddandvfyCart(){
        try{
            WebElement cartele = wait.until(ExpectedConditions.elementToBeClickable(Addcart));
            cartele.click();
            WebElement cartclck = wait.until(ExpectedConditions.presenceOfElementLocated(cartnumber));
            cartclck.click();
            WebElement quantityele = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityCheck));
            quantityele.getText();
        }
        catch (Exception e){

        }
    }





    }
