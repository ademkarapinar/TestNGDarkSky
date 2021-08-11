package com.qa.darksky.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {
    WebDriver driver;
    Properties prop;

    public WebDriver initialize_driver(Properties prop) {
        String browser = prop.getProperty("browser");
        String headless = prop.getProperty("headless");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            if (headless.equals("yes")) {
                ChromeOptions co = new ChromeOptions();
                co.addArguments("--headless");
                driver = new ChromeDriver(co);
            } else {
                driver = new ChromeDriver();
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            if (headless.equals("yes")) {
                FirefoxOptions fo = new FirefoxOptions();
                fo.addArguments("--headless");
                driver = new FirefoxDriver(fo);
            } else {
                driver = new FirefoxDriver();
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();

        return driver;
    }

    public Properties initialize_properties() {
        prop = new Properties();
        String path = "./src/main/java/com/qa/darksky/config/config.properties";
        try {
            FileInputStream ip = new FileInputStream(path);
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public void quitBrowser() {
        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println("Some exception occured while quitting the browser.");
        }
    }

    public void closeBrowser() {
        try {
            driver.close();
        } catch (Exception e) {
            System.out.println("Some exception occured while closing the browser.");
        }
    }
}
