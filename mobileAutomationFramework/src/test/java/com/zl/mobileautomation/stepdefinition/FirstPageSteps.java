package com.zl.mobileautomation.stepdefinition;

import com.zl.mobileautomation.pages.FirstPage;
import com.zl.mobileautomation.pages.ProductsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import java.net.MalformedURLException;

public class FirstPageSteps {

    FirstPage firstPage = new FirstPage();
    ProductsPage productsPage = new ProductsPage();

    public FirstPageSteps() throws MalformedURLException {
    }

    @When("I enter username as {string}")
    public void iEnterUsernameAs(String username) throws InterruptedException {
        firstPage.enterUserName(username);
    }

    @When("I enter password as {string}")
    public void iEnterPasswordAs(String password) {
        firstPage.enterPassword(password);
    }

    @When("I login")
    public void iLogin() throws MalformedURLException {
        firstPage.pressLoginBtn();
    }

    @Then("login should fail with an error {string}")
    public void loginShouldFailWithAnError(String err) {
        Assertions.assertEquals(firstPage.getErrTxt(), err);
    }

    @Then("I should see Products page with title {string}")
    public void iShouldSeeProductsPageWithTitle(String title) {
        Assertions.assertEquals(productsPage.getTitle(), title);
    }

}
