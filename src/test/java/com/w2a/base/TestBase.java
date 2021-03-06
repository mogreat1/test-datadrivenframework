package com.w2a.base;

import com.google.inject.internal.cglib.proxy.$Dispatcher;
import com.w2a.utilities.ExcelReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static ExcelReader excel = new ExcelReader("../DataDrivenFramework/src/test/resources/excel/testData.xlsx");
    public static WebDriverWait wait;

    @BeforeTest
    public void setUp() {

        if (driver == null) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("../DataDrivenFramework/src/test/resources/properties/config.properties");

                config.load(fis);
                fis = new FileInputStream("../DataDrivenFramework/src/test/resources/properties/OR.properties");
                OR.load(fis);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (config.getProperty("browser").equals("firefox")) {
            driver = new FirefoxDriver();

        } else if (config.getProperty("browser").equals("chrome")) {
            driver = new ChromeDriver();

        }
        driver.get(config.getProperty("testSiteUrl"));
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();

        }

    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

}
