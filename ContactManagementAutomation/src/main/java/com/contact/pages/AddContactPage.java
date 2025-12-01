package com.contact.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.contact.utils.WaitUtils;

public class AddContactPage extends BasePage {
	
	    private By firstName = By.id("firstName");
	    private By lastName = By.id("lastName");
	    private By email = By.id("email");
	    private By phone = By.id("phone");
	    private By submit = By.xpath("//button[contains(text(),'Submit') or @type='submit']");
	    private By addContactbtn=By.id("add-contact");
	    private By errorMsg=By.xpath("//span[@id='error']");
	    private By address = By.id("street1");

	    public String getAddressValue() {
	        return getValue(address);
	    }
	    public AddContactPage(WebDriver driver) { super(driver); }

	    public void open(String baseUrl) { driver.get(baseUrl); }

	    public void add(String fn, String ln, String mail, String ph) {
	    	click(addContactbtn);
	        type(firstName, fn);
	        type(lastName, ln);
	        type(email, mail);
	        type(phone, ph);
	        click(submit);
	    }
	    public String waitForErrorMessage() {
			// TODO Auto-generated method stub
			
			return WaitUtils.until(driver, ExpectedConditions
			          .visibilityOfElementLocated(errorMsg)).getText();
		}
	    

	    	public boolean isFormVerticallyOrdered() {

	    	    int fnY = wait.waitForVisibility(firstName).getLocation().getY();
	    	    int lnY = wait.waitForVisibility(lastName).getLocation().getY();
	    	    int submitY = wait.waitForVisibility(submit).getLocation().getY();

	    	    return fnY < lnY && lnY < submitY;
	    	}
	    
	    public boolean isFormCleared() {
	        try {
	            return driver.findElement(firstName).getAttribute("value").isEmpty()
	                    && driver.findElement(lastName).getAttribute("value").isEmpty();
	        } catch (Exception e) { return false; }
	    }


	    public void typeFirstName(String text) {
	        type(firstName, text);
	    }

	    public String getFirstNameValue() {
	        return getValue(firstName);
	    }

	    public boolean isErrorDisplayed() {
	        return driver.findElements(By.cssSelector(".error, .alert, #error")).size() > 0;
	    }
	    public boolean isUnicodeAcceptedInAddress() {
	       

	        String unicodeText = "House - 😊🚀 Chennai தமிழ் 中文 عربى";
	        type(address,unicodeText);
	        

	        String actual = ((WebElement) address).getAttribute("value");

	        return actual.contains("😊") &&
	               actual.contains("தமிழ்") &&
	               actual.contains("中文");
	    }
		
}
