package com.demo.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.pages.HomePage;
import com.demo.pages.LoginPage;
import com.demo.utils.ConfigReader;

public class UIAndUXValidationTest extends BaseTest {
	 @Test(description="verifyActionButtonsVisibility")
	    public void verifyActionButtonsVisibility() throws InterruptedException {

	        HomePage home = new HomePage(driver);
	        LoginPage lp = new LoginPage(driver);
			lp.open(baseUrl);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			Thread.sleep(3000);
	        Assert.assertTrue(home.isAddToCartVisible(), "'Add to Cart' button is not visible!");
	        Assert.assertTrue(home.isLoginVisible(), "'Log in' button is not visible!");
	        Assert.assertTrue(home.isSignupVisible(), "'Sign up' button is not visible!");
	    }
	 @Test
	    public void verifyAlertAndPopupStyle() {
	 }
	 

	     @Test(description="verifyFontConsistencyAcrossPages")
	     public void verifyFontConsistencyAcrossPages() {

	         HomePage home = new HomePage(driver);
	         home.open(baseUrl);
	         LoginPage lp = new LoginPage(driver);
	 		lp.open(baseUrl);
	 		lp.login(ConfigReader.get("admin.username"), ConfigReader.get("admin.password"));
	         String homeFont = getFont(home.getFirstProductTitle());

	         // Go to Cart
	         home.clickCart();
	         String cartFont = getFont(driver.findElement(By.tagName("body")));

	         // Go to Contact
	         home.clickContact();
	         String contactFont = getFont(driver.findElement(By.tagName("body")));

	         Assert.assertEquals(cartFont, homeFont, "Font mismatch on Cart page!");
	         Assert.assertEquals(contactFont, homeFont, "Font mismatch on Contact page!");
	     }

	     private String getFont(WebElement element) {
	         return element.getCssValue("font-family") + 
	                "|" + element.getCssValue("font-size") +
	                "|" + element.getCssValue("font-weight");
	     }
	 }

	 
	 
	

