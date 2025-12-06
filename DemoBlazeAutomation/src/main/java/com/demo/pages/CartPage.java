package com.demo.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {


    public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By cartBtn = By.id("cartur");
    private By cartRows = By.xpath("//tr/td[2]");
    private By deleteLink = By.linkText("Delete");

    public void openCart() throws InterruptedException {
        driver.findElement(cartBtn).click();
        Thread.sleep(9000);
        try { Thread.sleep(2000);} catch(Exception e){}
    }

    public boolean isProductPresent(String product) {
        List<WebElement> products = driver.findElements(cartRows);
        for (WebElement p : products) {
            if (p.getText().equalsIgnoreCase(product)) {
                return true;
            }
        }
        return false;
    }

    public void deleteProduct() throws InterruptedException {
    	Thread.sleep(9000);
        driver.findElement(deleteLink).click();
        try { Thread.sleep(2000);} catch(Exception e){}
    }
}