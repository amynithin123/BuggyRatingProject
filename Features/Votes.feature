Feature: Adding Votes by a newly regsitered user - Positive

  Background: Below are the common steps for each scenario
    Given User Launch browser
    When User opens URL "https://buggy.justtestit.org"
    Then User can view the Bugging Rating login page

  @regression
  Scenario Outline: Validate that a newly registered user is able to add vote for the popular model sucessfully
    When User clicks on Register
    And Add unique login credentials to register and login "<firstname>" "<lastname>" "<password>" to vote
    When User clicks on Buggy Rating
    And User clicks on Popular Model
    Then User enters the comment "<comment>" and clicks on Vote to validate the comment has been added
    And close the browser

    Examples: 
      | firstname | lastname | password      | loginCode | comment  |
      | Amy       | Nithin   | BALA.mani123! | AmyN      | Nice car |
