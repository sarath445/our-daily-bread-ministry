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

import static org.example.OurDailyBread.BreadWebElements.ourMinistry;

public class TobBarDropdownOptionSelect {
    static WebDriver driver;

    public TobBarDropdownOptionSelect(WebDriver driver) {
        this.driver = driver;

    }

    private static By Books = By.xpath("//span[text()='Books']");
    private static By Stationary = By.xpath("//span[text()='Stationery']");
    private static By hometitle = By.xpath("//*[@id=\"mainmenu\"]//child::li[contains(@class, \"nav-item level0\")]");
    private static By voicetitle = By.xpath("//li[@class='item product product-item']");
    private static By titlevoice = By.xpath("//div[@class='product details product-item-details']//following::a[@class='product-item-link']");
    public static String List_Of_Voice = "//div[@class='product details product-item-details']//following::a[@class='product-item-link']";
    private static By ministries = By.xpath("//span[text()='Our Ministry']");
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
                    System.out.println("type of cart" + text);
                    if (text.equalsIgnoreCase(titleName)) {
                        option.click(); // Click the matching title
                        System.out.println("Selected title: " + text);
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

    public static void selectVoicecollection(String voiceName) {
Actions act = new Actions(driver);
        try {
            List<WebElement> AllBooksNameInVoiceOption = driver.findElements(By.xpath(List_Of_Voice));

            for (WebElement option : AllBooksNameInVoiceOption) {
                try {
                    String text = option.getText().trim();
                    System.out.println("Book Name : " + text);
                    if (text.equalsIgnoreCase(voiceName)) {
                        act.moveToElement(option).build().perform();
                        act.click(option).perform();
                        System.out.println("Selected title: " + voiceName);
                        break;
                    }

                }
                catch(ElementNotInteractableException e){
                    System.out.println("element not interacting... " + e.getMessage());
                }
            }
        }
        catch(NoSuchElementException e){
            System.out.println("Empty List , No elements found : ");
        }
    }

    public static void selectOurministry(String ministry){
        Actions act = new Actions(driver);
        WebElement breadminis = driver.findElement(ministries);
        act.moveToElement(breadminis).build().perform();

            List<WebElement> searchResults = driver.findElements(By.xpath(BreadWebElements.ourMinistry));

            for (WebElement result : searchResults) {

                String text = result.getText();

                System.out.println("text >> " + text);

                if (text.equalsIgnoreCase(ministry)) {
                    result.click();
                    break;

                }

            }


//            act.moveToElement(driver.findElement(By.xpath(BreadWebElements.ourMinistry))).build().perform();
//            //Select dropdown = new Select(driver.findElement(By.xpath(BreadWebElements.ourMinistry)));   //using select class
//            List<WebElement> ourministry = dropdown.getOptions();
//
//            for(WebElement options :ourministry){
//                 String text =  options.getText();
//                 if(text.equalsIgnoreCase(ministry)){
//                     act.click(options);
//                 }
//
//            }

        }

        }

//*[@id="primaryMenuItem"]/div[3]/a/span[2]
        //*[@class="rounded bg-white/80 shadow p-4 border border-stone-950/10 backdrop-blur-2xl flex flex-col space-y-2 transition ease-out duration-[250ms] pointer-events-none [transform:rotateX(-15deg)] h-0"])[3]//child::div)//child:

