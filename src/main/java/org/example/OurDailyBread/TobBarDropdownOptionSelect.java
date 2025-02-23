package org.example.OurDailyBread;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TobBarDropdownOptionSelect {
      static WebDriver driver;
      public TobBarDropdownOptionSelect(WebDriver driver){
         this.driver = driver;
     }

    private static By Books = By.xpath("//span[text()='Books']");
     private static By Stationary = By.xpath("//span[text()='Stationery']");



    public static void SelectDropdownOption(String xpath, String option) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement dropdownElement = driver.findElement(By.xpath(xpath));
        System.out.println("Dropdown found...");

        Actions act = new Actions(driver);
        act.moveToElement(dropdownElement).perform();
        dropdownElement.click(); // Click to open dropdown

        // Wait for dropdown options to be present
        By optionsLocator = By.xpath(xpath + "/following-sibling::ul/li/a/span");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionsLocator));

        List<WebElement> allElements = driver.findElements(optionsLocator);

        System.out.println("Options found: " + allElements.size());

        for (WebElement element : allElements) {
            // Try different ways to get text
            String text = element.getText().trim();
            if (text.isEmpty()) {
                text = element.getAttribute("textContent").trim(); // Try textContent
            }
            if (text.isEmpty()) {
                text = element.getAttribute("innerText").trim(); // Try innerText
            }

            System.out.println("Option: " + text + ""); // Debugging

            if (text.equalsIgnoreCase(option)) {
                act.moveToElement(element).build().perform();
                act.click(element).perform();
                String parentWindow = driver.getWindowHandle();    //getting current window.
                Set<String> windowHandles = driver.getWindowHandles(); // Get all window handles

                for (String handle : windowHandles) {
                    if (!handle.equals(parentWindow)) {
                        driver.switchTo().window(handle); // Switch to new tab
                        System.out.println("Switched to new tab.");
                    }
                }

                System.out.println("Selected: " + text);
                break;
            }
        }
    }



}
