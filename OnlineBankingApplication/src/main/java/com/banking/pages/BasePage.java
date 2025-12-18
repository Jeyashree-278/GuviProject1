package com.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.banking.utils.WaitUtils;

public class BasePage {

	
	
	    protected WebDriver driver;
	    protected WaitUtils wait;

	    public BasePage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WaitUtils(driver, 10);
	    }

	    protected void click(By locator) {
	        wait.forClickable(locator);
	        driver.findElement(locator).click();
	    }

	    protected void type(By locator, String text) {
	        wait.forVisibility(locator);
	        driver.findElement(locator).clear();
	        driver.findElement(locator).sendKeys(text);
	    }

	    protected String getText(By locator) {
	        wait.forVisibility(locator);
	        return driver.findElement(locator).getText();
	    }

	    protected String getValue(By locator) {
	        wait.forVisibility(locator);
	        return driver.findElement(locator).getAttribute("value");
	    }
	}

