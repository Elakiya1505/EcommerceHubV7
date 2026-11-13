package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage
{
	//constructor
	 
	public HomePage(WebDriver driver)
	{
		super(driver); 
	}
	
	
	
	//locators
	
	//Link - My Account
	@FindBy(xpath = "//i[@class='fa-solid fa-user']")
	WebElement link_MyAccount;
	
	//Link - Login
	@FindBy(xpath ="//a[normalize-space()='Login']")
	WebElement link_Login;
	
	
	//action methods
	
	public void clickMyAccount()
	{
		link_MyAccount.click();
	}
	
	public void goToLogin()
	{
		link_Login.click();
	}

}