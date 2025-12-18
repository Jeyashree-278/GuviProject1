package com.banking.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountDetailsPage extends BasePage {
	

    private By transactionTable = By.id("transactionTable");
    private By balance =
            By.xpath("//td[contains(text(),'Balance:')]/following-sibling::td");

        private By accountDetailsHeader =
            By.xpath("//h1[contains(text(),'Account Details')]");

    public AccountDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isBalanceDisplayed() {
        return driver.findElement(balance).isDisplayed();
    }
    private By balanceValue =
            By.xpath("//td[text()='Balance']/following-sibling::td");

    public boolean isTransactionDisplayed() {
        return driver.findElement(transactionTable).isDisplayed();
    }
    public double getBalance() throws InterruptedException {
    	 Thread.sleep(2000);
        String bal = getText(balance).replace("$", "").trim();
        return Double.parseDouble(bal);
    }
    public boolean validateTransactionColumns() {

        By headers = By.xpath("//table[@id='transactionTable']//th");
        List<WebElement> columns = driver.findElements(headers);

        boolean hasDate = false;
        boolean hasAmount = false;
        boolean hasType = false;

        for (WebElement col : columns) {
            String text = col.getText();
            if (text.equalsIgnoreCase("Date")) hasDate = true;
            if (text.equalsIgnoreCase("Transaction")) hasAmount = true;
            if (text.equalsIgnoreCase("Debit")) hasType = true;
        }

        return hasDate && hasAmount && hasType;
    }


}
