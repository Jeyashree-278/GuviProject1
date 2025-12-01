package com.contact.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.contact.pages.HomePage;
import com.contact.pages.SignUpPage;

public class SignUpTest extends BaseTest {
	
	 @Test(description = "Verify sign-up with valid inputs")
	    public void signUpWithValidInputs() {
	        SignUpPage signup = new SignUpPage(driver);
	        signup.open(baseUrl);
	        String uniqueEmail = System.currentTimeMillis() + "@example.com";
	        signup.register("AutoUser","testname", uniqueEmail, "Pass1234");
	        HomePage home = new HomePage(driver);
	        Assert.assertTrue(home.isAt(), "User should see contact list after signup");
	    }


	 @Test(description = "Verify sign-up with already registered email")
	 public void signUpwithAlreadyRegisteredEmail()
	 {
		 SignUpPage signup = new SignUpPage(driver);
		 signup.open(baseUrl);
		 signup.register("AutoUser","testname","test12345@gmail.com", "Pass1234");
		 String msg=signup.waitForErrorMessage();
		    Assert.assertEquals(msg, "Email address is already in use");
	 }
	 
	 @Test(description = "Verify sign-up with blankfields")
	 public void signUpwithBlankFields()
	 {
		 SignUpPage signup = new SignUpPage(driver);
		 signup.open(baseUrl);
		 signup.register("AutoUser","testname","", "");
		 String msg=signup.waitForErrorMessage();
		    Assert.assertEquals(msg, "User validation failed: email: Email is invalid, password: Path `password` is required.");
	 }
	 @Test(description = "Verify email format validation during sign-up")
	 public void signUpwithIncorrectEmailFormat()
	 {
		 SignUpPage signup = new SignUpPage(driver);
		 signup.open(baseUrl);
		 signup.register("AutoUser","testname","test789", "jkuuhuhjhyt");
		 String msg=signup.waitForErrorMessage();
		    Assert.assertEquals(msg, "User validation failed: email: Email is invalid");
	 }
	 
}

