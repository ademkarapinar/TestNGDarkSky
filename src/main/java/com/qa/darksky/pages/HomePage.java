package com.qa.darksky.pages;

import com.qa.darksky.base.BasePage;
import com.qa.darksky.util.ElementUtil;
import com.qa.darksky.util.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {
    WebDriver driver;
    ElementUtil elementUtil;
    JavaScriptUtil javaScriptUtil;


    By currentLocBtn = By.xpath("//*[@id=\"searchForm\"]/input");
    By todayBtn = By.xpath("//span[contains(text(),'Today')]");
    By firstTemp = By.xpath("((//*[@class='temps'])[2]/span)/span[contains(text(), '°')][1]");
    By allTemps = By.xpath("((//*[@class='temps'])[2]/span)/span[contains(text(), '°')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
        javaScriptUtil = new JavaScriptUtil(driver);

    }

    public void clickToday() throws InterruptedException {
        elementUtil.doClick(currentLocBtn);
        elementUtil.doSendKeys(currentLocBtn, "929–973 Bagby St, Houston, TX");
        Thread.sleep(8000);

        javaScriptUtil.specificScrollPageDown();
        Thread.sleep(3000);
        elementUtil.doClick(todayBtn);

        String stringFirstTemp = elementUtil.doGetText(firstTemp);
        elementUtil.waitForElementPresent(firstTemp);

        String text = stringFirstTemp.replaceAll("°", "");
        int intFirstTemp = Integer.parseInt(text);
        int lowest = intFirstTemp;
        int highest = intFirstTemp;
        List<WebElement> allDHourlyTemps = driver.findElements(allTemps);
        for (int i = 0; i < allDHourlyTemps.size(); i++) {
            String stringTepms = allDHourlyTemps.get(i).getText();
            String text1 = stringTepms.replaceAll("°", "");
            int intTemp = Integer.parseInt(text1);
            if (lowest > intTemp) {
                lowest = intTemp;
            }
            if (highest < intTemp) {
                highest = intTemp;
            }
        }
        System.out.println("Lowest " + lowest + "°");
        System.out.println("Highest " + highest + "°");
    }
}
