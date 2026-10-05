package vikramkokareacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vikramkokareacademy.abstractcomponent.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
		@FindBy(css="#userEmail")
		WebElement userName;
		
		@FindBy(xpath = "//input[@id='userPassword']")
		WebElement password;
		
		@FindBy(css = ".btn.btn-block.login-btn")
		WebElement loginButton;
		
		@FindBy(css="[class*='flyInOut']")
		WebElement wrongPasswordMessagElement;
		
		public ProductCatalog logintoApplication(String emailAddress, String pass) {
			
			userName.sendKeys(emailAddress);
			password.sendKeys(pass);
			loginButton.click();
			ProductCatalog productCatalog = new ProductCatalog(driver);
			return productCatalog;
		}
		
		public void goTo(String url) {
			
			driver.get(url);
			
		}
		
		public String getErrorMessage() {
			waitForElementToAppear(wrongPasswordMessagElement);
			return wrongPasswordMessagElement.getText();
		}

}
