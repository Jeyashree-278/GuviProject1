package com.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountOverviewPage extends BasePage 

	
	{

	    private By accountTable = By.id("accountTable");
	    private By firstAccountLink = By.xpath("//table[@id='accountTable']//a");

	    public AccountOverviewPage(WebDriver driver) {
	        super(driver);
	    }

	    public void openFirstAccount() {
	        click(firstAccountLink);
	    }

	    public boolean isAccountSummaryDisplayed() {
	        return driver.findElement(accountTable).isDisplayed();
	    }
	}