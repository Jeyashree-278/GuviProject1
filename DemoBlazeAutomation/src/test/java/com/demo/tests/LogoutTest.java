package com.demo.tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.pages.HomePage;
import com.demo.pages.LoginPage;
import com.demo.utils.ConfigReader;

public class LogoutTest extends BaseTest {
	
	
	@Test(description="Verify logout functionality")
	public void verifyLogoutFunctionality() throws InterruptedException{
	LoginPage lp = new LoginPage(driver);
	lp.open(baseUrl);
	lp.login(ConfigReader.get("admin.username"), ConfigReader.get("admin.password"));
	// assert logged-in UI state
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	Thread.sleep(8000);
	HomePage home = new HomePage(driver);
	home.logout();
	Assert.assertTrue(home.isAtLoginPage(), "User Navigates back to Login");
	}

}
