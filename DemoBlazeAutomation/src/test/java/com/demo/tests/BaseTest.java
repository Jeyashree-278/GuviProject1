package com.demo.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.demo.utils.ConfigReader;
import com.demo.utils.DriverFactory;

public class BaseTest {


	protected WebDriver driver;
	protected String baseUrl = ConfigReader.get("baseUrl");


	@Parameters({"browser"})
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser) {
	driver = DriverFactory.createDriver(browser);
	driver.manage().window().maximize();
	}


	@AfterMethod(alwaysRun = true)
	public void tearDown() {
	if (driver != null) driver.quit();
	}
}
