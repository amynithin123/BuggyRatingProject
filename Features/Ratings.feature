Feature: Buggy Cars Rating -Positive

  Background: Below are the common steps for each scenario
    Given User Launch browser
    When User opens URL "https://buggy.justtestit.org"
    Then User can view the Bugging Rating login page

  @regression
  Scenario Outline: Validate the ranking of all registered models- display the details of top ranked Car
    When User enters Login code as "<loginCode>"
    And User enters Password as "<password>" to login
    And User clicks on Login to login to application
    Then validate that "<firstname>" can successfully login to the application
    When User clicks on Buggy Rating
    And User clicks on Overall Rating
    Then validate the ranking of the cars
    And close the browser

    Examples: 
      | firstname | lastname | password      | loginCode |
      | Amy       | Nithin   | BALA.mani123! | AmyN      |

  