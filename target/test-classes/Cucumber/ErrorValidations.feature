@tag
Feature: Purchase the product from ecommerce store


@Errorvalidation
Scenario Outline: Error validations

Given Navigate to ecommerce page
When Login with "<email>" and "<password>"
Then "Incorrect email or password." message is displayed 

Examples:
| email                 | password    | 
| buvanshetty@gmail.com | Shravan     | 