package com.banking.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.banking.pages.AccountDetailsPage;
import com.banking.pages.AccountOverviewPage;
import com.banking.pages.AlertMessagesPage;
import com.banking.pages.CustomerSupportPage;
import com.banking.pages.LoginPage;
import com.banking.pages.NavigationPage;

public class NavigationTest extends BaseTest{


	

	@BeforeMethod
    public void setup() {
		 LoginPage login = new LoginPage(driver);
	   	 login.open(baseUrl);

      
        AccountOverviewPage overview = new AccountOverviewPage(driver);
        AccountDetailsPage details = new AccountDetailsPage(driver);

        login.login("Diya", "Good");
    }
	@Test(description="navigateThroughTopNavLinks")
	public void navigateThroughTopNavLinks() {

	    NavigationPage nav = new NavigationPage(driver);

	    nav.goHome();
	    Assert.assertTrue(driver.getTitle().contains("ParaBank"),
	            "Home page not loaded");

	    nav.goAccounts();
	    Assert.assertTrue(driver.getPageSource().contains("Accounts Overview"),
	            "Accounts page not loaded");

	    nav.goTransfer();
	    Assert.assertTrue(driver.getPageSource().contains("Transfer Funds"),
	            "Transfer page not loaded");

	    nav.goLoan();
	    Assert.assertTrue(driver.getPageSource().contains("Loan Request"),
	            "Loan page not loaded");

	    nav.goContact();
	    Assert.assertTrue(driver.getPageSource().contains("Customer Care"),
	            "Contact page not loaded");
	}
	@Test(description="verifyLogoRedirectsToHome")
	public void verifyLogoRedirectsToHome() throws InterruptedException {
		Thread.sleep(3000);

	    driver.findElement(By.linkText("Transfer Funds")).click();

	    NavigationPage nav = new NavigationPage(driver);
	    nav.clickLogo();

	    Assert.assertTrue(driver.getTitle().contains("ParaBank"),
	            "Logo did not redirect to Home page");
	}
	@Test(description="validateButtonsVisibilityAndClickability")
	public void validateButtonsVisibilityAndClickability() throws InterruptedException {
		Thread.sleep(3000);
	    driver.findElement(By.linkText("Transfer Funds")).click();

	    WebElement transferBtn =
	        driver.findElement(By.xpath("//input[@value='Transfer']"));

	    Assert.assertTrue(transferBtn.isDisplayed(),
	            "Transfer button not visible");

	    Assert.assertTrue(transferBtn.isEnabled(),
	            "Transfer button not clickable");
	}
	@Test(description="validateContactSuccessPopup")
	public void validateContactSuccessPopup() {

		 driver.findElement(By.linkText("Contact Us")).click();

		    CustomerSupportPage support =
		        new CustomerSupportPage(driver);
		    AlertMessagesPage alert = new AlertMessagesPage(driver);

		    support.fillForm(
		        "Test User",
		        "user@test.com",
		        "9999999999",
		        "Testing customer support"
		    );

		    support.submitForm();	   

	    String successMsg = alert.getSuccessMessage();

	    Assert.assertTrue(successMsg.contains("Customer Care"),
	            "Success popup not displayed");
	}

}