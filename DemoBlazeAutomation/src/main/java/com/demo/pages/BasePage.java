package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.demo.utils.WaitUtils;



public class BasePage {
	
	protected WebDriver driver;
	protected WaitUtils wait;


	public BasePage(WebDriver driver) {
	this.driver = driver;
	int w = Integer.parseInt(com.demo.utils.ConfigReader.get("implicit.wait") == null ? "10" : com.demo.utils.ConfigReader.get("implicit.wait"));
	this.wait = new WaitUtils(driver, w);
	}
	
		// TODO Auto-generated constructor stub
	
	protected String getValue(By locator) {
	    return driver.findElement(locator).getAttribute("value");
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

}

