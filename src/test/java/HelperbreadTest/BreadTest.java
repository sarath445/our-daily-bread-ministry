package HelperbreadTest;

import org.example.OurDailyBread.Helperbread;
import org.example.WebDriverFactory.DriverFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BreadTest {
    WebDriver driver;
    DriverFactory factory;
    Helperbread bread;
    public String url = "https://www.odbm.org/";

   @BeforeClass
   public void objsetup(){
       factory=new DriverFactory(driver);
       driver = factory.InitializeBrowser("chrome");
       bread = new Helperbread(driver);
}
   @BeforeMethod
   public void breadpage(){
       driver.navigate().to(url);
       driver.manage().window().maximize();
   }

    @Test
    public void ShopSelectBook()throws Exception{
       //bread.ClickShop();
       Assert.assertTrue(bread.ClickShop(), "shop is clickable");
       String actual= bread.SelectBook();
       String expected =
               "The Family Bible Devotional";
       Assert.assertEquals(actual,expected,"bibile text is not matched");

    }

    @AfterTest
    public void teardown(){
       driver.quit();
    }
}
