package vikramkokareacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vikramkokareacademy.abstractcomponent.AbstractComponents;

public class CheckoutPage extends AbstractComponents{

    WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css=".hero-primary")
	WebElement title;	

	public String confirmText() {
		return title.getText();
	}	
}
