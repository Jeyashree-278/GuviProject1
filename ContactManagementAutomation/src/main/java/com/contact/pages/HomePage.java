package com.contact.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
	
	private By contactList = By.cssSelector(".contact-list, .contacts");
	private By addBtn = By.xpath("//button[contains(text(),'Add') or contains(text(),'Add Contact')]");
	private By logout = By.xpath("//a[contains(text(),'Logout') or contains(@href,'logout')]");


	public HomePage(WebDriver driver) { super(driver); }


	public boolean isAt() {
	try { wait.forVisibility(contactList); return true; } catch (Exception e) { return false; }
	}


	public void clickAdd() { click(addBtn); }
	public void logout() { click(logout); }

}
