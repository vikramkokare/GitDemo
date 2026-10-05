package vikramkokareacademy.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import vikramkokareacademy.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lPage;
	Properties prop = new Properties();
	
	public WebDriver initilizeDriver() throws IOException {
				
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\vikramkokareacadamey\\resources\\GlobalData.properties");
		prop.load(fis);
		
		/*If we are passing browser name from Maven then using this ternary statement we will get that value
		And if we don't have that value from Maven then we will use global property using prop object.*/
		
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser") : prop.getProperty("browser");
		
		//String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			ChromeOptions options =  new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}			
			driver = new ChromeDriver(options); 
			driver.manage().window().setSize(new Dimension(1440, 900));
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else {
		    throw new IllegalArgumentException("Invalid browser: " + browserName);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		return driver;
	}
	
	  @BeforeMethod(alwaysRun = true)
	  public LandingPage launchApplication() throws IOException { 
		  driver = initilizeDriver(); 
		  lPage = new LandingPage(driver);
	      lPage.goTo(prop.getProperty("LandingURL")); 
	      return lPage; 
	      
	  }
	  
	  @AfterMethod(alwaysRun = true)
	  public void tearDown() {
		  driver.close();
	  }
	  
	  public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
			
			// Read json to string
			// String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\vikramkokareacademy\\data\\PurchaseOrder.json"));
			 String jsonContent = FileUtils.readFileToString( new File(filePath),StandardCharsets.UTF_8
				);
			//convert string josn data to HashMap using Jackson databind
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
			
			return data;
		}
	  
	  public String getScreenshot(String testcasename, WebDriver driver) throws IOException {
		  TakesScreenshot ts =  (TakesScreenshot)driver;
		  File srcFile =  ts.getScreenshotAs(OutputType.FILE);
		  File 	file = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		  FileUtils.copyFile(srcFile, file);
		  return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";
 	  }
	 

}
