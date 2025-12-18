package com.banking.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.pages.HomePage;
import com.banking.pages.LoginPage;
import com.banking.pages.SupportPage;

public class SupportTest extends BaseTest {
	

	 @Test(description="testOpenSupportForm")
	    public void testOpenSupportForm() throws InterruptedException {
		 LoginPage login = new LoginPage(driver);
	        login.open(baseUrl);
	        Thread.sleep(5000);
	        HomePage home = new HomePage(driver);
	        home.clickContactUs();
	        // Optionally, verify URL or page title to confirm navigation
	        Assert.assertTrue(driver.getCurrentUrl().contains("contact"),
	                "Contact Us / Support form page did not open.");
	    }

	    @Test(description="testSubmitFormWithValidData")
	    public void testSubmitFormWithValidData() {
	    	LoginPage login = new LoginPage(driver);
	        login.open(baseUrl);
	        HomePage home = new HomePage(driver);
	        home.clickContactUs();

	        SupportPage support = new SupportPage(driver);
	        support.fillForm("Alice Tester", "alice@example.com", "Need Help", "This is a test message.");
	        support.submitForm();

	        String successMsg = support.getSuccessMessage();
	        Assert.assertTrue(successMsg.contains("Thank you") || successMsg.contains("success"),
	                "Success message not displayed as expected. Actual: " + successMsg);
	    }

	    @Test(description="testSubmitFormWithEmptyFields")
	    public void testSubmitFormWithEmptyFields() {
	    	LoginPage login = new LoginPage(driver);
	        login.open(baseUrl);
	        HomePage home = new HomePage(driver);
	        home.clickContactUs();

	        SupportPage support = new SupportPage(driver);
	        support.submitForm();

	        String errMsg = support.getErrorMessage();
	        Assert.assertTrue(errMsg != null && !errMsg.isEmpty(),
	                "Validation error message should appear when submitting empty form.");
	    }
}
