package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

public class WebDriverConfig {
    private static final Logger log = LoggerFactory.getLogger(WebDriverConfig.class);
    private static WebDriverConfig instance;
    private WebDriver driver;
    public static synchronized WebDriverConfig getInstance() {
        if (instance == null) {
            instance = new WebDriverConfig();
        }
        return instance;
    }
    public synchronized WebDriver webDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            ChromeOptions chromeOptions = new ChromeOptions();
            driver = new ChromeDriver(chromeOptions);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public URL getZilchLoginUrl() throws MalformedURLException {
        Properties props = getProperties();
        return URI.create(props.getProperty("zilch.login.url")).toURL();
    }

    public static Properties getProperties() {
        Properties props = new Properties();
        try (InputStream input = WebDriverConfig.class.getClassLoader().getResourceAsStream("bootstrap.properties")) {
            if (input == null) {
                log.error("Sorry, unable to find bootstrap.properties");
            }
            props.load(input);
        } catch (IOException ex) {
            log.error("Error loading properties file", ex);
        }
        return props;
    }
    public static void runtimeShutdownHook(WebDriver driver) {
         Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
    }
}