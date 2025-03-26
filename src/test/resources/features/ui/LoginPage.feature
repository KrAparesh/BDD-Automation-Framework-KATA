Feature: Login Functionality

  Scenario: Logging in with valid credentials
    Given the user is on the login page
    When the user enters valid username and password
    And clicks the login button
    Then the user is redirected to the dashboard

  Scenario: Logging in with invalid credentials
    Given the user is on the login page
    When the user enters invalid username and password
    And clicks the login button