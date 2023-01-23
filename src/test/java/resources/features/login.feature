Feature: Login Functionality

  @Test_01 @Positive
  Scenario Outline: Verify if user can successfully login with valid data
    Given User has created an account with first name <firstName>, last name <lastName>, email <username> and password <password>
    And New user <fullName> has created
    When User is on the login page and enters <username> and <password>
    Then User is successfully logged in

    Examples:
      | firstName | lastName | fullName | username          | password |
      | Mija      | Kia      | Mija Kia | mmija123@gmail.com | Pass123* |

  @Test_02 @Negative
  Scenario Outline: Verify if user can login with invalid data
    When User is on the login page and enters <username> and <password>
    Then User is not logged in with status <status>
    And User receives <message>


    Examples:
      | username           | password | message                                          | status    |
      | mel_nova.gmail.com | Pass123* | There is no user with email: mel_nova.gmail.com! | NOT_FOUND |
      | mmija123@gmail.com  | pass123* | Bad credentials                                  | 401       |

  @Test_03 @Negative
  Scenario Outline: Verify if user can login with deactivated account
    Given User is on the login page and enters <username> and <password>
    When User deactivate account
    And User is on the login page and enters <username> and <password>
    Then User is not logged in with status <status>
    And User receives <message>

    Examples:
      | username          | password | message                 | status    |
      | mmija123@gmail.com | Pass123* | Account is deactivated! | FORBIDDEN |