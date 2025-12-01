package com.contact.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.contact.utils.WaitUtils;

public class ContactListPage {

    private WebDriver driver;
    private WaitUtils wait;

    // Locators
    private By pageHeader = By.xpath("//h1[contains(text(), 'Contact List')]");
    private By logoutButton = By.id("logout");

    public ContactListPage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtils(driver, 10);
    }

    // Verify user is on Contact List page
    public boolean isAtContactList() {
        return driver.findElements(pageHeader).size() > 0;
    }

    // Logout action
    public void clickLogout() {
        wait.waitForClickable(logoutButton).click();
    }
    
    
}