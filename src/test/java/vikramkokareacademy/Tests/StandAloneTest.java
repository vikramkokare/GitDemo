package vikramkokareacademy.Tests;

import java.awt.event.ItemEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		//WebDriverManager.chromedriver().setup();
		//WebDriver driver = new ChromeDriver(); // Chrome driver initialized 
		
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("kokare.vikram@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Selenium@123");
		driver.findElement(By.cssSelector(".btn.btn-block.login-btn")).click();
		
		// Collecting all the elements into list
		
		String product1 = "ADIDAS ORIGINAL";
		String product2 = "ZARA COAT 3";
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		List<WebElement> products =  driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod =  products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(product1)).findFirst().orElse(null);
		
	 // Here we limited driver scope to this prod Webelemnt and using that we found the "add to cart" button.
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); 
		
		WebElement toastMessag =  driver.findElement(By.xpath("//div[@aria-label='Product Added To Cart']"));
		wait.until(ExpectedConditions.visibilityOf(toastMessag));
		
		WebElement prod2 =  products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(product2)).findFirst().orElse(null);
		prod2.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	    
		// Using explicit wait to load cart option
		
		WebElement loader =  driver.findElement(By.cssSelector(".ng-animating"));
		
		wait.until(ExpectedConditions.visibilityOf(toastMessag));
		
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		wait.until(ExpectedConditions.invisibilityOf(loader));
		
		//Clicking on cart
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
		
		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		
		boolean match =  cartItems.stream().anyMatch(Item -> Item.getText().equalsIgnoreCase(product2));
		
		Assert.assertTrue(match);
		//Thread.sleep(2000);
		
		WebElement checkoutButton = driver.findElement(By.cssSelector(".totalRow button"));
		wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
		//driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("window.scrollBy(0,500)");;
		
		/*
		 * wait.until(ExpectedConditions.invisibilityOf(toastMessag)); WebElement
		 * checkoutButton = driver.findElement(By.cssSelector(".totalRow button"));
		 * 
		 * wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.
		 * cssSelector(".totalRow button")))); checkoutButton.click();
		 */
		
		

	}

}
