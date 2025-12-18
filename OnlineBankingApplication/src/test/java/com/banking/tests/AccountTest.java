package com.banking.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.banking.pages.AccountDetailsPage;
import com.banking.pages.AccountOverviewPage;
import com.banking.pages.LoginPage;
import com.banking.pages.TransferFundsPage;

public class AccountTest extends BaseTest {

	
	 WebDriver driver;
	    LoginPage login;
	    AccountOverviewPage overview;
	    AccountDetailsPage details;

	    @BeforeMethod
	    public void setup() {
	    	login = new LoginPage(driver);
	    	 login.open(baseUrl);    
	        overview = new AccountOverviewPage(driver);
	        details = new AccountDetailsPage(driver);

	        login.login("Diya", "Good");
	    }

	    @Test(description="viewCheckingAccountSummary")
	    public void viewCheckingAccountSummary() {
	        overview.openFirstAccount();

	        Assert.assertTrue(details.isBalanceDisplayed());
	        Assert.assertTrue(details.isTransactionDisplayed());
	    }

	    @Test(description="viewSavingsAccountSummary")
	    
	    public void viewSavingsAccountSummary() {
	        overview.openFirstAccount();

	        Assert.assertTrue(details.isBalanceDisplayed());
	        Assert.assertTrue(details.isTransactionDisplayed());
	    }
	    @Test(description="validateUpdatedBalanceAfterTransaction")
	    public void validateUpdatedBalanceAfterTransaction() throws Exception {

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
  
	        driver.findElement(By.linkText("Accounts Overview")).click();
	        overview.openFirstAccount();   

	        double balanceAfter = details.getBalance();

	        Assert.assertNotEquals(balanceBefore, balanceAfter,
	                "Balance did not update after transaction");
	    }
	    
	    @Test(description="viewFullTransactionHistory")
	    public void viewFullTransactionHistory() {

	        AccountOverviewPage overview = new AccountOverviewPage(driver);
	        overview.openFirstAccount();

	        AccountDetailsPage details = new AccountDetailsPage(driver);

	        Assert.assertTrue(details.isTransactionDisplayed(),
	                "Transaction history table not displayed");
	    }
	    
	    @Test(description="validateTransactionHistoryColumns")
	    public void validateTransactionHistoryColumns() throws InterruptedException {

	        AccountOverviewPage overview = new AccountOverviewPage(driver);
	        overview.openFirstAccount();
	        Thread.sleep(5000);
	        AccountDetailsPage details = new AccountDetailsPage(driver);
	        Assert.assertTrue(details.validateTransactionColumns(),
	                "Transaction table does not contain Date, Transaction, and Type columns");
	    }
	    @Test(description="validateTransactionListFields")
	    public void validateTransactionListFields() {
	    	 AccountOverviewPage overview = new AccountOverviewPage(driver);
		        overview.openFirstAccount();

	        List<WebElement> rows =
	            driver.findElements(By.xpath("//table//tr[position()>1]"));
	        for (WebElement row : rows) {
	            Assert.assertFalse(row.findElements(By.xpath("./td[1]")).isEmpty()); // Date
	            Assert.assertFalse(row.findElements(By.xpath("./td[2]")).isEmpty()); // Description
	            Assert.assertFalse(row.findElements(By.xpath("./td[3]")).isEmpty()); // Amount
	        }
	    }

	    @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }
}
