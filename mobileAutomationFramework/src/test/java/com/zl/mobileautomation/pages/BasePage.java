package com.zl.mobileautomation.pages;

import config.AppiumConfig;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashMap;

import static config.AppiumConfig.getProperties;

@Component
public class BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    private String platform;

//    private final AppiumDriver appiumDriver;
//    @Autowired
//    @Qualifier("appiumConfig")
    AppiumConfig appiumConfig = AppiumConfig.getInstance();
    @Autowired
    public BasePage() throws MalformedURLException {
//        this.appiumDriver = appiumConfig.getAppiumDriver();
        PageFactory.initElements(new AppiumFieldDecorator(appiumConfig.getAppiumDriver()), this);
        platform = System.getProperty("platformName").isEmpty() ?
                getProperties().getProperty("platformName") : System.getProperty("platformName");

    }

    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(appiumConfig.getAppiumDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForVisibility(By e) {
        WebDriverWait wait = new WebDriverWait(appiumConfig.getAppiumDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(e));
    }

    public void clear(WebElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void click(WebElement e, String msg) {
        waitForVisibility(e);
        LOGGER.info(msg);
        e.click();
    }

    public void click(By e, String msg) {
        waitForVisibility(e);
        LOGGER.info(msg);
        appiumConfig.getAppiumDriver().findElement(e).click();
    }

    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public void sendKeys(WebElement e, String txt, String msg) {
        waitForVisibility(e);
        LOGGER.info(msg);
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getAttribute(By e, String attribute) {
        waitForVisibility(e);
        return appiumConfig.getAppiumDriver().findElement(e).getAttribute(attribute);
    }

    public String getText(WebElement e, String msg) {
        String txt;
        switch(platform){
            case "Android":
                txt = getAttribute(e, "text");
                break;
            case "iOS":
                txt = getAttribute(e, "label");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + platform);
        }
        LOGGER.info(msg + txt);
        return txt;
    }

    public String getText(By e, String msg) {
        String txt;
        switch(platform){
            case "Android":
                txt = getAttribute(e, "text");
                break;
            case "iOS":
                txt = getAttribute(e, "label");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + platform);
        }
        LOGGER.info(msg + txt);
        return txt;
    }

    public void closeApp() {
        boolean isAndroid = appiumConfig.getAppiumDriver() instanceof AndroidDriver;
        if (isAndroid) {
            ((AndroidDriver) appiumConfig.getAppiumDriver()).terminateApp(appiumConfig.getAppiumDriver().getCapabilities().getCapability("appPackage").toString());
        } else {
            ((IOSDriver) appiumConfig.getAppiumDriver()).terminateApp(appiumConfig.getAppiumDriver().getCapabilities().getCapability("bundleId").toString());
        }
    }

    public void launchApp() {
        InteractsWithApps appDriver = (InteractsWithApps) appiumConfig.getAppiumDriver();
        switch (platform) {
            case "Android" -> appDriver.activateApp(appiumConfig.getAppiumDriver().getCapabilities().getCapability("appPackage").toString());
            case "iOS" -> appDriver.activateApp(appiumConfig.getAppiumDriver().getCapabilities().getCapability("bundleId").toString());
        }
    }

    public WebElement andScrollToElementUsingUiScrollable(String childLocAttr, String childLocValue) {
        return appiumConfig.getAppiumDriver().findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector()."+ childLocAttr +"(\"" + childLocValue + "\"));"));
    }

    public WebElement iOSScrollToElementUsingMobileScroll(WebElement e) {
        RemoteWebElement element = ((RemoteWebElement) e);
        String elementID = element.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", elementID);
        scrollObject.put("toVisible", "sdfnjksdnfkld");
        appiumConfig.getAppiumDriver().executeScript("mobile:scroll", scrollObject);
        return e;
    }

    public By iOSScrollToElementUsingMobileScrollParent(WebElement parentE, String predicateString) {
        RemoteWebElement parent = (RemoteWebElement)parentE;
        String parentID = parent.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", parentID);
	    scrollObject.put("predicateString", predicateString);
        appiumConfig.getAppiumDriver().executeScript("mobile:scroll", scrollObject);
        By m = AppiumBy.iOSNsPredicateString(predicateString);
        System.out.println("Mobilelement is " + m);
        return m;
    }

    public boolean find(final WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(appiumConfig.getAppiumDriver(), Duration.ofSeconds(timeout));
            return wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    if (element.isDisplayed()) {
                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

    public boolean find(final By element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(appiumConfig.getAppiumDriver(), Duration.ofSeconds(timeout));
            return wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    if (driver.findElement(element).isDisplayed()) {
                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            return false;
        }
    }
}
