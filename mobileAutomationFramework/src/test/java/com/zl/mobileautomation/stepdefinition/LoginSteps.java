package com.zl.mobileautomation.stepdefinition;

import com.zl.mobileautomation.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private String username;
    private String password;

    @Given("I retrieve login credentials from the API")
    public void iRetrieveLoginCredentials() throws Exception {
        URL url = new URL("http://localhost:8080/api/auth/credentials");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        Scanner scanner = new Scanner(url.openStream());
        StringBuilder inline = new StringBuilder();
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }
        scanner.close();

        JSONObject json = new JSONObject(inline.toString());
        username = json.getString("username");
        password = json.getString("password");
    }

    @Given("I open the login page")
    public void iOpenTheLoginPage() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://example.com/login");
        loginPage = new LoginPage(driver);
    }

    @When("I enter the credentials and submit")
    public void iEnterTheCredentialsAndSubmit() {
        loginPage.login(username, password);
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        // Validate login (Assuming login success redirects to dashboard)
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        driver.quit();
    }
}