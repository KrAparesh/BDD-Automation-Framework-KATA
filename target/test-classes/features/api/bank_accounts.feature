Feature: API Testing with Step Definitions
  As an API tester
  I want to test the API endpoints using predefined step definitions
  So that I can validate the functionality of the API

  Background:
    Given a user is authenticated and the API base URI is set

  Scenario: Send a GET request and validate the response status code
    When they send a GET request to "/api/bank-accounts"
    Then the response status code should be 200
    And the response should contain "id"
    And the response should contain "name"
    And the response should contain "balance"

  Scenario: Send a POST request to create a new bank account
    When they send a POST request to "/api/bank-accounts" with payload:
      """
      {
        "name": "John Doe",
        "balance": 1000
      }
      """
    Then the response status code should be 201
    And the response should contain "id"
    And the response should contain "name"
    And the response should contain "balance"

  Scenario: Send a POST request with invalid data and validate the error response
    When they send a POST request to "/api/bank-accounts" with payload:
      """
      {
        "balance": 1000
      }
      """
    Then the response status code should be 400