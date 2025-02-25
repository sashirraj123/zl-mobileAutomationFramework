@web_login
Feature: Google Search for Zilch and Sign In

  Scenario: Search Zilch on Google and Click Sign In
    Given I open Google homepage
    When I search for "zilch.com"
    And I click on Zilch website link
    Then I click on the Sign In button

  Scenario: User logs in using API-retrieved credentials
    Given I retrieve login credentials from the API
    And I open the login page
    When I enter the credentials and submit
    Then I should be logged in successfully