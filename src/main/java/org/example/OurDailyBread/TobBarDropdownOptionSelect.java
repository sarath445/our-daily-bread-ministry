package org.example.OurDailyBread;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class TobBarDropdownOptionSelect {
      static WebDriver driver;
     public TobBarDropdownOptionSelect(){
         this.driver = driver;
     }

    private static By Books = By.xpath("//span[text()='Books']");



    public static void TobBardrop(String Xpath, String optionToselect ){
        WebElement Bookele = driver.findElement(Books);
        Select dropdown = new Select(Bookele);
        List<WebElement> drop = dropdown.getOptions();
        for (WebElement value : drop){

        }
    }
}
