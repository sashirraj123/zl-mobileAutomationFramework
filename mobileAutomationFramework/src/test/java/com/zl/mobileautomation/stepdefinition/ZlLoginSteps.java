package com.zl.mobileautomation.stepdefinition;

import com.zl.mobileAutomation.service.AuthService;
import com.zl.mobileautomation.pages.ZilchLoginPage;
import config.WebDriverConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ZlLoginSteps {
    private WebDriver driver;
    private ZilchLoginPage zilchLoginPage;
    private AuthService authService;

    public ZlLoginSteps() {
        this.driver = WebDriverConfig.getInstance().webDriver();
        this.zilchLoginPage = new ZilchLoginPage(driver);
        this.authService = new AuthService();
    }
    @Given("I open Zilch login page")
    public void iOpenZilchLoginPage() throws MalformedURLException {
        zilchLoginPage.openZilchLoginPage();
    }
    @When("I accept all cookies")
    public void userAcceptsCookies() {
        zilchLoginPage.acceptAllCookies();
    }
    @And("I enter credentials")
    public void userEntersCredentials() throws IOException {
        JSONObject credentials = authService.fetchCredentials();
        String username = credentials.getString("username");
        String password = credentials.getString("password");

        zilchLoginPage.enterEmail(username);
        zilchLoginPage.enterPassword(password);
    }
    @Then("I click on the Sign In button")
    public void iClickOnSignInButton() {
        zilchLoginPage.clickSignIn();
    }
    @Then("I should see user dashboard")
    public void iShouldBeOnTheZilchLoginPage() {
       zilchLoginPage.verifySuccessfulLogin();
    }

    @Then("I close the browser")
    public void iCloseTheBrowser() {
        WebDriverConfig.runtimeShutdownHook(driver);
    }
}
