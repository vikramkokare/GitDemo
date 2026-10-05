package vikramkokareacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import vikramkokareacademy.abstractcomponent.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
		@FindBy(css=".totalRow button")
		WebElement checkoutButton;
		
		@FindBy(xpath="//input[@placeholder='Select Country']")
		WebElement searchBox;
		
		@FindBy(xpath = "(//section[contains(@class,'ta-results list-group ng-star-inserted')]/button)[2]")
		WebElement countryName;
		
		@FindBy(css=".btnn.action__submit.ng-star-inserted")
		WebElement submitButton;	
		        
		public void clickOnCheckout() {
			WebElement cButton = waitForElementToVisible(checkoutButton);
			Actions a = new Actions(driver);
			a.moveToElement(cButton).click().perform();
		}
		
		public CheckoutPage selectCountry(String countryName) {
			Actions a = new Actions(driver);
			a.sendKeys(searchBox, countryName).build().perform();
			this.countryName.click();
			submitButton.click();	
			CheckoutPage cp = new CheckoutPage(driver);
			return cp;
		}
			

}
