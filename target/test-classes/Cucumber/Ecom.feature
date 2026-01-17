@tag
Feature: Purchase the product from ecommerce store

Background: 
Given Navigate to ecommerce page

@Regression
Scenario Outline: Positive test of submitting the order

Given Login with "<email>" and "<password>"
When I add "<product>" to cart
And checkout "<product>" and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed in thank you page

Examples:
| email                 | password    | product      |
| buvanshetty@gmail.com | Shravan@1   | ZARA COAT 3  |
