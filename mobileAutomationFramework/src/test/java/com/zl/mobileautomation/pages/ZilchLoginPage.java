package com.zl.mobileautomation.pages;

import config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

import java.net.MalformedURLException;
import java.time.Duration;

public class ZilchLoginPage {
    private final WebDriver driver = WebDriverConfig.getInstance().webDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    WebDriverConfig webDriverConfig = new WebDriverConfig();
    String acceptCookiesButton = "//button[contains(@class, 'cky-btn-accept') and @aria-label='Accept All']";
    String emailInput = "email-field";
    String passwordInput = "password-field";
    String signInButton = "log-in-button";
    @FindBy(id = "dashboard-element-id")
    private WebElement dashboardElement;

    public ZilchLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void openZilchLoginPage() throws MalformedURLException {
        driver.get(webDriverConfig.getZilchLoginUrl().toString());
    }
    public void acceptAllCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(acceptCookiesButton))).click();
    }
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(emailInput))).sendKeys(email);
    }
     public void enterPassword(String password) {
         wait.until(ExpectedConditions.elementToBeClickable(By.id(passwordInput))).sendKeys(password);
    }
    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(signInButton))).click();
    }
    public void verifySuccessfulLogin() {
        wait.until(ExpectedConditions.visibilityOf(dashboardElement));
    }
}
