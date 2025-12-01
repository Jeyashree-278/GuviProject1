package com.contact.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.contact.pages.AddContactPage;
import com.contact.pages.ContactDetailsPage;
import com.contact.pages.HomePage;
import com.contact.pages.LoginPage;
import com.contact.utils.ConfigReader;

public class UIandResponsivenessTest extends BaseTest {

	
	@Test(description="testFormFieldAlignment")
	public void testFormFieldAlignment() {

	    LoginPage login = new LoginPage(driver);
	    
	    login.open(baseUrl);
        login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isAt());
        home.clickAdd();
        AddContactPage add = new AddContactPage(driver);
        Assert.assertTrue(add.isFormVerticallyOrdered(),
    	        "Field alignment on Add Contact form is incorrect!");
           
	   
	}
	@Test(description="testDeleteContactShowsSuccessMessage")
	public void testDeleteContactShowsSuccessMessage() throws InterruptedException {

	   
		LoginPage login = new LoginPage(driver);
        login.open(baseUrl);
        login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
        HomePage home = new HomePage(driver);
       // home.clickAdd();
        AddContactPage add = new AddContactPage(driver);
       // String email = "john" + System.currentTimeMillis() + "@mail.com";
        //add.add("John", "Doe", email, "9876543210");
        Thread.sleep(5000);
            // Navigate to existing contact
        driver.findElement(By.xpath("//tr[1]//td[2]")).click();
        ContactDetailsPage details=new ContactDetailsPage(driver);
        details.clickDeleteContact();
        
        // Step 3: verify alert appears
        boolean alertVisible = details.isDeleteConfirmationDisplayed();

        Assert.assertTrue(alertVisible, 
                "Expected confirmation alert, but it did NOT appear");

        System.out.println("Delete confirmation alert displayed successfully");
	}
}
