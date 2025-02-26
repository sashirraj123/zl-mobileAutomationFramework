@web_login
Feature: login to Zilch website

  Scenario: login to Zilch using API mock service
    Given I open Zilch login page
    And I accept all cookies
    When I enter credentials
    Then I click on the Sign In button
    And I close the browser

