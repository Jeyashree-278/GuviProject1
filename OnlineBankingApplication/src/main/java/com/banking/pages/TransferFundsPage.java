package com.banking.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage extends BasePage {
	private By amountField = By.id("amount");
	private By fromAccount = By.id("fromAccountId");
    private By toAccount = By.id("toAccountId");
    private By transferBtn = By.xpath("//input[@value='Transfer']");
    private By successMsg = By.xpath("//h1[normalize-space()='Transfer Complete!']");
    private By errorMsg = By.cssSelector(".error");


    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }
    
    public void selectDifferentAccounts() throws Exception {

        Select from = new Select(driver.findElement(fromAccount));
        Select to = new Select(driver.findElement(toAccount));

        List<WebElement> fromOptions = from.getOptions();
        List<WebElement> toOptions = to.getOptions();

        if (fromOptions.size() < 1 || toOptions.size() < 1) {
            throw new Exception("Not enough accounts to select different accounts");
        }

        // Select first real account (skip placeholder)
        from.selectByIndex(0);

        // Select a different account
        to.selectByIndex(1);
    }
    public void selectSameAccount() {
        Select from = new Select(driver.findElement(fromAccount));
        Select to = new Select(driver.findElement(toAccount));
        String selectedValue =
                from.getFirstSelectedOption().getText();

            to.selectByVisibleText(selectedValue);
    }
    public void transferAmount(String amount) throws InterruptedException {
        type(amountField, amount);
        Thread.sleep(2000);
        click(transferBtn);
    }

    public boolean isSuccess() throws InterruptedException {
    	Thread.sleep(2000);
        return driver.findElement(successMsg).isDisplayed();
    }

    public boolean isErrorDisplayed() {
        return driver.findElements(errorMsg).size() > 0;
    }

}
