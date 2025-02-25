package com.zl.mobileautomation.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ZilchHomePage {

    private final WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Sign in') or contains(text(),'Log in')]")
    private WebElement signInButton;

    public ZilchHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSignIn() {
        signInButton.click();
    }
}
