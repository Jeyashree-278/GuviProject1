package com.contact.pages;


import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.contact.utils.WaitUtils;

public class ContactDetailsPage extends BasePage {

    public ContactDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	

   
    private By deleteButton = By.xpath("//button[text()='Delete Contact']");

    private By editButton = By.id("edit-contact");
    private By phone = By.id("phone");
    private By lastName = By.id("lastName");

    public void clickEdit() {
        driver.findElement(editButton).click();
    }

    public String getPhone() {
        return driver.findElement(phone).getText();
    }

    public String getLastName() {
        return driver.findElement(lastName).getText();
    }
    public void clickDeleteContact()
    
    {
    	WaitUtils waitUtils = new WaitUtils(driver, 10);
    	waitUtils.waitForClickable(deleteButton).click();
    }
    public void deleteContact() {

    	WaitUtils waitUtils = new WaitUtils(driver, 10);
        // Click delete button
        
        // Accept confirmation alert
        Alert alert = waitUtils.waitForAlert();
        alert.accept();
    }
    
    public boolean isDeleteConfirmationDisplayed() {
    	
    	WaitUtils waitUtils = new WaitUtils(driver, 10);
        try {
            waitUtils.waitForAlert();
            return true;     // alert appeared
        } catch (Exception e) {
            return false;    // alert NOT displayed
        }
    }

    
}
