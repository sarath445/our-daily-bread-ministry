package org.example.OurDailyBread;

import com.aventstack.extentreports.util.Assert;
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
        Actions act = new Actions(driver);
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
    public static String pricebook = "//div[@class='product details product-item-details']//following::span[@data-price-type='finalPrice']/span[@class='price']";
    public static String List_Of_Voice = "//div[@class='product details product-item-details']//following::a[@class='product-item-link']";

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
    public void checkoutdetails(String Email, String Firstname, String Lastname, String company) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click checkout button
        WebElement checkele = wait.until(ExpectedConditions.elementToBeClickable(checkoutbtn));
        checkele.click();

        // Wait for iframe and switch to it
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='full_payment']")));

        // Wait for email field inside iframe
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BreadWebElements.Email1)));
        emailField.sendKeys(Email);

          WebElement Emailfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.Email1)));
          Emailfield.sendKeys(Email);
          WebElement Firstnamefield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.firstnames1)));
          Firstnamefield.sendKeys(Firstname);
          WebElement Lastnamefield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.lastnames2)));
          Lastnamefield.sendKeys(Lastname);
          WebElement CompanyField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.city)));
          CompanyField.sendKeys(company);
    }

    public void bookprice(String bookname, String price)throws Exception{
          List<WebElement> voicebookPrice = driver.findElements(By.xpath(pricebook));
          List<WebElement> voicebooklist = driver.findElements(By.xpath(List_Of_Voice));


          try {
              if(voicebooklist.size()==voicebookPrice.size()){
                  System.out.println("booklist and prizelist are same" + voicebooklist.size() + voicebookPrice.size());
                  for(int i = 0; i<voicebooklist.size() && i<voicebookPrice.size(); i++){
                        WebElement elementText = voicebooklist.get(i);
                        WebElement elementPrice = voicebookPrice.get(i);    //getting value of each book and price.
                        String book = elementText.getText().trim();
                        String pricebook = elementPrice.getText().trim();     //getting textvalue of each book and price
                        System.out.println("Books and price " + book + pricebook);
                  }
          }
              else {
                  System.out.println("size is not equal");

              }

          }
          catch (NoSuchElementException e){
              System.out.println("Element not found " + e.getMessage());
        }
    }
    public void joinNowGodHears(){
        try {

        }
    }



    }
