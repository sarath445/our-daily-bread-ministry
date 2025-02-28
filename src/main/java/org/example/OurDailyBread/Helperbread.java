package org.example.OurDailyBread;

import com.sun.net.httpserver.Authenticator;
import org.example.WebDriverFactory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Helperbread {
    //initializing web driver and web driverwait.
    WebDriver driver;
    WebDriverWait wait;
    String quantityText;

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
    private By cartmsg = By.xpath("//span[@class='count']/parent::div");
    @FindBy (xpath= "//a[@title='The Family Bible Devotional ']")
    WebElement bibleText;
    private By updatebtn = By.xpath("//span[text()='Update']");
    private By quantitymsg = By.xpath("//span[@class='count']/parent::div/span[@class='count']");


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
    public void ClickADDtocart()throws Exception{
        try{
            Actions act=new Actions(driver);
            WebElement cartele = wait.until(ExpectedConditions.elementToBeClickable(Addcart));
            act.moveToElement(cartele).build().perform();
            act.click(cartele).perform();
            //cartele.click();
            Thread.sleep(5000);
            WebElement cartclck = wait.until(ExpectedConditions.elementToBeClickable(cartnumber));
            act.moveToElement(cartclck).build().perform();
            //act.click(cartclck).perform();
           // cartclck.click();
        }
        catch (ElementNotInteractableException e){

            WebElement cartclck = wait.until(ExpectedConditions.elementToBeClickable(cartnumber));
            cartclck.click();
            System.out.println("button is not available");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int QuantityvfyCheck()throws InterruptedException{
        Thread.sleep(5000);
        try{
             quantityText = driver.findElement(quantityCheck).getAttribute("value");   //get value from the input field.
            return Integer.parseInt(quantityText);       //convert string to int.
        }
        catch (Exception e){
           System.out.println("quantity is null");
           return 1;
        }
    }

    public String getCartmessage()throws InterruptedException{
        Thread.sleep(2000);
        WebElement cartmsgele = wait.until(ExpectedConditions.visibilityOfElementLocated(cartmsg));
        return cartmsgele.getText();
    }
    public int msgquantity(String xpath){
         WebElement quantele = driver.findElement(By.xpath(xpath));
         String num = quantele.getText().trim();
         return Integer.parseInt(num);

    }
    public void updateBtn(int quantity)throws InterruptedException{

        WebElement quanityties = driver.findElement(quantityCheck);
        quanityties.clear();     //clear existing value.
        Thread.sleep(3000);
        quanityties.sendKeys(String.valueOf(quantity));      //input new quantity from converting string to integer
        WebElement updateele = wait.until(ExpectedConditions.elementToBeClickable(updatebtn));
        updateele.click();     //clicking the update button.

    }
    public void checkoutdetails(String Email, String Firstname, String Lastname, String company)throws Exception{

          WebElement Emailfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.Emails)));
          Emailfield.sendKeys(Email);
          WebElement Firstnamefield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.firstnames)));
          Firstnamefield.sendKeys(Firstname);
          WebElement Lastnamefield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.lastnames)));
          Lastnamefield.sendKeys(Lastname);
          WebElement CompanyField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.companys)));
          CompanyField.sendKeys(company);
    }





    }
