Feature: User Login and logout of the application by a registered user

  Background: Below are the common steps for each scenario
    Given User Launch browser
    When User opens URL "https://buggy.justtestit.org"
    Then User can view the Bugging Rating login page

  @regression
  Scenario Outline: Login to Buggy Rating application with valid login code and valid password-Positive
    When User enters Login code as "<loginCode>"
    And User enters Password as "<password>" to login
    And User clicks on Login to login to application
    Then validate that "<firstname>" can successfully login to the application
    And User log out of the application
    And close the browser

    Examples: 
      | firstname | lastname | password      | loginCode |
      | Amy       | Nithin   | BALA.mani123! | AmyN      |

  @regression
  Scenario Outline: Login to Buggy Rating application with valid login code and invalid password- Negative
    When User enters Login code as "<loginCode>"
    And User enters Password as "<password>" to login
    And User clicks on Login to login to application
    Then validate that invalid username or password "<errormsg>" is displayed
    And close the browser

    Examples: 
      | firstname | lastname | password         | loginCode |  | errormsg                  |
      | Amy       | Nithin   | invalidpassword1 | AmyN      |  | Invalid username/password |

  @regression
  Scenario Outline: Login to Buggy Rating application with valid login code and invalid password - Negative
    When User enters Login code as "<loginCode>"
    And User enters Password as "<password>" to login
    And User clicks on Login to login to application
    Then validate that invalid username or password "<errormsg>" is displayed
    And close the browser

    Examples: 
      | firstname | lastname | password | loginCode         |  | errormsg                  |
      | Amy       | Nithin   | BALA     | invalidlogincode1 |  | Invalid username/password |
