@web_login
Feature: login to Zilch website

  Scenario: login to Zilch using API mock service
    Given I open Zilch login page
    And I accept all cookies
    And I enter credentials
    And I click on the Sign In button
#    Then I should see user dashboard
