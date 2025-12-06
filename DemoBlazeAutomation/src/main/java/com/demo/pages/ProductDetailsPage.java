package com.demo.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

    private By addToCartBtn = By.xpath("//a[normalize-space()='Add to cart']");
    private By productPrice = By.cssSelector(".price-container");
    private By productName = By.cssSelector(".name");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    // Get product title
    public String getProductName() {
        return getText(productName);
    }

    // Get price (ex: "$790 *includes tax")
    public String getProductPrice() {
        return getText(productPrice);
    }

    // Click Add to Cart
    public void clickAddToCart() {
        click(addToCartBtn);
        waitForAlert();   // Fix for NoAlertPresentException
    }

    // Handle alert after clicking Add to Cart
    public void waitForAlert() {
        try {
            wait.waitForAlert();
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            System.out.println("Alert not found after Add to Cart action");
        }
    }
}