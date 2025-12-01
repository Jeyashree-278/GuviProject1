package com.contact.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.contact.pages.HomePage;
import com.contact.pages.LoginPage;
import com.contact.utils.ConfigReader;

public class LoginTest extends BaseTest {

	@Test(description = "Verify login with valid credentials")
	public void loginWithValidCredentials() {
	LoginPage login = new LoginPage(driver);
	login.open(baseUrl);
	login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
	HomePage home = new HomePage(driver);
	Assert.assertTrue(home.isAt(), "User should see contact list after login");
	}


	@Test(description = "Verify login with invalid password")
	public void loginWithInvalidPassword() {
	LoginPage login = new LoginPage(driver);
	login.open(baseUrl);
	login.login(ConfigReader.get("admin.email"), "wrongPass");
	Assert.assertFalse(new HomePage(driver).isAt(), "Should not navigate to home with wrong credentials");
	}
	
	@Test(description = "Verify login with empty fields")
	public void loginWithEmptyFields() {
	    LoginPage login = new LoginPage(driver);
	    login.open(baseUrl);

	    // Try logging in with empty email and password
	    login.login("", "");
	    
	    // Expect validation message
	    String msg = login.waitForErrorMessage();
	    Assert.assertEquals(msg, "Incorrect username or password");
	}
	    
	    @Test(description = "Verify login with Invalid email id format")
		public void loginWithInvalidEmailFormat() {
		    LoginPage login = new LoginPage(driver);
		    login.open(baseUrl);

		    // Try logging in with invalid email id format
		    login.login("jey", "");
		    // Expect validation message
		    String msg = login.waitForErrorMessage();
		    Assert.assertEquals(msg, "Incorrect username or password");
	}
	    
	    @Test(description = "Verify that password field masks user input")
	    public void verifyPasswordMasking() {
	        LoginPage login = new LoginPage(driver);
	        login.open(baseUrl);
	        Assert.assertTrue(login.isPasswordMasked(), "Password should be masked!");
}
}
