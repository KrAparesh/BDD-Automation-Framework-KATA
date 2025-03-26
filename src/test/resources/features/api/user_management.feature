Feature: User Management API

  Scenario: View user details
    Given a user is authenticated and the API base URI is set
    When they send a GET request to "/api/users"
    Then the response status code should be 200
    And the response should contain "user"

  Scenario: Create a new user
    Given a user is authenticated and the API base URI is set
    When they send a POST request to "/api/admin/users" with payload:
      """
      {
        "login": "asdfd",
        "firstName": "jhfhjj",
        "lastName": "dfgtr",
        "email": "astdf@localhost.com",
        "createdDate": "",
        "imageUrl": "",
        "activated": true,
        "langKey": "en",
        "createdBy": "system",
        "lastModifiedBy": "system",
        "authorities": [
          "ROLE_USER",
          "ROLE_ADMIN"
        ]
      }
      """
    Then the response status code should be 201
    And the response should contain "id"

  Scenario: User details not found
    Given a user is authenticated and the API base URI is set
    When they send a GET request to "/api/admin/users/999"
    Then the response status code should be 404
    And the response should contain "error"

  Scenario: Unsuccessful user creation due to invalid data
    Given a user is authenticated and the API base URI is set
    When they send a POST request to "/api/admin/users" with payload:
      """
      {
        "name": "",
        "email": "invalid-email",
        "password": ""
      }
      """
    Then the response status code should be 400
