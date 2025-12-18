package com.banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationPage extends BasePage {

    private By home = By.linkText("Home");
    private By accounts = By.linkText("Accounts Overview");
    private By transfer = By.linkText("Transfer Funds");
    private By loan = By.linkText("Request Loan");
    private By contact = By.linkText("Contact Us");

    private By logo = By.xpath("//img[@alt='ParaBank']");

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    public void goHome() {
        click(home);
    }

    public void goAccounts() {
        click(accounts);
    }

    public void goTransfer() {
        click(transfer);
    }

    public void goLoan() {
        click(loan);
    }

    public void goContact() {
        click(contact);
    }

    public void clickLogo() {
        click(logo);
    }
}
