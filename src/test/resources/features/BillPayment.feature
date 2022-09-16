#Author: Shukla, Abhishek
Feature: Bill_Payment :  Transfer fund using Bill payment service from Para bank application

  @integrate
  Scenario Outline: Register Users(Sender,Payee) using Random user generation API and pay bill from  Sender to Payee and validate the fund transfer to the right person.
    Given I have the end point of random user generation API
    And I call the API to generate user as a Sender
    Given I open the Para bank Application
    When I click on user registration page
    And I enter the sender details and press save button
    Then I see the user registration confirmation message prompt on screen as <message>
    Given I have the end point of random user generation API
    And I call the API to generate user as a Payee
    When I click on bill payment link
    And I see the sender account details
    #And I enter the login details of Sender
    #And I click on Login IN button
    #Then I see the user logged in application
    And I enter the bill payment details as <amount>
    And I press submit button
    And I verify the transaction details as <transactionalAmount>
    Examples:
      | message                                                      | amount | transactionalAmount   |
      | Your account was created successfully. You are now logged in.| 500    | $500.00               |
