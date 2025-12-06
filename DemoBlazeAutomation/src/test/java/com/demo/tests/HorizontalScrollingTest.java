package com.demo.tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.pages.HomePage;
import com.demo.pages.LoginPage;
import com.demo.utils.ConfigReader;

public class HorizontalScrollingTest extends BaseTest {

    @Test
    public void verifyNoHorizontalScrollingOnMobile() {

    	 LoginPage login = new LoginPage(driver);
         login.open(baseUrl);
 		//login.login(ConfigReader.get("admin.username"), ConfigReader.get("admin.password"));
 		//login.waitForLoginModalToClose();
        // Simulate mobile
        driver.manage().window().setSize(new Dimension(375, 812)); // iPhone size

        JavascriptExecutor js = (JavascriptExecutor) driver;

        long scrollWidth = (Long) js.executeScript(
                "return document.body.scrollWidth;");
        long clientWidth = (Long) js.executeScript(
                "return document.body.clientWidth;");

        Assert.assertTrue(scrollWidth <= clientWidth,
                "Horizontal scrolling detected on mobile view!");
    }
}

