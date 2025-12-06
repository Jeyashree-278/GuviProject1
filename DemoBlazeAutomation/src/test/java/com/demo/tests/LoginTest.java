package com.demo.tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.utils.ConfigReader;
import com.demo.pages.HomePage;
import com.demo.pages.LoginPage;

public class LoginTest extends BaseTest {
	
	 
		@Test(description="Verify login with valid credentials")
		public void loginWithValidCredentials(){
		LoginPage lp = new LoginPage(driver);
		lp.open(baseUrl);
		lp.login(ConfigReader.get("admin.username"), ConfigReader.get("admin.password"));
		// assert logged-in UI state
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.isAt(), "User Logged in successfully");
		}


		@Test(description="Verify login with invalid password")
		public void loginWithInvalidPassword(){
		LoginPage lp = new LoginPage(driver);
		lp.open(baseUrl);
		lp.login("testuser1", "wrongpass");
		// demoblaze uses alert for login errors
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains("Wrong" ) || alert.getText().length() > 0);
		alert.accept();
		}
		
		@Test(description="Verify login with empty fields")
		public void loginWithEmptyFields(){
		LoginPage lp = new LoginPage(driver);
		lp.open(baseUrl);
		lp.login("", "");
		// demoblaze uses alert for login errors
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains("Please fill out Username and Password." ) || alert.getText().length() > 0);
		alert.accept();
		}
		
		@Test(description = "Verify that password field masks user input")
	    public void verifyPasswordMasking() {
	        LoginPage login = new LoginPage(driver);
	        login.open(baseUrl);
	        Assert.assertTrue(login.isPasswordMasked(), "Password should be masked!");
	 }
}

