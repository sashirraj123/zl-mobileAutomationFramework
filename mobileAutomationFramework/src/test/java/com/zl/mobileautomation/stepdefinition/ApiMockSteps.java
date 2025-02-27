package com.zl.mobileautomation.stepdefinition;


import com.zl.mobileAutomation.service.Credentials;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.RestAssuredManager;

public class ApiMockSteps {

    private RestAssuredManager restAssuredManager;
    private Response response;
    private Credentials credentials;

    public ApiMockSteps() {
        this.restAssuredManager = new RestAssuredManager();
    }

    @Given("I send a request to get authentication credentials")
    public void iGetAuthenticationCredentials() {
        response = restAssuredManager.getCredentialsResponse();
        credentials = restAssuredManager.getCredentials();
    }

    @Then("the response status code should be {int}")
    public void responseStatusCodeShouldBe(Integer expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Invalid status code");
    }

    @Then("the response should contain a valid username and password")
    public void the_response_should_contain_a_valid_username_and_password() {
        Assert.assertNotNull(credentials.getUsername(), "Username is null");
        Assert.assertNotNull(credentials.getPassword(), "Password is null");
        Assert.assertFalse(credentials.getUsername().isEmpty(), "Username is empty");
        Assert.assertFalse(credentials.getPassword().isEmpty(), "Password is empty");
        Assert.assertEquals(credentials.getUsername(), "sashir_kakumanu@yahoo.co.uk");
        Assert.assertEquals(credentials.getPassword(), "Password1");
    }
}
