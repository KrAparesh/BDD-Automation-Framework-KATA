Feature: Registration Functionality

  Scenario: Registering a new user successfully
    Given the user is on the registration page
    When the user enters valid registration details
    When the user enters invalid registration details
    And clicks the register button
    Then the user is redirected to the registration success page

#  Scenario: Registration fails due to missing required details
#    Given the user is on the registration page
#    When the user enters invalid registration details
#    And clicks the register button
#    Then the user is redirected to the registration success page