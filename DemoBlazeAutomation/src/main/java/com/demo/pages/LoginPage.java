package com.demo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

		
		private By loginLink = By.id("login2");
		private By usernameInput = By.id("loginusername");
		private By passwordInput = By.id("loginpassword");
		private By loginBtn = By.cssSelector("#logInModal .btn-primary");


		public LoginPage(WebDriver driver) { 
			super(driver); 
			}

		public void waitForLoginModalToClose() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("logInModal")));
		}
		
		public void open(String baseUrl) 
		{ 
			driver.get(baseUrl); 
			
		}
		public void openLoginModal(){ driver.findElement(loginLink).click(); }
		
		public boolean isPasswordMasked() {
		    return driver.findElement(passwordInput).getAttribute("type").equals("password");
		}
		public void login(String user, String pass){
		openLoginModal();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
		type(usernameInput,user);
		type(passwordInput,pass);
		click(loginBtn);
		}
		}


