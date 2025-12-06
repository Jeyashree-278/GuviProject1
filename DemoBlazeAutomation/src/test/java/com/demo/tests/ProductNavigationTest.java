package com.demo.tests;

import com.demo.pages.*;
import com.demo.utils.ConfigReader;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

	import org.testng.Assert;
	import org.testng.annotations.Test;
	
	

	public class ProductNavigationTest extends BaseTest {
		@Test(description=" Verify product Navigation")
		public void verifyProductNavigation()
		{
			 String productName = "Sony vaio i5";
		CartPage cart = new CartPage(driver);
	    LoginPage login = new LoginPage(driver);
	    login.open(baseUrl);
		login.login(ConfigReader.get("admin.username"), ConfigReader.get("admin.password"));
		login.waitForLoginModalToClose();
		ProductPage product = new ProductPage(driver);
		ProductDetailsPage details=new ProductDetailsPage(driver);
		product.selectProduct("Laptops", productName);
	    
	        
	        String actualProductName = details.getProductName();
	        Assert.assertEquals(actualProductName, productName, "Product name mismatch!");

	        String price = details.getProductPrice();
	        Assert.assertTrue(price.contains("$"), "Product price not displayed correctly!");
	        String alertMsg = product.addToCart();
	        System.out.print("Product navigation + add to cart flow verified successfully.");
	    }
		
		   @Test(description="Verify Home Navigation")
		    public void verifyHomeNavigation() {

		        HomePage home = new HomePage(driver);
		       
		        LoginPage login = new LoginPage(driver);
			    login.open(baseUrl);
				login.login(ConfigReader.get("ins"), ConfigReader.get("ins"));
				login.waitForLoginModalToClose();
		        // Click Home from navbar
		        home.clickHome();
		        Assert.assertTrue(driver.getCurrentUrl().contains("index"), "Home page not opened!");
		               
		      
		    }
	
		   @Test(description="verifyNavbarNavigation")
		    public void verifyNavbarNavigation() {

			   HomePage home = new HomePage(driver);
		       
		        LoginPage login = new LoginPage(driver);
			    login.open(baseUrl);
				login.login(ConfigReader.get("ins"), ConfigReader.get("ins"));
				login.waitForLoginModalToClose();
		        // Click Home from navbar
		        home.clickHome();
		        Assert.assertFalse(home.isProductListVisible(), "Home page not displayed!");
		        // 2. Cart
		        home.clickCart();
		        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Cart page not opened!");
		       
		        
		    }
		}
		
	