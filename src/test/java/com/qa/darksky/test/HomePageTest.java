package com.qa.darksky.test;

import com.qa.darksky.base.BasePage;
import com.qa.darksky.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Properties;

public class HomePageTest {
    WebDriver driver;
    Properties prop;
    BasePage basePage;
    HomePage homePage;

    @BeforeTest
    public void setUp() {
        basePage = new BasePage();
        prop = basePage.initialize_properties();
        driver = basePage.initialize_driver(prop);
        driver.get(prop.getProperty("url"));
        homePage = new HomePage(driver);
    }
    @Test(priority=1)
    public void clickTodayTest() throws InterruptedException {
        homePage.clickToday();
    }
    @AfterTest
    public void tearDown() {
//		basePage.quitBrowser();
    }
}
