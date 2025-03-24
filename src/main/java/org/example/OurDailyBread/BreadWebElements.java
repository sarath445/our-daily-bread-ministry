package org.example.OurDailyBread;

public class BreadWebElements {
    public static String Emails = "//div[@class='control _with-tooltip']/input[@name='username']";
    public static String firstnames = "//div/input[@name='firstname']";
    public static String lastnames = "//div/input[@name='lastname']";
    public static String companys = "//div/input[@name='company']";

    public static String Email1 = "//input[@id='email']";
    public static String firstnames1 = "//input[@id='TextField0']";
    public static String lastnames2 = "//input[@placeholder='Last name']";
    public static String city = "//input[@placeholder='City']";

    public static String ourMinistry = "//div[@class='min-h-9']/div/ul/li[4]/div/div/div";
    public static String joinNowbtn = "//span[text()='Join Now']";

    public static String firstGodhearName = "//input[@name='form_fields[fname]']";
    public static String lastGodhearName = "//input[@name='form_fields[lname]']";
    public static String EmailGodhear = "//input[@name='form_fields[email]']";
    public static String dropdownGodhear = "//select[@name='form_fields[location]']";
    public static String dropdownOptionsGod = "//select[@name='form_fields[location]']/option";
    public static String submitform = "//button[@type='submit']";
    public static String successMsg = "//h2[contains(text(),'you’ll see a note from')]";
    public static String successmsgNew = "//h2[starts-with(text(),'Welcome to the God')]";
    public static String podcastbtn = "//ul[@id='menu-1-88d0bcf']//descendant::a[@href='/podcast']";
    public static String episodeSearch = "//input[@placeholder='Search episodes...']";
    public static String playBtn = "//div[@class='player-grid__controls']/span[@aria-label='Play button']";
    public static String iframe = "//*[@title='Embed Player']";
    public static String forwardBtn = "//div[@class='player-grid__controls']/child::span[@aria-label='Play next episode']";
    public static String rewindBtn = "//div[@class='player-grid__controls']/child::span[@aria-label='Play previous episode']";
    public static String speedBtn = "//div[@class='player-grid__controls']/child::span[@aria-label='Player speed1']";
    public static String duration = "//div[@class='player-grid__duration']/time[1]";
    public static String searchReclaim = "(//div[@class='searchwp-form-input-container swp-items-stretch']/child::input[@type='search'])[1]";
    public static String toggleBtnss = "//div[@class='swp-toggle-switch swp-toggle-switch--mini']";
    public static String authorNames = "//select[@class='swp-select']/option";
    public static String articleImage = "//a[@href='https://reclaimtoday.org/burdens-and-freedom-in-christ/']//div//child::img";
    public static String moreOption = "(//span[text()='More...'])[1]";
    public static String moreWordss = "//div[@data-elementor-type='loop-item']";
    public static String shopDropdownElement= "//a[@class='elementor-item menu-link has-submenu highlighted']//following-sibling::ul/li";
    public static String shopBtn = "(//span[@role='application']//parent::a[contains(text(),'Shop')])[1]";
    public static String tShirt = "(//a[@href='https://reclaimtoday.org/product/short-sleeve-tshirt/'])[1]";
    public static String sizeDropdown = "//select[@id='pa_size']";
    public static String cartMsg = "//div[@class='woocommerce-message']/text()[normalize-space()]";
    public static String addTocartBtn = "//button[text()='Add to cart']";
    public static String plusBtn = "//*[@class='product_title entry-title']//following::a[contains(@id,'plus_qty')]";
    public static String quantity = "//*[@class='product_title entry-title']//following::input[@type='number']";
    public static String viewCartbtn = "//a[text()='View cart']";


}