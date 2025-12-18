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
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        LoginPage login = new LoginPage(driver);
        AccountOverviewPage overview = new AccountOverviewPage(driver);
        AccountDetailsPage details = new AccountDetailsPage(driver);

        login.login("Diya", "Good");
    }

	@Test
	public void openCustomerSupportForm() {

	    driver.findElement(By.linkText("Contact Us")).click();

	    Assert.assertTrue(
	        driver.getTitle().contains("Customer Care"),
	        "Customer support page not opened"
	    );
	}
	@Test
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
	@Test
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
	@Test
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
