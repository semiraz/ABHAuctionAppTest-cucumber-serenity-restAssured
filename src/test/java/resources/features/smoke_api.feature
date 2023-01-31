@Smoke
Feature: Smoke
  Verification if user can add item as seller and bid on other products

  Scenario Outline: E2E test flow
    Given user has created an account with first name <firstName> and last name <lastName>
    And a user logs in with username and password
    And user adds an item <itemName> to sell with photos, starting price, credit card info and their address
    And the item <itemName> is successfully added and ready for auction
    When user finds <itemToBid> item
    And user places the highest bid on the item
    Then user receives success notification
    And user is logged out

    Examples:
      | firstName | lastName | itemName | itemToBid |
      | Jake      | Polka    | Sweater  | Watch     |
      | Wanda     | Smith    | Trousers | Ring      |
