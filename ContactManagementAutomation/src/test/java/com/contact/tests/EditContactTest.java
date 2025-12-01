
package com.contact.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.contact.pages.AddContactPage;
import com.contact.pages.ContactDetailsPage;
import com.contact.pages.EditContactPage;
import com.contact.pages.HomePage;
import com.contact.pages.LoginPage;
import com.contact.utils.ConfigReader;

public class EditContactTest extends BaseTest {
	

@Test
    public void editExistingContact() throws InterruptedException {
	
	LoginPage login = new LoginPage(driver);
    login.open(baseUrl);
    login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
    HomePage home = new HomePage(driver);
    Assert.assertTrue(home.isAt());
   // home.clickAdd();
    AddContactPage add = new AddContactPage(driver);
   // String email = "john" + System.currentTimeMillis() + "@mail.com";
    //add.add("John", "Doe", email, "9876543210");
    Thread.sleep(5000);
        // Navigate to existing contact
        driver.findElement(By.xpath("//tr[1]//td[2]")).click();

        ContactDetailsPage details = new ContactDetailsPage(driver);

        // Click Edit
        details.clickEdit();

        EditContactPage edit = new EditContactPage(driver);
        Thread.sleep(3000);
        edit.setPhone("9876543210");
        Thread.sleep(3000);
        edit.clickSave();
        Thread.sleep(3000);
        // Verify
        String updatedPhone = details.getPhone();
        Assert.assertEquals(updatedPhone, "9876543210");
    }

    @Test
    public void cancelEditShouldNotSave() throws InterruptedException {

    	LoginPage login = new LoginPage(driver);
        login.open(baseUrl);
        login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isAt());
       // home.clickAdd();
        AddContactPage add = new AddContactPage(driver);
       // String email = "john" + System.currentTimeMillis() + "@mail.com";
        //add.add("John", "Doe", email, "9876543210");
        Thread.sleep(5000);
            // Navigate to existing contact
        driver.findElement(By.xpath("//tr[1]//td[2]")).click();

        ContactDetailsPage details = new ContactDetailsPage(driver);
        String originalPhone = details.getPhone();
        details.clickEdit();
        EditContactPage edit = new EditContactPage(driver);
        edit.setPhone("1234567890");
        edit.clickCancel();
        String afterCancel = details.getPhone();
        Assert.assertEquals(afterCancel, originalPhone);
    }

    @Test
    public void validationErrorOnEmptyLastName() throws InterruptedException {

    	LoginPage login = new LoginPage(driver);
        login.open(baseUrl);
        login.login(ConfigReader.get("admin.email"), ConfigReader.get("admin.password"));
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isAt());
       // home.clickAdd();
        AddContactPage add = new AddContactPage(driver);
       // String email = "john" + System.currentTimeMillis() + "@mail.com";
        //add.add("John", "Doe", email, "9876543210");
        Thread.sleep(5000);
            // Navigate to existing contact
        driver.findElement(By.xpath("//tr[1]//td[2]")).click();


        ContactDetailsPage details = new ContactDetailsPage(driver);
        details.clickEdit();

        EditContactPage edit = new EditContactPage(driver);
        Thread.sleep(5000);
        // Clear Last Name
        edit.setLastName("");

        // Try saving
        edit.clickSave();

        // Error
        String error = edit.waitForErrorMessage();
        Assert.assertEquals(error, "Validation failed: lastName: Path `lastName` is required.");
    }
}