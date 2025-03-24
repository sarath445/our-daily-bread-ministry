package org.example.GenericFunctions;

import org.example.OurDailyBread.BreadWebElements;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class Genericmethods {
    WebDriver driver;
    Actions act;

    //creating constructor.
    public Genericmethods(WebDriver driver) {
        this.driver = driver;
        this.act = new Actions(driver);
    }

    //mouse hovering to element.
    public boolean hoverOverElement(WebElement element) {
        //Actions act = new Actions(driver);
        try{
            act.moveToElement(element).perform();
            return true;
        }
        catch (Exception e){
            System.out.println("Exception in mousehover" + e.getMessage());
            return false;
        }


    }

    public boolean clickingElement(WebElement element) {
        //Actions act = new Actions(driver);
        try {
            act.click(element).perform();
            return true;
        } catch (Exception e) {
            System.out.println("Exception in clicking " + e.getMessage());
            return false;
        }

    }

    public void doubleCLickElement(WebElement element) {
        //Actions act = new Actions(driver);
        act.doubleClick(element);
    }

    public void scrollElement(WebElement element) {
        //Actions act = new Actions(driver);
        act.scrollToElement(element);
    }

    public void contextClickElement(WebElement element) {
        //Actions act = new Actions(driver);
        act.contextClick(element);
    }

    public boolean submitGodhears(WebElement element) {
        try {
            act.click(element).perform();
            return true;
        } catch (Exception e) {
            System.out.println("Exception in submitting" + e.getMessage());
            return false;
        }

    }

    public boolean passingInput(WebElement element, String text)throws Exception{
        try{
            act.sendKeys(element,text).perform();
            return true;
        }
        catch (Exception e){
            System.out.println("Exception in passing" + e.getMessage());
            return false;
        }
    }

    public boolean passingInputEnter(WebElement element, String text)throws Exception{
        try{
            act.sendKeys(element,text + Keys.ENTER).perform();
            return true;
        }
        catch (Exception e){
            System.out.println("Exception in passing" + e.getMessage());
            return false;
        }
    }

    public boolean toggleBtn(WebElement element, boolean enable)throws Exception{
        try{
            boolean isCurrentState = element.isSelected();    //check toggle state of the button

            if(isCurrentState!=enable){
                element.click();
                System.out.println("Toggle switched successfully");
            }
            else {
                System.out.println("Toggle already in enabled state");
            }
            return true;
        }
        catch (Exception e){
           System.out.println("Toggle button has some issues" + e.getMessage());
           return false;
        }
    }

    public boolean addTocart()throws Exception{
        try {
            WebElement cartele = driver.findElement(By.xpath(BreadWebElements.addTocartBtn));
            if(cartele.isEnabled()){
                clickingElement(cartele);
                System.out.println("add to cart button is enable");
                return true;
            }
            else {
                System.out.println("add to cart options is disabled");
                return false;
            }
        }
        catch (Exception e){
            System.out.println("Exception error " +e.getMessage());
            return false;
        }
    }

    public boolean viewCart()throws Exception{
        try{
            WebElement viewCartele = driver.findElement(By.xpath(BreadWebElements.viewCartbtn));
            if(viewCartele.isEnabled()){
                clickingElement(viewCartele);
                System.out.println("add to cart button is enable");
                return true;
            }
            else {
                System.out.println("add to cart options is disabled");
                return false;
            }
        }
        catch (Exception e){
            System.out.println("Exception error " +e.getMessage());
            return false;
        }
    }
}
