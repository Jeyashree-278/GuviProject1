package com.demo.pages;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By phonesCategory = By.linkText("Phones");
    private By laptopsCategory = By.linkText("Laptops");
    private By monitorsCategory = By.linkText("Monitors");
   // private By addToCartBtn = By.linkText("Add to cart");
    private By addToCartButton = By.xpath("//a[contains(text(), 'Add to cart')]");

    public void openCategory(String category) {
        switch (category.toLowerCase()) {
            case "phones":  driver.findElement(phonesCategory).click(); break;
            case "laptops": driver.findElement(laptopsCategory).click(); break;
            case "monitors":driver.findElement(monitorsCategory).click(); break;
        }
    }

    public void openProductByName(String productName) {
        driver.findElement(By.linkText(productName)).click();
    }

    
    public void selectProduct(String category, String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click category
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='list-group-item' and text()='" + category + "']"))).click();

        // Wait for products to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h4[@class='card-title']/a")));

        // Click the product
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText(productName))).click();
    }
    
    public String addToCart() {
        
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for Add to Cart button to appear on product page
        WebElement btn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(addToCartButton)
                        
                );

        // Scroll into view
      //  ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        btn.click();
        try {
        	
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String msg = alert.getText();
            alert.accept();
            return msg;

        } catch (Exception e) {
            System.out.println("❌ ALERT DID NOT APPEAR");
            return "NO_ALERT";
        }
        // Wait until clickable
        
      
}
}