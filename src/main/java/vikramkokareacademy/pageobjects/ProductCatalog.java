package vikramkokareacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vikramkokareacademy.abstractcomponent.AbstractComponents;

public class ProductCatalog extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
		@FindBy(css=".mb-3")
		List<WebElement> products;
		
		@FindBy(css=".ng-animating")
		WebElement loader;
				
		@FindBy(id="toast-container")
		WebElement toaster;
		
		@FindBy(xpath = "//div[@class='cartSection']/h3")
		List<WebElement> cartItems;
		
        //List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
				
		By productlistBy = By.cssSelector(".mb-3");
		
		By addToCart = By.cssSelector(".card-body button:last-of-type"); // Add to cart locator
		By toastMessage = By.xpath("//div[@aria-label='Product Added To Cart']"); // toast message locator
		
		
		public List<WebElement> getProducts() {
			
			waitForElementToAppear(productlistBy);
			return products;
		}
		
		public WebElement getProductByName(String productName) {
			
			WebElement prod =  getProducts().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
			return prod;
		}
		
		public void addProductToCart(String productName) {
			WebElement prod =  getProductByName(productName);
			prod.findElement(addToCart).click(); 
			waitForElementToAppear(toastMessage);
			waitForElementToDisappear(loader);
		}
		
		public boolean findProductAddedIntoCart(String productName) {
			waitForElementToDisappear(toaster);
			boolean match =  cartItems.stream().anyMatch(Item -> Item.getText().equalsIgnoreCase(productName)); //Checking product added product is present in list or not
			return match;			
		}

}
