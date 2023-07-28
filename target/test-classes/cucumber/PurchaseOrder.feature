@tag
Feature: Purchase the order from Ecommerce website
  
  Background: 
  Given I landed on Ecommerce page

   @tag2
  Scenario Outline: Positive test of submitting the order
    Given I logged in with username <name> and password <password>
    When I add the product <product> in cart and checkout the product
    Then "THANKYOU FOR THE ORDER." message is displayed 

    Examples: 
      | name  							| password				| product  				|
      | anshika02@gmail.com | Iamking@123 		| ADIDAS ORIGINAL |
      | ramansingh@gmail.com| Raman123$				| IPHONE 13 PRO		|
      