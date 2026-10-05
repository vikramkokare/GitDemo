package vikramkokareacademy.abstractcomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import vikramkokareacademy.pageobjects.CartPage;
import vikramkokareacademy.pageobjects.OrdersPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersButton;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	
	public WebElement waitForElementToVisible(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
		return ele;
	}
	
	public void waitForElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	public CartPage goToCart() {
		cart.click();	
		CartPage co = new CartPage(driver);
		return co;
	}
	
	public OrdersPage goToOrders() {
		ordersButton.click();
		OrdersPage op = new OrdersPage(driver);
		return op;
	}

}
