Feature: Testing Reqres.in (2)

  @case_positive @get_single_user
  Scenario Outline: Get single user (registered)
    Given Get single user with parameter "<id>"
    When Send request get single user
    Then API response status code should be 200 OK
    And response body should contain Email "<Email>" , First Name "<first_name>" and Last Name "<last_name>"
    Examples:
    |id |Email                   |first_name|last_name|
    |2  |janet.weaver@reqres.in  |Janet     |Weaver   |
    |7  |michael.lawson@reqres.in|Michael   |Lawson   |
    |11 |george.edwards@reqres.in|George    |Edwards  |

  @case_negative @get_single_user
  Scenario: Get single user not found (unregistered)
    Given Get single user with id "20"
    When Send request get single user
    Then API response status code should be 404 not found

  @case_negative @get_single_user
  Scenario: Get single user with invalid parameter
    Given Get single user with parameter "abc"
    When Send request get single user
    Then API response status code should be 404 not found

  @case_positive @get_list_resources
  Scenario Outline: Get list resources
    Given Get list resources with parameter "<page>"
    When Send request get list resources
    Then API response status code should be 200 OK
    And response body should contain name "<name>" , year <year> and color "<color>"
    And Get list resources assert json validation
    Examples:
      |page |name          |year|color  |
      |1    |true red      |2002|#BF1932|
      |2    |blue iris     |2008|#5A5B9F|

  @case_negative @get_list_resources
  Scenario: Get list resources with invalid parameter
    Given Get list resources with parameter "abcdef"
    When Send request get list resources
    Then API response status code should be 404 not found
#yang terjadi adalah bisa di get (status code 200 OK), padahal seharusnya 404 not found

  @case_positive @get_single_resources
  Scenario Outline: Get single resources
    Given Get single resources with parameter "<id>"
    When Send request get single resources
    Then API response status code should be 200 OK
    And response body should contain name "<name>" , year <year> , color "<color>" and pantone value "<pantone_value>"
    Examples:
      |id   |name         |year|color  |pantone_value|
      |5    |tigerlily    |2004|#E2583E|17-1456      |
      |11   |turquoise     |2010|#45B5AA|15-5519      |

  @case_negative @get_single_resources
  Scenario: Get single resources with invalid parameter
    Given Get single resources with parameter "abc"
    When Send request get single resources
    Then API response status code should be 404 not found

  @case_positive @patch_update
  Scenario Outline: Patch update user
    Given Patch update user with id "<id>" and with valid json file
    When Send request patch update user
    Then API response status code should be 200 OK
    And response body should contain name "Rizky Febrian" and job "QA Engineer (Aamiin)"
  Examples:
    |id |
    |5  |
    |10 |

  @case_negative @put_update
  Scenario: Put update user without name
    Given Put update user with id "10" and with UpdateUserWithoutName.json file
    When Send request PUT for update user
    Then API response status code should be 200 OK
    And response body should contain job "QA (Edited)"

  @case_negative @put_update
  Scenario: Put update user without job
    Given Put update user with id "8" and with UpdateUserWithoutJob.json file
    When Send request PUT for update user
    Then API response status code should be 200 OK
    And response body should contain name "Rizky (Edited)"

  @case_negative @delete
  Scenario: Delete user which id not found (unregistered)
    Given delete user with id "20"
    When Send request delete user
    Then API response status code should be 404 not found
#yang terjadi adalah bisa dihapus (status code 204 no content), padahal seharusnya 404 not found

  @case_negative @delete
  Scenario: Delete user with invalid parameter
    Given delete user with parameter "abc"
    When Send request delete user
    Then API response status code should be 404 not found
#yang terjadi adalah bisa dihapus (status code 204 no content), padahal seharusnya 404 not found

  @case_positive @login
  Scenario: Login with valid email and password
    Given request post login user with valid data on json file
    When Send request post login user
    Then API response status code should be 200 OK
    And post login user assert json validation

  @case_negative @login
  Scenario: Login with valid email and invalid password
    Given request post login user with invalid password on json file
    When Send request post login user
    Then API response status code should be 404 not found
#yang terjadi adalah bisa login (status code 200 OK), padahal seharusnya 404 not found

  @case_negative @login
  Scenario: Login with invalid email and valid password
    Given request post login user with invalid email on json file
    When Send request post login user
    Then API response status code should be 400 bad request

  @case_negative @login
  Scenario: Login with invalid email and invalid password
    Given request post login user with invalid data on json file
    When Send request post login user
    Then API response status code should be 400 bad request

  @case_negative @login
  Scenario: Login with empty email
    Given request post login user with empty email on json file
    When Send request post login user
    Then API response status code should be 400 bad request

  @case_negative @login
  Scenario: Login with empty password
    Given request post login user with empty password on json file
    When Send request post login user
    Then API response status code should be 400 bad request

  @case_positive @register
  Scenario: Register with valid email and password
    Given request post register user with valid data on json file
    When Send request post register user
    Then API response status code should be 200 OK
    And response body should contain id 4 and token "QpwL5tke4Pnpja7X4"

  @case_negative @register
  Scenario: Register with invalid email and valid password
    Given request post register user with invalid email on json file
    When Send request post register user
    Then API response status code should be 400 bad request

  @case_negative @register
  Scenario: Register with empty email
    Given request post register user with empty email on json file
    When Send request post register user
    Then API response status code should be 400 bad request

  @case_negative @register
  Scenario: Register with empty password
    Given request post register user with empty password on json file
    When Send request post register user
    Then API response status code should be 400 bad request