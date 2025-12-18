package com.banking.pages;

import org.openqa.selenium.By;
import com.banking.pages.*;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
	
	 public LoginPage(WebDriver driver) {
	        super(driver);
	    }

	    private By usernameField = By.name("username");
	    private By passwordField = By.name("password");
	    private By loginBtn = By.xpath("//input[@value='Log In']");
	    private By errorMsg = By.cssSelector("#rightPanel .error");

	    private By passwordMaskCheck = By.name("password");
	    private By rememberMe = By.name("rememberMe");

	    public void open(String url) {
	        driver.get(url);
	    }

	    public void login(String username, String password) {
	        type(usernameField, username);
	        type(passwordField, password);
	        click(loginBtn);
	        
	    }

	    public String getErrorMessage() {
	        return getText(errorMsg);
	    }

	    public boolean isPasswordMasked() {
	        return driver.findElement(passwordMaskCheck).getAttribute("type").equals("password");
	    }

	    public boolean isAtAccountOverview() {
	        return driver.getCurrentUrl().contains("overview.htm");
	    }
	    public void loginWithRememberMe(String user, String pass) {
	    	type(usernameField, user);
	        type(passwordField, pass);
	        click(rememberMe);
	        click(loginBtn);
	    }
	}


