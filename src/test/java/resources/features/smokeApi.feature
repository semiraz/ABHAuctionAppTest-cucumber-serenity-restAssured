Feature: Verification if user can add item as seller and bid on other products

  Scenario Outline: E2E test flow
    Given User has created an account with first name <firstName> and last name <lastName>
    And Logged in with username and password
    When User add item <itemName> to sell with photos, starting price and fill credit card and shipping form
    Then Item <itemName> is successfully added and ready for auction
    When User find wanted item <itemToBid>
    And User place the highest bid on the item
    Then Notification with successfully placed highest bid is received
    And User is logged out

    Examples:
      | firstName | lastName | itemName | itemToBid |
      | Maja      | Mljekic   | Scarf    | Watch     |