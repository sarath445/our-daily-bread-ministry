package org.example.OurDailyBread;

import org.openqa.selenium.*;
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

    public TobBarDropdownOptionSelect(WebDriver driver) {
        this.driver = driver;
    }

    private static By Books = By.xpath("//span[text()='Books']");
    private static By Stationary = By.xpath("//span[text()='Stationery']");
    private static By hometitle = By.xpath("//*[@id=\"mainmenu\"]//child::li[contains(@class, \"nav-item level0\")]");
    private static By voicetitle = By.xpath("//li[@class='item product product-item']");


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

    public static void selectTitleoption(String titleName) {
        try {
            WebElement dropdownElement = driver.findElement(hometitle); // Locate dropdown
            List<WebElement> options = dropdownElement.findElements(hometitle); // Get all options

            for (WebElement option : options) {
                try {
                    String text = option.getText().trim(); // Get text safely and remove unwanted space.
                    if (text.equalsIgnoreCase(titleName)) {
                        option.click(); // Click the matching title
                        System.out.println("Selected title: " + titleName);
                        return;
                    }
                } catch (StaleElementReferenceException e) {
                    System.out.println("Element became stale, retrying...");
                    dropdownElement.findElements(By.tagName("option")); // Re-locate elements
                }
            }
            System.out.println("Title not found: " + titleName);
        } catch (NoSuchElementException e) {
            System.out.println("Dropdown not found: " + e.getMessage());
        }
    }
    public static void selectVoicecollection(String voiceName){
        try {
            WebElement dropdownElement = driver.findElement(voicetitle);
            List<WebElement> voiceoptions =  dropdownElement.findElements(voicetitle);

            for(WebElement option:voiceoptions){
                try {
                    String text = option.getText().trim();
                    if(text.equalsIgnoreCase(voiceName)){
                        option.click();   //clicking the matching title
                        System.out.println("Selected title: " + voiceName);
                        return;
                    }
                }
                catch (ElementNotInteractableException e){
                    System.out.println("element not interacting... " + e.getMessage());
                    dropdownElement.findElements(By.tagName("option"));
                }
            }
        }
        catch (NoSuchElementException e){
            System.out.println("text is not found: " + e.getMessage());
        }

    }
}
