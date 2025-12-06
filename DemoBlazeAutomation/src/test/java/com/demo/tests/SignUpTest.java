package com.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.pages.LoginPage;
import com.demo.pages.SignUpPage;

public class SignUpTest extends BaseTest {

    @Test(description="verify Signup With Unique Credentials")
    public void verifySignupWithUniqueCredentials() {
    	LoginPage lp = new LoginPage(driver);
		lp.open(baseUrl);
        SignUpPage sp = new SignUpPage(driver);
        sp.openSignupModal();
        sp.enterSignupDetails("user" + System.currentTimeMillis(), "pass123");

        String alertText = sp.clickSignup();

        Assert.assertEquals(alertText, "Sign up successful.");
    }

    @Test(description="verify Signup With existing username")
    public void verifySignupWithExistingUsername() {
    	LoginPage lp = new LoginPage(driver);
		lp.open(baseUrl);
        SignUpPage sp = new SignUpPage(driver);

        sp.openSignupModal();
        sp.enterSignupDetails("Jeyashree", "password");

        String alertText = sp.clickSignup();

        Assert.assertEquals(alertText, "This user already exist.");
    }

    @Test(description="verify Signup With empty fields")
    public void verifySignupWithEmptyFields() {
    	
    	LoginPage lp = new LoginPage(driver);
		lp.open(baseUrl);
        SignUpPage sp = new SignUpPage(driver);

        sp.openSignupModal();

        String alertText = sp.clickSignup();

        Assert.assertTrue(alertText.contains("Please fill out"));
    }
}