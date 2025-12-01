package com.contact.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.contact.utils.WaitUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditContactPage extends BasePage {

    public EditContactPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

   

    

    // Locators
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("email");
    private By phone = By.id("phone");
    private By submit = By.id("submit");
    private By cancel = By.id("cancel");
    private By errorMsg=By.xpath("//span[@id='error']");
    public String waitForErrorMessage() {
		return WaitUtils.until(driver, ExpectedConditions
		          .visibilityOfElementLocated(errorMsg)).getText();
    }
    //=============== Utility Clear Methods ===============//

    private void clearField(By locator) {
        driver.findElement(locator).clear();  // works for this application
    }

    private void forceClear(By locator) {
        WebElement el = driver.findElement(locator);
        el.sendKeys(Keys.CONTROL + "a");
        el.sendKeys(Keys.DELETE);
    }

    //=============== Field Setters ===============//

    public EditContactPage setFirstName(String value) {
        clearField(firstName);
        driver.findElement(firstName).sendKeys(value);
        return this;
    }

    public EditContactPage setLastName(String value) {
        clearField(lastName);
        driver.findElement(lastName).sendKeys(value);
        return this;
    }

    public EditContactPage setEmail(String value) {
        clearField(email);
        driver.findElement(email).sendKeys(value);
        return this;
    }

    public EditContactPage setPhone(String value) {
    	clearField(phone);
    	driver.findElement(phone).sendKeys(value);
        return this;
    }

    //=============== Buttons ===============//

    public void clickSave() {
        driver.findElement(submit).click();
    }

    public void clickCancel() {
        driver.findElement(cancel).click();
    }
}
