@Regression
Feature: Users profile information test
  Verification if a user can update their personal information, shipping address and credit card information

  @Test_04 @Positive
  Scenario: Verify if user can update personal info request
    Given a user Lalo Toko has created an account with lalo_tako@gmail.com
    And user is logged in
    When user update personal information
    Then updated personal information are saved

  @Test_05 @Positive
  Scenario: Verify if user can update address
    Given user is logged in
    When user update address information
    Then updated address information are saved


  @Test_06 @Positive
  Scenario: Verify if user can update credit card request
    Given user is logged in
    When user update credit card information
    Then updated credit card information are saved