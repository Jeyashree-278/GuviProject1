package com.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerSupportPage extends BasePage {

    // Locators
    private By name = By.id("name");
    private By email = By.id("email");
    private By phone = By.id("phone");
    private By message = By.id("message");
    private By sendBtn = By.xpath("//input[@value='Send to Customer Care']");

    private By successMsg =
        By.xpath("//h1[contains(text(),'Customer Care')]");

    private By errorMsg =
        By.cssSelector(".error");

    public CustomerSupportPage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(String userName, String userEmail,
                         String userPhone, String userMessage) {
        type(name, userName);
        type(email, userEmail);
        type(phone, userPhone);
        type(message, userMessage);
    }

    public void submitForm() {
        click(sendBtn);
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(successMsg).size() > 0;
    }

    public boolean isErrorDisplayed() {
        return driver.findElements(errorMsg).size() > 0;
    }
}
