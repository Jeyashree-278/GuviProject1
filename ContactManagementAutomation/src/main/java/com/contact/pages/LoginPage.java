package com.contact.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.contact.utils.WaitUtils;

public class LoginPage extends BasePage {
	private By email = By.id("email");
	private By password = By.id("password");
	private By loginBtn = By.id("submit");
	private By validationMsg = By.cssSelector(".error, .alert, .toast");
	private By errorMsg=By.xpath("//span[@id='error']");
	 private By loginPageHeader = By.xpath("//h2[contains(text(), 'Login')]");
	public LoginPage(WebDriver driver) { super(driver); }

	public boolean isAtLoginPage() {
        return driver.findElements(loginBtn).size() > 0;
    }
	public void open(String baseUrl) 
	{ 
		driver.get(baseUrl); 
		
	}


	public void login(String userEmail, String pwd) {
	type(email, userEmail);
	type(password, pwd);
	click(loginBtn);
	}
	public String getErrorMessage() {
	    return driver.findElement(errorMsg).getText();
	    
	}
	public String waitForErrorMessage() {
		return WaitUtils.until(driver, ExpectedConditions
		          .visibilityOfElementLocated(errorMsg)).getText();
	}
	public boolean isPasswordMasked() {
	    return driver.findElement(password).getAttribute("type").equals("password");
	}
	public String getValidationMessage() {
	try { return getText(validationMsg); } catch (Exception e) { return ""; }
	}
	}

