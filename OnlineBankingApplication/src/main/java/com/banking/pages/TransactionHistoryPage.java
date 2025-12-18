package com.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransactionHistoryPage extends BasePage {

	
	  private By fromDate = By.id("fromDate");
	    private By toDate = By.id("toDate");
	    private By findBtn = By.xpath("//input[@value='Find Transactions']");
	    private By rows = By.xpath("//table[@id='transactionTable']//tr[position()>1]");

	    public TransactionHistoryPage(WebDriver driver) {
	        super(driver);
	    }

	    public void filterByDate(String from, String to) {
	        type(fromDate, from);
	        type(toDate, to);
	        click(findBtn);
	    }

	    public int getTransactionCount() {
	        return driver.findElements(rows).size();
	    }
	}
	


