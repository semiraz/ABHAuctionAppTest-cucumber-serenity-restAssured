@Regression
Feature: Login Functionality
  Verification if a user can successfully create an account and login with valid data,
  verification if a user would receive a correct error message in case of inputting invalid data and
  verification if a user with a deactivated account cannot login any more

  @Test_01 @Positive
  Scenario Outline: Verify if user can successfully login with valid data
    Given a user has created an account with first name <firstName> and last name <lastName>
    And new user <fullName> has created
    When user is on the login page entering their username and password
    Then user is successfully logged in

    Examples:
      | firstName | lastName | fullName |
      | Jane      | Anderson      | Jane Anderson |

  @Test_02 @Negative
  Scenario Outline: Verify if user can login with invalid data
    Given new user Mel Nova has already created
    When user is on the login page and enters <username> and <password>
    Then user is not logged in with status <status>
    And user receives a message <message>


    Examples:
      | username           | password | message                                          | status    |
      | mel_nova.gmail.com | Pass123* | There is no user with email: mel_nova.gmail.com! | NOT_FOUND |
      | mel_nova@gmail.com | pass123* | Bad credentials                                  | 401       |

  @Test_03 @Negative
  Scenario Outline: Verify if user can login with deactivated account
    Given a user has created an account with first name <firstName> and last name <lastName>
    And user is on the login page entering their username and password
    When user deactivate an account
    And user is on the login page entering their username and password
    Then user is not logged in with status <status>
    And user receives a message <message>

    Examples:
      | firstName | lastName | message                 | status    |
      | Mellisa      | Lennon   | Account is deactivated! | FORBIDDEN |