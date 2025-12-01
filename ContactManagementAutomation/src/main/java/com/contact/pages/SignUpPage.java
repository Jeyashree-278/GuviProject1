package com.contact.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.contact.utils.WaitUtils;

public class SignUpPage extends BasePage{
	private By fname = By.id("firstName");
    private By lname = By.id("lastName");
    private By email1 = By.id("email");
    private By pwd = By.id("password");
    private By submitBtn = By.id("submit");
    private By signupBtn=By.id("signup");
    private By errorMsg=By.xpath("//span[@id='error']");

    public SignUpPage(WebDriver driver) { super(driver); }

    public void open(String baseUrl) { driver.get(baseUrl); }

    public void register(String firstname, String lastname, String email, String password) {
    	click(signupBtn);
        type(fname, firstname);
        type(lname, lastname);
        type(email1, email);
        type(pwd, password);
        click(submitBtn);
    }
    
   

	public String waitForErrorMessage() {
		// TODO Auto-generated method stub
		
		return WaitUtils.until(driver, ExpectedConditions
		          .visibilityOfElementLocated(errorMsg)).getText();
	}
}


