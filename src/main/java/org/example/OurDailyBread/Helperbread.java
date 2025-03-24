package org.example.OurDailyBread;

import com.aventstack.extentreports.util.Assert;
import com.sun.net.httpserver.Authenticator;
import org.example.GenericFunctions.Genericmethods;
import org.example.WebDriverFactory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;
// inheriting Genericmethods class.
public class Helperbread extends Genericmethods {
    private static final Logger log = LoggerFactory.getLogger(Helperbread.class);
    //initializing web driver and web driverwait.
    WebDriver driver;
    WebDriverWait wait;
    String quantityText;
    JavascriptExecutor js;

    //creating constructor and passing parameter
    public Helperbread(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);     //improving
        Actions act = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

    }

    //creating webelements.
    private By Shop = By.xpath("//li[@class='group relative flex items-center overflow-hidden']/a[@aria-label='Shop']");
    private By Books = By.xpath("//span[text()='Books']");
    private By Devotional = By.xpath("//ul[@class='uaccordion uaccordion-style1 sideacco']/li/a/span[contains(text(),'Devotionals')]");
    private By Addcart = By.xpath("//a[@title='The Family Bible Devotional ']/ancestor::div[contains(@class,'product details product-item-details')]/div[contains(@class,'product-item-inner')]/div/div/form/button");
    private By cartnumber = By.xpath("//span[@class='counter-number']");
    private By checkoutbtn = By.id("top-cart-btn-checkout");
    private By quantityCheck = By.xpath("//*[@type='number']");
    @FindBy(xpath = "//span[@class='count']/parent::div")
    WebElement cartitem;
    private By cartmsg = By.xpath("//span[@class='count']/parent::div");
    @FindBy(xpath = "//a[@title='The Family Bible Devotional ']")
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
        } catch (NoSuchElementException e) {
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

    public String SelectBook() throws Exception {
        try {
            WebElement bookele = wait.until(ExpectedConditions.visibilityOfElementLocated(Books));    //wait for element to be visible and then click like as explicit wait.
            bookele.click();
            WebElement deveotionele = wait.until(ExpectedConditions.elementToBeClickable(Devotional));
            deveotionele.click();
            if (bibleText == null) {
                throw new NullPointerException();
            }
            return bibleText.getText();
        } catch (TimeoutException e) {
            System.out.println("button is not clicked");
            return null;
        }

    }

    public void ClickADDtocart() throws Exception {
        try {
            Actions act = new Actions(driver);
            WebElement cartele = wait.until(ExpectedConditions.elementToBeClickable(Addcart));
            act.moveToElement(cartele).build().perform();
            act.click(cartele).perform();
            //cartele.click();
            Thread.sleep(5000);
            WebElement cartclck = wait.until(ExpectedConditions.elementToBeClickable(cartnumber));
            act.moveToElement(cartclck).build().perform();
            //act.click(cartclck).perform();
            // cartclck.click();
        } catch (ElementNotInteractableException e) {

            WebElement cartclck = wait.until(ExpectedConditions.elementToBeClickable(cartnumber));
            cartclck.click();
            System.out.println("button is not available");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int QuantityvfyCheck() throws InterruptedException {
        Thread.sleep(5000);
        try {
            quantityText = driver.findElement(quantityCheck).getAttribute("value");   //get value from the input field.
            return Integer.parseInt(quantityText);       //convert string to int.
        } catch (Exception e) {
            System.out.println("quantity is null");
            return 1;
        }
    }

    public String getCartmessage() throws InterruptedException {
        Thread.sleep(2000);
        WebElement cartmsgele = wait.until(ExpectedConditions.visibilityOfElementLocated(cartmsg));
        return cartmsgele.getText();
    }

    public int msgquantity(String xpath) {
        WebElement quantele = driver.findElement(By.xpath(xpath));
        String num = quantele.getText().trim();
        return Integer.parseInt(num);

    }

    public void updateBtn(int quantity) throws InterruptedException {

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

    public void bookprice(String bookname, String price) throws Exception {
        List<WebElement> voicebookPrice = driver.findElements(By.xpath(pricebook));
        List<WebElement> voicebooklist = driver.findElements(By.xpath(List_Of_Voice));


        try {
            if (voicebooklist.size() == voicebookPrice.size()) {
                System.out.println("booklist and prizelist are same" + voicebooklist.size() + voicebookPrice.size());
                for (int i = 0; i < voicebooklist.size() && i < voicebookPrice.size(); i++) {
                    WebElement elementText = voicebooklist.get(i);
                    WebElement elementPrice = voicebookPrice.get(i);    //getting value of each book and price.
                    String book = elementText.getText().trim();
                    String pricebook = elementPrice.getText().trim();     //getting textvalue of each book and price
                    System.out.println("Books and price " + book + pricebook);
                }
            } else {
                System.out.println("size is not equal");

            }

        } catch (NoSuchElementException e) {
            System.out.println("Element not found " + e.getMessage());
        }
    }

    public void joinNowGodHears() throws Exception {
        try {
            WebElement scroll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.joinNowbtn)));
            scrollElement(scroll);
            Thread.sleep(3000);
            clickingElement(scroll);
            Thread.sleep(2000);
            //clickingElement(scroll);
        } catch (NoSuchElementException e) {
            System.out.println("element is not found " + e.getMessage());

        }
    }

    public void fillingForm(String First, String Last, String Email, String countryOption) throws Exception {
        try {
            WebElement formSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.submitform)));
            WebElement firstelement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BreadWebElements.firstGodhearName)));
            firstelement.sendKeys(First);
            WebElement lastelement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BreadWebElements.lastGodhearName)));
            lastelement.sendKeys(Last);
            WebElement Emailelement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BreadWebElements.EmailGodhear)));
            Emailelement.sendKeys(Email);
            WebElement Countryelement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BreadWebElements.dropdownGodhear)));

            Select dropdownCountry = new Select(Countryelement);     //setting the dropdown element
            List<WebElement> countryList = dropdownCountry.getOptions();  //storing all list elements as options.
            for (WebElement options : countryList) {
                String text = options.getText().trim();
                System.out.println("total countries in the list " + text);
                if (text.equalsIgnoreCase(countryOption)) {
                    options.click();
                    break;
                } else {
                    System.out.println("country is not found");
                }
            }
            submitGodhears(formSubmit);


        } catch (ElementNotInteractableException e) {
            System.out.println("Element is not interactimg....." + e.getMessage());
        }
    }

    public String vfySuccessfullmsg() {
        WebElement submitele = driver.findElement(By.xpath(BreadWebElements.successmsgNew));
        return submitele.getText();
    }

    public void audioPlayPause() throws Exception {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(BreadWebElements.iframe)));
            WebElement playele = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BreadWebElements.playBtn)));
            js.executeScript("arguments[0].click();", playele);
            //js.executeScript("arguments[0].play()", playele);
            //playele.click();
            log.info("Audio is playing....");
            Thread.sleep(5000);
            boolean isPlaying = (boolean) js.executeScript("return !arguments[0].paused;", playele);
            if (isPlaying) {
                System.out.println("audion is playing fine..");
            } else {
                System.out.println("audio is not playing...check");
            }
            js.executeScript("arguments[0].paused()", playele);
            System.out.println("audio is paused..");
            Thread.sleep(3000);
            boolean isPaused = (boolean) js.executeScript("return arguments[0].paused", playele);
            if (isPaused) {
                System.out.println("audio is paused successfully");
            } else {
                System.out.println("fail to pause audio");
            }
        } catch (Exception e) {
            System.out.println("play button not found" + e.getMessage());
            WebElement playele = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BreadWebElements.playBtn)));
            playele.click();
        }
    }

    public void audioPlay() throws Exception {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(BreadWebElements.iframe)));
            WebElement playele = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BreadWebElements.playBtn)));
            scrollElement(playele);
            js.executeScript("arguments[0].click();", playele);
            //js.executeScript("arguments[0].play()", playele);
            log.info("Audio is playing....");
            Thread.sleep(10000);
            boolean isPlaying = (boolean) js.executeScript("return !arguments[0].paused;", playele);
            if (isPlaying) {
                System.out.println("audion is playing fine..");
            } else {
                System.out.println("audio is not playing...check");
            }

        } catch (Exception e) {
            System.out.println("play button not found" + e.getMessage());
            WebElement playele = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BreadWebElements.playBtn)));
            playele.click();
        }
    }

    public void forwardPlayClick() throws Exception {
        audioPlay();
        try {
            WebElement forwardele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BreadWebElements.forwardBtn)));
            for (int i = 0; i < 6; i++) {
                Thread.sleep(2000);
                //forwardele.click();
                js.executeScript("arguments[0].click(); ", forwardele);
                System.out.println("click using js " + (i + 1) + "times ");
                Thread.sleep(2500);    //wait 2 seconds between click.
            }
        } catch (Exception e) {
            System.out.println("forwardBtn is not found " + e.getMessage());

        }
    }

    public void rewindPlayClick() throws Exception {
        try {
            WebElement rewindele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BreadWebElements.rewindBtn)));
            for (int i = 0; i < 5; i++) {
                js.executeScript("arguments[0].click(); ", rewindele);
                System.out.println("Rewind button is clickable" + (i + 1) + "times ");
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println("Rewind button is not found " + e.getMessage());

        }

    }

    public void speedPlay(int playback)throws Exception{
        try{
            WebElement speedele = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BreadWebElements.speedBtn)));
            speedele.getAttribute("value");
            WebElement audioDuration = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BreadWebElements.duration)));
            double playbackRate = (double) js.executeScript("return arguments[0].playbackRate;", audioDuration);
            if(playbackRate==playback){
                System.out.println("playspeed verification passed: " + playbackRate);
            }
            else {
                System.out.println("playbackspeed verification failed");
            }

        }
        catch (Exception e){
             System.out.println("element is not found " + e.getMessage());
        }

    }
    public void reclaimArticle(String reclaim)throws Exception{
        try{
            WebElement searchele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BreadWebElements.searchReclaim)));
            passingInputEnter(searchele, reclaim);
            Thread.sleep(2500);


        }
        catch (Exception e){
           System.out.println("element not found" + e.getMessage());
        }

    }

    public boolean setToggle(boolean enable)throws Exception{
        try{
            WebElement togglele = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BreadWebElements.toggleBtnss)));
             return toggleBtn(togglele, enable);
        }
        catch (Exception e){
            System.out.println("toggle button is vanished");
            return  false;
        }

    }

    public void authorSelection(String nameOfAuthor)throws Exception{
        try{
            WebElement formSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.submitform)));
            WebElement imagele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.articleImage)));
             List<WebElement> authorResult = driver.findElements(By.xpath(BreadWebElements.authorNames));
             for (WebElement options : authorResult){
                 String text = options.getText();
                 if(text.equalsIgnoreCase(nameOfAuthor)){
                     options.click();
                 }
             }
             submitGodhears(formSubmit);
             Thread.sleep(2000);
             imagele.click();

        }
        catch (Exception e){
               System.out.println("authornames are not found" + e.getMessage());
        }
    }

    public boolean moreOptions()throws Exception{
            try{
                  WebElement morele = driver.findElement(By.xpath(BreadWebElements.moreOption));
                  if(morele.isEnabled()){
                      clickingElement(morele);
                      System.out.println("more option is enable");
                      return true;
                  }
                  else {
                      System.out.println("more option is disabled");
                      return false;
                  }
            }
            catch (Exception e){
                System.out.println("Exception error " +e.getMessage());
                return false;

            }
    }
    public void shirtSizeselection(String size)throws Exception{
        WebElement tShirtele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BreadWebElements.tShirt)));
        tShirtele.click();

        Select dropdown = new Select(driver.findElement(By.xpath(BreadWebElements.sizeDropdown)));
        List<WebElement> sizesShirt = dropdown.getOptions();
        for (WebElement options : sizesShirt){
            String text = options.getText();
            System.out.println("selected size " + text);
            if(text.equalsIgnoreCase(size)){
                options.click();
                break;
            }

        }
    }
    public void addQuantity()throws Exception{
        try {
            WebElement plusele = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BreadWebElements.plusBtn)));
            WebElement quantityele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BreadWebElements.quantity)));
            int initialQuantity = Integer.parseInt(quantityele.getAttribute("value"));

            for (int i =0; i<5; i++){
                plusele.click();
                int expectedQuantity = initialQuantity + i + 1;
                wait.until(ExpectedConditions.attributeToBe(quantityele, "value", String.valueOf(expectedQuantity)));
                int updatedQuantity = Integer.parseInt(quantityele.getAttribute("value"));     //for getting the updated quantity.
                if(updatedQuantity==initialQuantity + i+1){
                    System.out.println("value " + (i+1));
                }
                else {
                    System.out.println("value " + (i+1));
                }
                System.out.println("click using js " + (i + 1) + "times ");
                Thread.sleep(2500);
            }
        }
        catch (Exception e){
            System.out.println("Elements not found" + e.getMessage());

        }

    }


}
