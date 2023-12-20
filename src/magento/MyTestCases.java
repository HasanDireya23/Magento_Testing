package magento;





import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import java.time.Duration;
import java.util.List;
import java.util.Random;


public class MyTestCases {

	String LogoutPage = "https://magento.softwaretestingboard.com/customer/account/logout/";
	String LoginPage = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";
	String Items = "https://magento.softwaretestingboard.com/men/tops-men/jackets-men.html";
	WebDriver driver = new ChromeDriver();

	String UserPassword = "ASDasd123!";
	String[] firstNames = { "ahmad", "mohammad", "omar" };
	String[] lastNames = { "anas", "ali", "mahmoud" };
	Random rand = new Random();
	int randomIndex = rand.nextInt(3);
	int randomEmailID = rand.nextInt(9999);
	String UserFirstName = firstNames[randomIndex];
	String UserLastName = lastNames[randomIndex];
	String email_Address = UserFirstName + UserLastName + randomEmailID + "@gmail.com";

	@BeforeTest
	public void myBeforeTest() {
		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		WebElement SignUpButton = driver.findElement(By.linkText("Create an Account"));

		SignUpButton.click();

	}

	@Test(priority = 1)
	public void SignUp() throws InterruptedException {
		WebElement FirstName = driver.findElement(By.id("firstname"));
		WebElement LastName = driver.findElement(By.id("lastname"));
		WebElement Email = driver.findElement(By.id("email_address"));
		WebElement Password = driver.findElement(By.id("password"));
		WebElement ConfirmPassword = driver.findElement(By.id("password-confirmation"));
		WebElement CreateAccountButton = driver.findElement(By.xpath("//button[@title='Create an Account']"));
		FirstName.sendKeys(UserFirstName);
		LastName.sendKeys(UserLastName);
		Email.sendKeys(email_Address);
		Password.sendKeys(UserPassword);
		ConfirmPassword.sendKeys(UserPassword);
		CreateAccountButton.click();

		Thread.sleep(3000);
		
		WebElement SignUpMsg = driver.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		
		Assert.assertEquals(SignUpMsg.getText().contains("Thank you"),true,"\"Sign UP is failed \"");

	}
	@Test(priority = 2)
	public void logoutTest() {
		driver.get(LogoutPage);

	}

	@Test(priority = 3)
	public void LoginPage() throws InterruptedException {
		driver.get(LoginPage);
		WebElement LoginEmail = driver.findElement(By.id("email"));
		WebElement LoginPassword = driver.findElement(By.id("pass"));
		WebElement LoginButton = driver.findElement(By.id("send2"));

		 
		LoginEmail.sendKeys(email_Address);
		LoginPassword.sendKeys(UserPassword);
		LoginButton.click();
		Thread.sleep(2000);
		WebElement WelcomeMsg = driver.findElement(By.xpath("//div[@class='panel header']"));

	Assert.assertEquals(WelcomeMsg.getText().contains("Welcome"),true,"\"this is to check if Welcome msg is there or no \"");
	}
	
	
	@Test(priority = 4)
	public void AddFirstItem() throws InterruptedException {
		driver.get(Items);
		WebElement FirstItem = driver.findElement(By.cssSelector("img[alt='Proteus Fitness Jackshirt']"));
        FirstItem.click();

        // Find elements for sizes and colors
        List<WebElement> sizeOptions = driver.findElements(By.cssSelector(".swatch-option.text"));
        List<WebElement> colorOptions = driver.findElements(By.cssSelector(".swatch-option.color"));

        // Randomly select a size
        Random rand = new Random();
        int randomSizeIndex = rand.nextInt(sizeOptions.size());
        WebElement selectedSize = sizeOptions.get(randomSizeIndex);
        selectedSize.click();

        // Randomly select a color
        int randomColorIndex = rand.nextInt(colorOptions.size());
        WebElement selectedColor = colorOptions.get(randomColorIndex);
        selectedColor.click();

        // Add the item to the cart
        WebElement addToCartButton = driver.findElement(By.cssSelector("#product-addtocart-button"));
        addToCartButton.click();

        Thread.sleep(2000);

        // Print item name and price
        WebElement itemNameElement = driver.findElement(By.cssSelector(".base")); // Update this CSS selector to match the correct item name element
        WebElement itemPriceElement = driver.findElement(By.cssSelector("span[id='product-price-430'] span[class='price']")); // Update this CSS selector to match the correct item price element

        
        WebElement AddMsg = driver.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
        
        
    	Assert.assertEquals(AddMsg.getText().contains("You added"),true,"\"No Added Items \"");
        
        
        String itemName = itemNameElement.getText();
        String itemPrice = itemPriceElement.getText();
        
      
        
        System.out.println("the item " + itemName + " has the orginal price of " + itemPrice);
	}

	@AfterTest
	public void myAfterTest() {
		driver.close(); 
	}
}



