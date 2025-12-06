package com.demo.pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactPage extends BasePage {

	public ContactPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	 public String viewConatct() {
	        
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

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
	       
	 }
}
