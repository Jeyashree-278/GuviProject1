package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage extends BasePage {

	private By placeOrderBtn= By.xpath("//button[normalize-space()='Place Order']");
	private By orderModal=By.id("orderModal");
	private By name1=By.id("name");
	private By confirmationText=By.cssSelector(".sweet-alert h2");
	private By orderInfo=By.cssSelector(".sweet-alert p");
    public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	


  
    // Open Place Order modal
    public void openPlaceOrder() {
       
        click(placeOrderBtn);
        wait.forVisibility(orderModal);
        
    }

    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
    	wait.forVisibility(name1);
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("card")).sendKeys(card);
        driver.findElement(By.id("month")).sendKeys(month);
        driver.findElement(By.id("year")).sendKeys(year);
    }

    public void clickPurchase() {
        WebElement purchaseBtn = driver.findElement(By.xpath("//button[text()='Purchase']"));
        purchaseBtn.click();
    }

    // Get confirmation popup text
    public String getConfirmationText() {
        WebElement msg=wait.waitForVisibility(confirmationText);
        
        return msg.getText();
    }

    public String getOrderInfo() {
       WebElement info=wait.waitForVisibility(orderInfo);
        return info.getText();   // contains ID + amount
    }

    public void closeConfirmation() {
        driver.findElement(By.xpath("//button[text()='OK']")).click();
    }
}
