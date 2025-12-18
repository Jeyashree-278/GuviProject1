package com.banking.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.banking.pages.AccountDetailsPage;
import com.banking.pages.AccountOverviewPage;
import com.banking.pages.LoanRequestPage;
import com.banking.pages.LoginPage;

public class LoanRequestTest extends BaseTest {
	
	@BeforeMethod
    public void setup() {
		 LoginPage login = new LoginPage(driver);
	   	 login.open(baseUrl);
        AccountOverviewPage overview = new AccountOverviewPage(driver);
        AccountDetailsPage details = new AccountDetailsPage(driver);

        login.login("Diya", "Good");
    }
	@Test(description="submitLoanRequestWithValidDetails")
	public void submitLoanRequestWithValidDetails() {

	    driver.findElement(By.linkText("Request Loan")).click();

	    LoanRequestPage loan = new LoanRequestPage(driver);
	    loan.enterLoanAmount("5000");
	    loan.enterDownPayment("1000");
	    loan.submitLoan();

	    Assert.assertTrue(loan.isLoanApproved(),
	            "Loan request was not approved");
	}
	@Test(description="submitLoanRequestWithMissingInfo")
	public void submitLoanRequestWithMissingInfo() {

	    driver.findElement(By.linkText("Request Loan")).click();

	    LoanRequestPage loan = new LoanRequestPage(driver);
	    loan.submitLoan();  // No data entered

	    Assert.assertTrue(loan.isErrorDisplayed(),
	            "Validation error not displayed for missing fields");
	}
	
	@Test(description="verifyLoanRequestStatusAndResult")
	public void verifyLoanRequestStatusAndResult() {

	    driver.findElement(By.linkText("Request Loan")).click();

	    LoanRequestPage loan = new LoanRequestPage(driver);
	    loan.enterLoanAmount("4000");
	    loan.enterDownPayment("500");
	    loan.submitLoan();

	    Assert.assertTrue(loan.isLoanApproved(),
	            "Loan status/result not displayed");
	}
	@Test(description="submitLoanRequestExceedingLimit")
	public void submitLoanRequestExceedingLimit() {

	    driver.findElement(By.linkText("Request Loan")).click();

	    LoanRequestPage loan = new LoanRequestPage(driver);
	    loan.enterLoanAmount("99999999");
	    loan.enterDownPayment("1");
	    loan.submitLoan();

	    Assert.assertTrue(
	        loan.isErrorDisplayed() || loan.isLoanApproved(),
	        "No response shown for high loan amount"
	    );
	}
	 @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }
}
