package com.demo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public static WebDriver createDriver(String browser) {
		if (browser == null) browser = "chrome";
		switch (browser.toLowerCase()) {
		case "chrome":
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
		case "edge":
           WebDriverManager.edgedriver().setup();
           return new EdgeDriver();
           
		case "firefox":
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
		default:
		throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

}
}
