Feature: Profile Page Test

  Scenario: Update user profile
    Given the user is on the profile page
    When the user updates the profile details
    And clicks the Update Profile button
    Then the profile update should be successful