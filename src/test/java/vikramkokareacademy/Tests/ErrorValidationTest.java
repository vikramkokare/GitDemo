package vikramkokareacademy.Tests;

import java.awt.event.ItemEvent;
import java.io.IOException;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vikramkokareacademy.TestComponent.BaseTest;
import vikramkokareacademy.TestComponent.Retry;
import vikramkokareacademy.pageobjects.CartPage;
import vikramkokareacademy.pageobjects.CheckoutPage;
import vikramkokareacademy.pageobjects.LandingPage;
import vikramkokareacademy.pageobjects.ProductCatalog;

public class ErrorValidationTest extends BaseTest{

	@Test(groups="ErrorvalidationTest", retryAnalyzer = Retry.class) //We need to mention this specifically if we think this test case might be failed due to application load or other issue and we want to rerun it.
	public void loginErrorValidation() throws IOException{
				 		
		ProductCatalog productCatalog = lPage.logintoApplication("kokare.vikram@gmail.com", "Selenium@1234");	
		Assert.assertEquals("Incorrect email or password.", lPage.getErrorMessage());			
	}

	@Test
	public void productErrorValidation() {
		ProductCatalog productCatalog = lPage.logintoApplication("arinjay@test.com", "Arinjay@123");
		
		String product1 = "ADIDAS ORIGINAL";
		String product2 = "ZARA COAT 3";
			
		productCatalog.addProductToCart(product1);
		productCatalog.addProductToCart(product2);	 
						
		//Clicking on cart
		productCatalog.goToCart();
		
		Assert.assertTrue(productCatalog.findProductAddedIntoCart("ZARA COAT 33"));
	}
}
