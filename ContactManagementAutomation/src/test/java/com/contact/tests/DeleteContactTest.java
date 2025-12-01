package com.contact.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.contact.pages.AddContactPage;
import com.contact.pages.ContactDetailsPage;
import com.contact.pages.HomePage;
import com.contact.pages.LoginPage;
import com.contact.utils.ConfigReader;

public class DeleteContactTest extends BaseTest 
{
	@Test(description="Verify deleting a contact")
	    
	public void deleteContactDetails() throws InterruptedException {

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
	        details.deleteContact();



}
	
	

	    @Test(description="verify Delete Confirmation Alert")
	    public void verifyDeleteConfirmationAlert() throws InterruptedException {

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