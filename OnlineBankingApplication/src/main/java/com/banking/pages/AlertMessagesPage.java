package com.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertMessagesPage extends BasePage {

    // Common ParaBank alert / message locators
    private By errorMessage = By.cssSelector(".error");
    private By successMessage = By.cssSelector(".title");

    public AlertMessagesPage(WebDriver driver) {
        super(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}