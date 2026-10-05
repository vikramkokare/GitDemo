package vikramkokareacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import vikramkokareacademy.abstractcomponent.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
		@FindBy(css=".totalRow button")
		WebElement checkoutButton;
		
		@FindBy(xpath="//tbody/tr/td[2]")
		List<WebElement> prodcutNames;
		
		public boolean verfiyOrderDisplay(String productName) {
			boolean match =  prodcutNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
			return match;
			
		}
		
			

}
