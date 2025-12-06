package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
	private By homeBtn=By.className("nav-link");
	private By logout=By.id("logout2");
	private By login=By.id("login2");
	private By addToCartBtns =  By.id("cartur");
	private By signupBtn = By.id("signin2");
	public HomePage(WebDriver driver) { super(driver); }

public void clickHome()
{
	click(homeBtn);
}
	public boolean isAt() {
	try { wait.forVisibility(logout); return true; } catch (Exception e) { return false; }
	}
	public boolean isAtLoginPage() {
		try { wait.forVisibility(login); return true; } catch (Exception e) { return false; }
		}

	
	private By productCard(String productName) {
        return By.xpath("//a[normalize-space()='" + productName + "']");
    }

	
	public boolean isAddToCartVisible() {
	    return driver.findElements(addToCartBtns).size() > 0;
	}

	public boolean isLoginVisible() {
	    return driver.findElement(login).isDisplayed();
	}
	public WebElement getFirstProductTitle() {
	    return driver.findElement(By.cssSelector(".card-title"));
	}

	public boolean isSignupVisible() {
	    return driver.findElement(signupBtn).isDisplayed();
	}
    private By nextBtn = By.id("next2");
    private By prevBtn = By.id("prev2");
    private By categories = By.id("cat");
    private By homeLink = By.xpath("//a[normalize-space()='Home']");
    private By cartLink = By.xpath("//a[normalize-space()='Cart']");
    private By contactLink = By.xpath("//a[normalize-space()='Contact']");
    private By productCards = By.cssSelector(".card");

  

    public void open(String url) {
        driver.get(url);
    }
    

    public void logout() {
        click(logout);
    }

    public void clickHome1() {
        click(homeLink);
    }

    public void clickCart() {
        click(cartLink);
    }

    public void clickContact() {
        click(contactLink);
    }

    public boolean isProductListVisible() {
        return driver.findElements(productCards).size() > 0;
    }
 

    // Click product by name
    public void selectProduct(String productName) {
        wait.forVisibility(productCard(productName));
        click(productCard(productName));
    }

    // Pagination
    public void goToNextPage() {
        click(nextBtn);
    }

    public void goToPreviousPage() {
        click(prevBtn);
    }

    // Verify category menu visible
    public boolean isCategorySectionVisible() {
        return driver.findElement(categories).isDisplayed();
    }
}


