package org.example.GenericFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Genericmethods {
         WebDriver driver;
          Actions act;
        //creating constructor.
    public Genericmethods(WebDriver driver){
       this.driver = driver;
        this.act = new Actions(driver);
    }
    //mouse hovering to element.
    public void hoverOverElement( WebElement element){
        //Actions act = new Actions(driver);
        act.moveToElement(element).build().perform();

    }
    public void clickingElement( WebElement element){
        //Actions act = new Actions(driver);
        act.click(element).perform();
    }
    public void doubleCLickElement( WebElement element){
        //Actions act = new Actions(driver);
        act.doubleClick(element);
    }
    public void scrollElement( WebElement element){
        //Actions act = new Actions(driver);
        act.scrollToElement(element);
    }
    public void contextClickElement( WebElement element){
        //Actions act = new Actions(driver);
        act.contextClick(element);
    }
    public void submitGodhears(WebElement element){
        act.click(element);
    }
}
