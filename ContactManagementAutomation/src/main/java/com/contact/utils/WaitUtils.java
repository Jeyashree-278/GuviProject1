package com.contact.utils;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	
		private WebDriverWait wait;
		public WaitUtils(WebDriver driver, int seconds) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		}


		public void forVisibility(By locator) { wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); }
		public void forClickable(By locator) { wait.until(ExpectedConditions.elementToBeClickable(locator)); }

		public static WebElement until(WebDriver driver, ExpectedCondition<WebElement> visibilityOfElementLocated) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        return wait.until(visibilityOfElementLocated);
	    }

		 public WebElement waitForVisibility(By locator) {
		        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		    }

		    // Return clickable element
		    public WebElement waitForClickable(By locator) {
		        return wait.until(ExpectedConditions.elementToBeClickable(locator));
		    }

		    // Wait for alert and return it
		    public Alert waitForAlert() {
		        return wait.until(ExpectedConditions.alertIsPresent());
		    }
		
		}
		
		
		

