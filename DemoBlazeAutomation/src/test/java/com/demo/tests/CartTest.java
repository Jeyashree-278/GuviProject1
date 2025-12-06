package com.demo.tests;

import com.demo.utils.ConfigReader;
import com.demo.pages.CartPage;
import com.demo.pages.HomePage;
import com.demo.pages.LoginPage;
import com.demo.pages.ProductPage;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest{

    @Test(description="Add product to cart")
    public void addProductToCart() {
        LoginPage login = new LoginPage(driver);
        login.open(baseUrl);
		login.login(ConfigReader.get("admin.username"), ConfigReader.get("admin.password"));
		login.waitForLoginModalToClose();
		 ProductPage product = new ProductPage(driver);
		 product.selectProduct("Laptops", "Sony vaio i5");
        String alertMsg = product.addToCart();
        Assert.assertEquals(alertMsg, "Product added.");
    }

    @Test(description=" View cart with added Products")
    public void viewCartWithAddedProducts() throws InterruptedException {
        CartPage cart = new CartPage(driver);
        LoginPage login = new LoginPage(driver);
        login.open(baseUrl);
		login.login(ConfigReader.get("admin.username"), ConfigReader.get("admin.password"));
		login.waitForLoginModalToClose();
		 ProductPage product = new ProductPage(driver);
		 product.selectProduct("Laptops", "Sony vaio i5");
        String alertMsg = product.addToCart();

        cart.openCart();
        Assert.assertTrue(cart.isProductPresent("Sony vaio i5"));
    }

    @Test(description=" Remove product from Cart")
    public void removeProductFromCart() throws InterruptedException {
        CartPage cart = new CartPage(driver);
        LoginPage login = new LoginPage(driver);
        login.open(baseUrl);
		login.login("Sathyadev","Sathyadev");
		login.waitForLoginModalToClose();
		 ProductPage product = new ProductPage(driver);
		 product.selectProduct("Laptops", "Sony vaio i5");
        String alertMsg = product.addToCart();
        cart.openCart();
        cart.deleteProduct();
        Assert.assertFalse(cart.isProductPresent("Sony vaio i5"));
    }

    @Test(description=" add multiple products")
    public void addMultipleProducts() throws InterruptedException {
        ProductPage product = new ProductPage(driver);
        CartPage cart = new CartPage(driver);

        LoginPage login = new LoginPage(driver);
        login.open(baseUrl);
		login.login("Sathyadev", "Sathyadev");
		login.waitForLoginModalToClose();
		 ProductPage product1 = new ProductPage(driver);
		 product1.selectProduct("Laptops", "Sony vaio i5");
        String alertMsg = product1.addToCart();
        HomePage home=new HomePage(driver);
        home.clickHome();
        product1.selectProduct("Phones", "Samsung galaxy s6");
        String alertMsg1 = product1.addToCart();
        cart.openCart();  
        Assert.assertTrue(cart.isProductPresent("Samsung galaxy s6"));
        Assert.assertTrue(cart.isProductPresent("Sony vaio i5"));
    }
}
