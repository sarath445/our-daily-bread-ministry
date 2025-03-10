package HelperbreadTest;

import ExcelUtility.DataExcel;
import TestngListener.ITestListeners;
import org.example.OurDailyBread.Helperbread;
import org.example.OurDailyBread.TobBarDropdownOptionSelect;
import org.example.WebDriverFactory.DriverFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.*;

import java.util.Random;
@Listeners(ITestListeners.class)               //initialzing Listeners for start, failure, success and finish.
public class BreadTest {
    WebDriver driver;
    DriverFactory factory;
    Helperbread bread;
    public String url = "https://www.odbm.org/";
    TobBarDropdownOptionSelect topbar;



    @BeforeClass
   public void objsetup(){
       factory=new DriverFactory(driver);
       driver = factory.InitializeBrowser("chrome");
       bread = new Helperbread(driver);
       topbar = new TobBarDropdownOptionSelect(driver);
}
   @BeforeMethod
   public void breadpage(){
       driver.navigate().to(url);
       driver.manage().window().maximize();
   }

    @Test(priority = 1, description = "verification of adding books into cart and continue shopping", dataProvider = "shippings", dataProviderClass = BreadTestData.class)
    public void ShopSelectBook(String Email, String Firstname, String Lastname, String company)throws Exception{
       Random random = new Random();
       int randomquantity = random.nextInt(10) + 1;     //generating numbers between 1 - 10.
       //bread.ClickShop();
       Assert.assertTrue(bread.ClickShop(), "shop is clickable");

       String actual= bread.SelectBook();
       String expected = "The Family Bible Devotional";
       Assert.assertEquals(actual,expected,"bibile text is not matched");
       //Thread.sleep(3000);
       bread.ClickADDtocart();

       //verify initial cart quantity and message.
       int initialquantity = bread.QuantityvfyCheck();
       String initialmessage = bread.getCartmessage();
       Assert.assertEquals(initialquantity, 1, "initialcart quantity mismatch");
       Assert.assertEquals(initialmessage, "1 Item in Cart", "initial cart message mismatch");
       System.out.println("Initial quanitity" + initialquantity);

       bread.updateBtn(randomquantity);
       //verify the updated cart quanity and message.
        Thread.sleep(2000);
        String itemxpath ="//span[@class='count']/parent::div/span[@class='count']";
        int updatedquantity = bread.msgquantity(itemxpath);          //getting quantity
        //String updatedmessage = (updatedquantity==1) ? "1 Item in the cart" : updatedquantity + "items in the cart";
        int updatedmessage = bread.QuantityvfyCheck();   //getting message

        System.out.println("updated quantity" + updatedquantity);
        //Assert.assertEquals(updatedquantity, randomquantity,"updated cart quantity mismatch");
        Assert.assertEquals(updatedquantity, updatedmessage, "updated quantity and message are equal");
        System.out.println("Test passed and verify with random quantity" + randomquantity);
        bread.checkoutdetails(Email,Firstname, Lastname, company);
   }
   @Test(priority = 3)
   public void Tobbartest()throws Exception{
       Assert.assertTrue(bread.ClickShop(), "shop is clickable");
       String xppp = "//a[@href='https://ourdailybreadpublishing.org/books.html']";
       TobBarDropdownOptionSelect.SelectDropdownOption(xppp,"Children");

   }
   @Test(priority = 2)
   public void Topbartitle()throws Exception{
       Assert.assertTrue(bread.ClickShop(), "shop is clickable");
       TobBarDropdownOptionSelect.selectTitleoption("VOICES COLLECTION");

   }
   @Test()
   public void checktext()throws Exception{
       Assert.assertTrue(bread.ClickShop(), "shop is clickable");
       TobBarDropdownOptionSelect.selectTitleoption("VOICES COLLECTION");
       Thread.sleep(5000);
       TobBarDropdownOptionSelect.selectVoicecollection("Caring Well");
   }
   @Test
   public void BookandPrice()throws Exception{
       Assert.assertTrue(bread.ClickShop(), "shop is clickable");
       TobBarDropdownOptionSelect.selectTitleoption("VOICES COLLECTION");
       bread.bookprice("Prayer and Pen", "$17.00");
   }
   @Test( dataProvider = "shippings", dataProviderClass = BreadTestData.class)
   public void BreadourMinistry(String First, String Last, String Email, String Country)throws Exception{
        TobBarDropdownOptionSelect.selectOurministry("God hears her");
        bread.joinNowGodHears();
        bread.fillingForm(First, Last, Email, Country);
        String Actualmsg = bread.vfySuccessfullmsg();
        boolean k = Actualmsg.startsWith("ddnfd");
        System.out.println(k);
        String Expectedmsg = "Welcome to the God Hears Her community! By signing up to receive emails, you’ll see a note from Our Daily Bread Ministries in your inbox. Please use that email to confirm your account to access all the latest God Hears Her news.";
        Assert.assertEquals(Actualmsg.trim(), Expectedmsg.trim(), "messages doesn't matches");
   }
//   @AfterMethod
//   public void cleanup(){
//       driver.manage().deleteAllCookies();
//   }
//    @AfterClass
//    public void teardown(){
//       driver.quit();
//    }
}
