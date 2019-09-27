package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    private void loginAsBankManager() {
        driver.findElement(By.xpath(OR.getProperty("mblBtn"))).click();
        Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("addCustBtn"))));

    }
}
