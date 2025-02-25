package com.zl.mobileautomation.stepdefinition;

import com.zl.mobileautomation.pages.GoogleSearchPage;
import com.zl.mobileautomation.pages.ZilchHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ZlLoginSteps {

    @Autowired
    private WebDriver driver;

    @Autowired
    private GoogleSearchPage googleSearchPage;

    @Autowired
    private ZilchHomePage zilchHomePage;

    @Given("I open Google homepage")
    public void iOpenGoogleHomepage() {
        googleSearchPage.openGoogleHomePage();
    }

    @When("I search for {string}")
    public void iSearchFor(String query) {
        googleSearchPage.searchFor(query);
    }

    @When("I click on Zilch website link")
    public void iClickOnZilchWebsiteLink() {
        googleSearchPage.clickOnZilchLink();
    }

    @Then("I click on the Sign In button")
    public void iClickOnSignInButton() {
        zilchHomePage.clickSignIn();
    }
}
