package com.contact.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.contact.pages.AddContactPage;
import com.contact.pages.HomePage;
import com.contact.pages.LoginPage;
import com.contact.utils.ConfigReader;

public class AddContactTest  extends BaseTest {

    @Test(description = "Verify adding contact with all valid details")
    public void addContactWithValidDetails() {
        LoginPage login = new LoginPage(driver);
        login.open(baseUrl);
        login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isAt());
        home.clickAdd();
        AddContactPage add = new AddContactPage(driver);
        String email = "john" + System.currentTimeMillis() + "@mail.com";
        add.add("John", "Doe", email, "9876543210");
        Assert.assertTrue(home.isAt(), "After adding contact should return to contact list");    
        
    }
    @Test(description = "Verify adding contact with missing required fields")
	public void addContactwithMissingFields() {
	    LoginPage login = new LoginPage(driver);
	    login.open(baseUrl);
	    login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isAt());
        home.clickAdd();
	    
	    AddContactPage add = new AddContactPage(driver);
        String email = "john" + System.currentTimeMillis() + "@mail.com";
        add.add("", "", email, "9876543210");
        String msg = add.waitForErrorMessage();
	    Assert.assertEquals(msg, "Contact validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required.");
}
    @Test(description = "Verify phone field accepts only numeric input")
   	public void verifyPhoneFieldValidation() {
   	    LoginPage login = new LoginPage(driver);
   	    login.open(baseUrl);
   	    login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
           HomePage home = new HomePage(driver);
           Assert.assertTrue(home.isAt());
           home.clickAdd();
   	    
   	    AddContactPage add = new AddContactPage(driver);
           String email = "john" + System.currentTimeMillis() + "@mail.com";
           add.add("john", "joe", email, "9876543jjk0");
           String msg = add.waitForErrorMessage();
   	    Assert.assertEquals(msg, "Contact validation failed: phone: Phone number is invalid");
   }
    @Test(description = "Verify adding duplicate contact details")
   	public void validateDuplicateContactDetails() {
   	    LoginPage login = new LoginPage(driver);
   	    login.open(baseUrl);
   	    login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
           HomePage home = new HomePage(driver);
           Assert.assertTrue(home.isAt());
           home.clickAdd();
   	    AddContactPage add = new AddContactPage(driver);
           String email = "duplicatecontact@gmail.com";
           add.add("Duplicate", "Contact", email, "1234567890");
           Assert.assertTrue(home.isAt(), "After adding contact should return to contact list");
   }
    
    @Test(description = "Verify form resets after contact is added")
   	public void validateFormReset() {
   	    LoginPage login = new LoginPage(driver);
   	    login.open(baseUrl);
   	    login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
           HomePage home = new HomePage(driver);
           Assert.assertTrue(home.isAt());
           home.clickAdd();
           AddContactPage add = new AddContactPage(driver);
           add.isFormCleared();
}
}

