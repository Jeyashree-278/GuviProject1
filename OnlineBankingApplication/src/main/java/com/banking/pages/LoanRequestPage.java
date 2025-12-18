package com.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoanRequestPage extends BasePage {
	 

	    private By loanAmount = By.id("amount");
	    private By downPayment = By.id("downPayment");
	    private By applyBtn = By.xpath("//input[@value='Apply Now']");
	    private By successHeader = By.xpath("//h1[contains(text(),'Loan Request Processed')]");
	    private By errorMsg = By.cssSelector(".error");

	    public LoanRequestPage(WebDriver driver) {
	        super(driver);
	    }

	    public void enterLoanAmount(String amount) {
	        type(loanAmount, amount);
	    }

	    public void enterDownPayment(String payment) {
	        type(downPayment, payment);
	    }

	    public void submitLoan() {
	        click(applyBtn);
	    }

	    public boolean isLoanApproved() {
	        return driver.findElements(successHeader).size() > 0;
	    }

	    public boolean isErrorDisplayed() {
	        return driver.findElements(errorMsg).size() > 0;
	    }

}
