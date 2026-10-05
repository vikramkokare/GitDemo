package vikramkokareacademy.Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.desktop.OpenFilesEvent;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vikramkokareacademy.TestComponent.BaseTest;
import vikramkokareacademy.pageobjects.CartPage;
import vikramkokareacademy.pageobjects.CheckoutPage;
import vikramkokareacademy.pageobjects.LandingPage;
import vikramkokareacademy.pageobjects.OrdersPage;
import vikramkokareacademy.pageobjects.ProductCatalog;

public class SubmitOrder extends BaseTest {

	@Test(dataProvider = "getData", groups = "PurchaseOrder")
	// public void submitOrder(String username, String password, String productName)
	// throws IOException{
	public void submitOrder(HashMap<String, String> input) throws IOException {
		// ProductCatalog productCatalog = lPage.logintoApplication(username, password);
		ProductCatalog productCatalog = lPage.logintoApplication(input.get("username"), input.get("password"));

		String product1 = "ADIDAS ORIGINAL";
		String product2 = "ZARA COAT 3";
		String countryName = "india";
		String expectedText = "Thankyou for the order.";

		// productCatalog.addProductToCart(productName);
		productCatalog.addProductToCart(input.get("productName"));
		// productCatalog.addProductToCart(product2);

		//String product = input.get("productName");
		// Clicking on cart
		CartPage co = productCatalog.goToCart();

		Assert.assertTrue(productCatalog.findProductAddedIntoCart(input.get("productName")));

		co.clickOnCheckout();
		CheckoutPage cp = co.selectCountry(countryName);
		Assert.assertTrue(cp.confirmText().equalsIgnoreCase(expectedText));

	}

	@Test(dependsOnMethods = "submitOrder")
	public void orderHistory() {
		ProductCatalog productCatalog = lPage.logintoApplication("kokare.vikram@gmail.com", "Selenium@123");
		OrdersPage op = productCatalog.goToOrders();
		boolean result = op.verfiyOrderDisplay("ZARA COAT 3");
		Assert.assertTrue(result);
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\vikramkokareacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1)} };
	}
	
	/* Using HASHMAP providing input data
	 * @DataProvider public Object[][] getData() {
	 * 
	 * HashMap<String, String> map = new HashMap<String, String>();
	 * map.put("username", "kokare.vikram@gmail.com"); map.put("password",
	 * "Selenium@123"); map.put("productName", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * map1.put("username", "arinjay@test.com"); map1.put("password",
	 * "Arinjay@123"); map1.put("productName", "ADIDAS ORIGINAL");
	 * 
	 * return new Object[][] { { map }, { map1 } }; }
	 */
	
	/* using array dataprovider
	 * @DataProvider public Object[][] getData() { 
	 * return new Object[][] { { "arinjay@test.com","Arinjay@123","ADIDAS ORIGINAL" }, { "kokare.vikram@gmail.com","Selenium@123", "ZARA COAT 3"} }; 
	 * }
	 */

}
