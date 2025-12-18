package com.banking.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.banking.pages.AccountDetailsPage;
import com.banking.pages.AccountOverviewPage;
import com.banking.pages.CustomerSupportPage;
import com.banking.pages.LoginPage;

public class CustomerSupportTest extends BaseTest {
	
	@BeforeMethod
    public void setup() {
		  LoginPage login = new LoginPage(driver);
		login = new LoginPage(driver);
   	 login.open(baseUrl);

      
        AccountOverviewPage overview = new AccountOverviewPage(driver);
        AccountDetailsPage details = new AccountDetailsPage(driver);

        login.login("Diya", "Good");
    }

	@Test(description="Open customer support form")
	public void openCustomerSupportForm() {

	    driver.findElement(By.linkText("Contact Us")).click();

	    Assert.assertTrue(
	        driver.getTitle().contains("Customer Care"),
	        "Customer support page not opened"
	    );
	}
	@Test(description="submitSupportFormWithValidDetails")
	public void submitSupportFormWithValidDetails() {

	    driver.findElement(By.linkText("Contact Us")).click();

	    CustomerSupportPage support =
	        new CustomerSupportPage(driver);

	    support.fillForm(
	        "Jeyashree",
	        "test@example.com",
	        "9876543210",
	        "Need help with my account"
	    );

	    support.submitForm();

	    Assert.assertTrue(
	        support.isSuccessMessageDisplayed(),
	        "Success message not shown after form submission"
	    );
	}
	@Test(description="submitSupportFormWithEmptyFields")
	public void submitSupportFormWithEmptyFields() {

	    driver.findElement(By.linkText("Contact Us")).click();

	    CustomerSupportPage support =
	        new CustomerSupportPage(driver);

	    support.submitForm();

	    Assert.assertTrue(
	        support.isErrorDisplayed(),
	        "Form submitted successfully with empty fields"
	    );
	}
	@Test(description="verifySupportFormSuccessMessage")
	public void verifySupportFormSuccessMessage() {

	    driver.findElement(By.linkText("Contact Us")).click();

	    CustomerSupportPage support =
	        new CustomerSupportPage(driver);

	    support.fillForm(
	        "Test User",
	        "user@test.com",
	        "9999999999",
	        "Testing customer support"
	    );

	    support.submitForm();

	    Assert.assertTrue(
	        support.isSuccessMessageDisplayed(),
	        "Acknowledgement / confirmation message not displayed"
	    );
	}





}
