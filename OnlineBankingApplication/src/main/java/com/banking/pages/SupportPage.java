package com.banking.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SupportPage extends BasePage {
	 

	    public SupportPage(WebDriver driver) {
	        super(driver);
	    }
	    

	    @FindBy(name = "name")         // replace with actual name/id
	    WebElement nameField;

	    @FindBy(name = "email")        // replace with actual name/id
	    WebElement emailField;

	    @FindBy(name = "subject")      // replace with actual name/id
	    WebElement subjectField;

	    @FindBy(name = "message")      // replace with actual name/id
	    WebElement messageField;

	    @FindBy(css = "button[type='submit']")   // or actual locator of the submit button
	    WebElement submitButton;

	    @FindBy(css = ".success")      // or actual locator of success message
	    WebElement successMessage;

	    @FindBy(css = ".error")        // or actual locator of error / validation message
	    WebElement errorMessage;

	    public void fillForm(String name, String email, String subject, String message) {
	        nameField.clear();
	        nameField.sendKeys(name);
	        emailField.clear();
	        emailField.sendKeys(email);
	        subjectField.clear();
	        subjectField.sendKeys(subject);
	        messageField.clear();
	        messageField.sendKeys(message);
	    }

	    public void submitForm() {
	        submitButton.click();
	    }

	    public String getSuccessMessage() {
	        return successMessage.getText();
	    }

	    public String getErrorMessage() {
	        return errorMessage.getText();
	    }

}
