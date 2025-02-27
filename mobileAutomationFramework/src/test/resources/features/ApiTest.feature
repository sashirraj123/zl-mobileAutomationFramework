@api_test

Feature: Api test to retrieve Api Mock service response

    Scenario: Retrieve Api Mock service response
        Given I send a request to get authentication credentials
        Then the response status code should be 200
        And the response should contain a valid username and password
