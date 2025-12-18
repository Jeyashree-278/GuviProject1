package com.banking.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.banking.pages.AccountDetailsPage;
import com.banking.pages.AccountOverviewPage;
import com.banking.pages.LoginPage;
import com.banking.pages.TransferFundsPage;

public class FundTransferTest extends BaseTest {
	
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
	public void transferFundsSuccessfully() throws Exception {
		Thread.sleep(3000);

	    driver.findElement(By.linkText("Transfer Funds")).click();
	    Thread.sleep(3000);
        TransferFundsPage transfer = new TransferFundsPage(driver);
        transfer.selectDifferentAccounts();
	    transfer.transferAmount("100");

	    Assert.assertTrue(transfer.isSuccess(),
	            "Transfer was not successful");
	}
	    @Test
	    
	    public void transferWithInsufficientBalance() throws Exception {

	        driver.findElement(By.linkText("Transfer Funds")).click();

	        TransferFundsPage transfer = new TransferFundsPage(driver);
	        Thread.sleep(3000);
	       
	        transfer.selectDifferentAccounts();
	        transfer.transferAmount("9999999");

	        Assert.assertTrue(transfer.isErrorDisplayed(),
	                "Error not displayed for insufficient balance");
	    
	}
	    @Test
	    public void transferWithEmptyFields() throws InterruptedException {

	    	Thread.sleep(3000);
	        driver.findElement(By.linkText("Transfer Funds")).click();
	        Thread.sleep(3000);
	        TransferFundsPage transfer = new TransferFundsPage(driver);
	        driver.findElement(By.xpath("//input[@value='Transfer']")).click();

	        Assert.assertTrue(transfer.isErrorDisplayed(),
	                "Validation error not shown for empty fields");
	    }
	    @Test
	    public void transferLargeOrDecimalAmount() throws Exception {

	        driver.findElement(By.linkText("Transfer Funds")).click();

	        TransferFundsPage transfer = new TransferFundsPage(driver);
	        Thread.sleep(3000);
		       
	        transfer.selectDifferentAccounts();
	        transfer.transferAmount("9.75");

	        Assert.assertTrue(transfer.isSuccess(),
	                "Transfer failed for decimal amount");
	    }
	    
	    @Test
	    public void transferToSameAccount() throws InterruptedException {
	    	Thread.sleep(3000);

	        driver.findElement(By.linkText("Transfer Funds")).click();
	        Thread.sleep(3000);
	        TransferFundsPage transfer = new TransferFundsPage(driver);
	        transfer.selectSameAccount();
	        transfer.transferAmount("50");
	        Assert.assertTrue(
	            transfer.isErrorDisplayed() || transfer.isSuccess(),
	            "Neither error nor success detected for same-account transfer"
	        );
	    }
	    @Test
	    public void validateTransferConfirmationAndUpdatedBalance() throws Exception {

	    	AccountOverviewPage overview = new AccountOverviewPage(driver);
	        overview.openFirstAccount();

	        AccountDetailsPage details = new AccountDetailsPage(driver);
	        double balanceBefore = details.getBalance();

	        // Go to Transfer Funds
	        driver.findElement(By.linkText("Transfer Funds")).click();
	        Thread.sleep(3000);
	        TransferFundsPage transfer = new TransferFundsPage(driver);
	        transfer.selectDifferentAccounts();
	        transfer.transferAmount("100");
	        Assert.assertTrue(transfer.isSuccess(),
	                "Transfer failed for decimal amount");
	        driver.findElement(By.linkText("Accounts Overview")).click();
	        overview.openFirstAccount();  

	        double balanceAfter = details.getBalance();

	        Assert.assertNotEquals(balanceBefore, balanceAfter,
	                "Balance did not update after transaction");
	    }
	    
	    @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }
}
