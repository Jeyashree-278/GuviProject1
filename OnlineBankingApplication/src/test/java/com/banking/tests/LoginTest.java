package com.banking.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.pages.LoginPage;

public class LoginTest extends BaseTest {
	
	 @Test
	    public void validLoginTest() {
	        LoginPage login = new LoginPage(driver);
	        login.open(baseUrl);
	        login.login("iniya", "GIRL");

	        Assert.assertTrue(login.isAtAccountOverview(), "User not redirected to account summary!");
	    }

	    @Test
	    public void incorrectPasswordTest() {
	        LoginPage login = new LoginPage(driver);
	        login.open(baseUrl);
	        login.login("john", "wrongpass");

	        Assert.assertEquals(login.getErrorMessage(), "An internal error has occurred and has been logged.");
	    }

	    @Test
	    public void unregisteredUserTest() {
	        LoginPage login = new LoginPage(driver);
	        login.open(baseUrl);
	        login.login("unknownUser123", "pass");

	        Assert.assertEquals(login.getErrorMessage(), "An internal error has occurred and has been logged.");
	    }

	    @Test
	    public void emptyFieldsTest() {
	        LoginPage login = new LoginPage(driver);
	        login.open(baseUrl);
	        login.login("", "");

	        Assert.assertEquals(login.getErrorMessage(), "Please enter a username and password.");
	    }

	    @Test
	    public void invalidEmailFormatTest() {
	        LoginPage login = new LoginPage(driver);
	        login.open(baseUrl);
	        login.login("user@com", "demo");

	        Assert.assertEquals(login.getErrorMessage(), "An internal error has occurred and has been logged.");
	    }

	    @Test
	    public void passwordMaskedTest() {
	        LoginPage login = new LoginPage(driver);
	        login.open(baseUrl);

	        Assert.assertTrue(login.isPasswordMasked(), "Password field is not masked!");
	    }
	    @Test
	    public void verifySessionTimeoutDueToInactivity() {
	    	LoginPage login = new LoginPage(driver);
	    	login.open(baseUrl);
	        login.login("iniya", "iniya");

	        // simulate inactivity/session timeout
	        driver.manage().deleteAllCookies();
	        driver.navigate().refresh();

	        Assert.assertTrue(driver.getCurrentUrl().contains("index"),
	                "User not redirected to login page after session timeout");}
	        @Test
	        public void verifyRememberMeFunctionality() {
	        	LoginPage login = new LoginPage(driver);
	        	login.open(baseUrl);
	            login.loginWithRememberMe("iniya", "iniya");
	            driver.quit();
	            driver = new ChromeDriver();
	            driver.get("https://parabank.parasoft.com/parabank/index.htm");

	            Assert.assertTrue(driver.getPageSource().contains("Accounts Overview"),
	                    "User not remembered after browser restart");
	        }    
	        }



