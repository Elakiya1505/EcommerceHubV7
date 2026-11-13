package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends BasePage
{	
	// Constructor
		public AccountPage(WebDriver driver)
		{
			super(driver); 
        }
		
   //Locators
   @FindBy(xpath="//h1[normalize-space()='My Account']")
   WebElement confirmationText_MyAccount;
   
   @FindBy(xpath = "//li[@class='list-inline-item']//i[@class='fa-solid fa-caret-down']")
	WebElement dropDown_MyAccount;

	@FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Logout']")
	WebElement link_Logout;

	//Action Methods
	public WebElement getMyAccountConfirmation() {
		return confirmationText_MyAccount;
	}
	
	public void clickMyAccountDropDown() {
		dropDown_MyAccount.click();
	}

	public void clickLogout() {
		link_Logout.click();
	}
	
}
