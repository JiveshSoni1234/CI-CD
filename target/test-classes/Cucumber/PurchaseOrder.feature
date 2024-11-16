
@tag
Feature: Purchase the order from ecommmerce website
  I want to use this template for my feature file

Background: 
Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of purchasing the order
    Given Logged in with username <name> and password<password>
    When I add the product < productname > to cart
    And checkout< productname > and submit the order
    Then "THANKYOU FOR THE ORDER" message is displayed on the confirmationPage

    Examples: 
      | name                  |  password   | productname  |
      | rahulshetty12343214@gmail.com | IamKing@000 | ZARA COAT 3 |
      
