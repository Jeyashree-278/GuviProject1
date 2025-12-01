package com.contact.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.contact.pages.AddContactPage;
import com.contact.pages.BasePage;
import com.contact.pages.ContactListPage;
import com.contact.pages.HomePage;
import com.contact.pages.LoginPage;
import com.contact.utils.ConfigReader;

public class InputValidationAndSecurityTest extends BaseTest {

	
	
	@Test(description="Test Max Character Limit For FirstName")
	public void testMaxCharacterLimitForFirstName() {

	    String longText = "A".repeat(350);  // 350 characters

	    LoginPage login = new LoginPage(driver);
	    ContactListPage list = new ContactListPage(driver);
	    AddContactPage add = new AddContactPage(driver);
        login.open(baseUrl);
        login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isAt());
        home.clickAdd();
	    add.typeFirstName(longText);

	    String actual = add.getFirstNameValue();

	    // Validate field accepted characters (should accept all)
	    Assert.assertFalse(actual.length() >= 300,
	            "First Name field accept long input!");

	  
	}
	
	@Test(description=" Test UnicodeSupport")
	public void testUnicodeSupport() {
		 LoginPage login = new LoginPage(driver);
		    ContactListPage list = new ContactListPage(driver);
		    AddContactPage add = new AddContactPage(driver);
	        login.open(baseUrl);
	        login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
	        HomePage home = new HomePage(driver);
	        Assert.assertTrue(home.isAt());
	        home.clickAdd();

	    Assert.assertTrue(add.isUnicodeAcceptedInAddress(),
	        "Unicode/Emoji not accepted in address field!");
	}
}
