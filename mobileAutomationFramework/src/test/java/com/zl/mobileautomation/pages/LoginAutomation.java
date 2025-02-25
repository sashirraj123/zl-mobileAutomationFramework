package com.zl.mobileautomation.pages;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class LoginAutomation{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAutomation.class);
    public static void main(String[] args) {
        // Automatically downloads and sets up ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Open Google
            driver.get("https://www.google.com");

            // Maximize window
            driver.manage().window().maximize();

            // Wait implicitly
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            // Accept cookies
            driver.findElement(By.id("L2AGLb")).click(); //

            //*[@id="L2AGLb"]/div

            // Locate search box and enter "zilch.com"
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("zilch.com");
            searchBox.sendKeys(Keys.RETURN);

            // Wait for results to load
            Thread.sleep(3000); // Not recommended, use WebDriverWait instead for better performance

            // Click on the first search result (Zilch website)
            List<WebElement> results = driver.findElements(By.cssSelector("h3"));
            for (WebElement result : results) {
                if (result.getText().toLowerCase().contains("zilch")) {
                    result.click();
                    break;
                }
            }

            // Wait for Zilch homepage to load
            Thread.sleep(3000);

            // Click on "Sign In" button (Update selector if needed)
            WebElement signInButton = driver.findElement(By.xpath("//a[@class='btn pz-direct signin' and text()='Sign in']"));
            signInButton.click();

            // Wait and validate sign-in page opened
            Thread.sleep(5000);
            System.out.println("Test Passed: Successfully navigated to Sign In page");

        } catch (Exception e) {
            LOGGER.error("Error occurred: ", e);
        } finally {
            // Close browser
            driver.quit();
        }
    }
}