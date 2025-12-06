package com.demo.tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.pages.CartPage;
import com.demo.pages.LoginPage;
import com.demo.pages.OrderPage;
import com.demo.pages.ProductPage;
import com.demo.utils.ConfigReader;

public class OrderTest extends BaseTest {

	@Test(description="place order with valid details")
	public void placeOrderValidDetails() throws InterruptedException {
		CartPage cart = new CartPage(driver);
	    LoginPage login = new LoginPage(driver);
	    login.open(baseUrl);
		login.login(ConfigReader.get("admin.username"), ConfigReader.get("admin.password"));
		login.waitForLoginModalToClose();
		ProductPage product = new ProductPage(driver);
		product.selectProduct("Laptops", "Sony vaio i5");
	    String alertMsg = product.addToCart();
	    cart.openCart();
	    OrderPage order = new OrderPage(driver);
	    order.openPlaceOrder();

	    order.fillOrderForm(
	            "John Doe", "India", "Chennai", 
	            "123456789", "12", "2025"
	    );

	    order.clickPurchase();

	    String msg = order.getConfirmationText();
	    Assert.assertEquals(msg, "Thank you for your purchase!");

	    String info = order.getOrderInfo();
	    System.out.println(info);

	    Assert.assertTrue(info.contains("Id"));
	    Assert.assertTrue(info.contains("Amount"));
	    order.closeConfirmation();
	}
	
	@Test(description="Place order empty form")
	public void placeOrderEmptyForm() throws InterruptedException {
		CartPage cart = new CartPage(driver);
	    LoginPage login = new LoginPage(driver);
	    login.open(baseUrl);
		login.login(ConfigReader.get("admin.username"), ConfigReader.get("admin.password"));
		login.waitForLoginModalToClose();
		ProductPage product = new ProductPage(driver);
		product.selectProduct("Laptops", "Sony vaio i5");
	    String alertMsg = product.addToCart();
	    cart.openCart();
	    OrderPage order = new OrderPage(driver);
	    order.openPlaceOrder();
	    order.clickPurchase();

	    // Handle required-field alert
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	    String alertMsg1 = alert.getText();
	    alert.accept();
	    Assert.assertTrue(alertMsg1.contains("Please fill out"));
	}
	@Test(description="Verify order confirmation popup")
	public void verifyOrderConfirmationPopup() throws InterruptedException {
		CartPage cart = new CartPage(driver);
	    LoginPage login = new LoginPage(driver);
	    login.open(baseUrl);
		login.login(ConfigReader.get("admin.username"), ConfigReader.get("admin.password"));
		login.waitForLoginModalToClose();
		ProductPage product = new ProductPage(driver);
		product.selectProduct("Laptops", "Sony vaio i5");
	    String alertMsg = product.addToCart();
	    cart.openCart();
	    OrderPage order = new OrderPage(driver);
	    order.openPlaceOrder();

	    order.fillOrderForm("Mike", "USA", "NYC", "5555", "11", "2026");
	    order.clickPurchase();

	    String title = order.getConfirmationText();
	    Assert.assertEquals(title, "Thank you for your purchase!");

	    String info = order.getOrderInfo();
	    Assert.assertTrue(info.contains("Id"));
	    Assert.assertTrue(info.contains("Amount"));
	}


}
