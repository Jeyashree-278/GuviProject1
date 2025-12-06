package com.demo.pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}



   

    private By signupBtn = By.id("signin2");
    private By usernameInput = By.id("sign-username");
    private By passwordInput = By.id("sign-password");
    private By signupConfirmBtn = By.xpath("//button[text()='Sign up']");

   
    public void openSignupModal() {
        driver.findElement(signupBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
    }

    
    public void enterSignupDetails(String user, String pass) {
        driver.findElement(usernameInput).sendKeys(user);
        driver.findElement(passwordInput).sendKeys(pass);
    }

   
    public String clickSignup() {
        driver.findElement(signupConfirmBtn).click();

        // Wait for alert
        try { Thread.sleep(2000); } catch (Exception e) {}

        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        alert.accept();

        return msg;
    }
}
