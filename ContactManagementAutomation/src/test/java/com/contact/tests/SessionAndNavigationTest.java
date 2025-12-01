package com.contact.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.contact.pages.ContactListPage;
import com.contact.pages.HomePage;
import com.contact.pages.LoginPage;
import com.contact.utils.ConfigReader;

public class SessionAndNavigationTest extends BaseTest {
	
	@Test(description="Verify logout redirects to login page")
	public void verifyLogoutRedirectsToLoginPage() throws InterruptedException {
	    LoginPage login = new LoginPage(driver);
	    ContactListPage list = new ContactListPage(driver);
	    login.open(baseUrl);
		login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.isAt(), "User should see contact list after login");
	    list.clickLogout();
	    Thread.sleep(3000);
	    Assert.assertTrue(login.isAtLoginPage(),
	            " User is not redirected to login page after logout.");

	    System.out.println("Logout redirects to login page.");
	}

	@Test(description="Verify login state on refresh")
	public void verifyLoginStateOnRefresh() {
	    LoginPage login = new LoginPage(driver);
	    ContactListPage list = new ContactListPage(driver);
	    login.open(baseUrl);
		login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.isAt(), "User should see contact list after login");
	    driver.navigate().refresh();
	    Assert.assertTrue(home.isAt(),
	            "User lost session after refresh!");
	    System.out.println("User remains logged in after refresh.");
	}
	@Test(description=" VerifyLoginIsRequiredToAccessContactList")
	public void verifyLoginIsRequiredToAccessContactList() {
	driver.get("https://thinking-tester-contact-list.herokuapp.com/contactList");
	ContactListPage list = new ContactListPage(driver);
	Assert.assertTrue(list.isAtContactList(), "User can see Contactlist page");

	
	}
	@Test(description="verifyBackButtonDoesNotRestorePostLogoutSession")
	public void verifyBackButtonDoesNotRestorePostLogoutSession() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
	    ContactListPage list = new ContactListPage(driver);
	    login.open(baseUrl);
		login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.isAt(), "User should see contact list after login");
	    list.clickLogout();
	    Thread.sleep(3000);
	    Assert.assertTrue(login.isAtLoginPage(),
	            " User is not redirected to login page after logout.");
	    driver.navigate().back();

	    // Expect STILL on login page
	    Assert.assertTrue(login.isAtLoginPage(),
	            "User accessed contact list using BACK after logout!");

	    System.out.println("Back button does not restore session after logout.");
	}
	
}
