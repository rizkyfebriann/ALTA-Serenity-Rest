Feature: Request to API Reqres

  Scenario Outline: Get list user
    Given Get list user with parameter "<parameter>"
    When Send request get list user
    Then status code should be 200 OK
    And response body should contain First name "<firstName>" and Last name "<lastName>"
    And Get list user assert json validation
  Examples:
    |parameter |firstName|lastName|
    |1         |George   |Bluth   |
    |2         |Michael  |Lawson  |

  Scenario: Create user (POST)
    Given Post create new user with valid json file
    When Send request post create user
    Then status code should be 201 Created
    And response body should contain name "Rizky Febrian" and job "QA Engineer"
    And post create user json validation

  Scenario Outline: Put update user
    Given Put update user with id <id> and with valid json file
    When Send request put update user
    Then status code should be 200 OK
    And response body should contain name "Rizky Febrian Update" and job "QA Engineer Update"
  Examples:
    |id|
    |1 |
    |2 |

  Scenario Outline: Delete user
    Given Delete user with id <id>
    When send request delete user
    Then Status code should be 204 No Content
  Examples:
    |id|
    |1|
