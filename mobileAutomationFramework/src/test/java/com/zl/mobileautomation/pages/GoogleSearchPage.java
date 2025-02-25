package com.zl.mobileautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {

    private final WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchBox;

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openGoogleHomePage() {
        driver.get("https://www.google.com");
    }

    public void searchFor(String query) {
        searchBox.sendKeys(query);
        searchBox.sendKeys(Keys.RETURN);
    }

    public void clickOnZilchLink() {
        WebElement zilchLink = driver.findElement(By.xpath("//h3[contains(text(), 'Zilch')]"));
        zilchLink.click();
    }
}