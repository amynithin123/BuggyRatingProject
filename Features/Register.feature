Feature: User Registartion - Positive and Negative

  Background: Below are the common steps for each scenario
    Given User Launch browser
    When User opens URL "https://buggy.justtestit.org"
    Then User can view the Bugging Rating login page

  @regression
  Scenario Outline: Registartion by a user with unique login, valid first name, last name and password- positive
    When User clicks on Register
    And User enters Login
    And User enters First Name as "<firstname>"
    And User enters Last Name as "<lastname>"
    And User enters Password as "<password>" and Confirm Password as "<password>"
    And User clicks on Register to submit registration
    Then validate that the user registration is successful "Registration is successful"
    And close the browser

    Examples: 
      | firstname | lastname | password      |
      | Amy       | Nithin   | BALA.mani123! |

  @regression
  Scenario Outline: Validate the User already exists alert on unsuccessful registration by an existing user-negative
    When User clicks on Register
    And User enters LoginCode "<loginCode>"
    And User enters First Name as "<firstname>"
    And User enters Last Name as "<lastname>"
    And User enters Password as "<password>" and Confirm Password as "<password>"
    And User clicks on Register to submit registration
    Then Validate the "<errormsg>" exception message is displayed
    And close the browser

    Examples: 
      | firstname | lastname | password      | loginCode |  | errormsg                                     |
      | Amy       | Nithin   | BALA.mani123! | AmyN      |  | UsernameExistsException: User already exists |

  @regression
  Scenario Outline: Validate the password mismatch alert on unsuccessful registration-negative
    When User clicks on Register
    And User enters LoginCode "<loginCode>"
    And User enters First Name as "<firstname>"
    And User enters Last Name as "<lastname>"
    And User enters Password as "<password>" and mismtach Password as "<confirmpassword>"
    And User clicks on Register to submit registration
    Then Validate the "<errormsg>" exception message is displayed
    And close the browser

    Examples: 
      | firstname | lastname | password      | confirmpassword | loginCode |  | errormsg               |
      | Amy       | Nithin   | BALA.mani123! | abc             | AmyN      |  | Passwords do not match |

  @regression
  Scenario Outline: Validate the minimum field size for password alert on unsuccessful registration-negative
    When User clicks on Register
    And User enters LoginCode "<loginCode>"
    And User enters First Name as "<firstname>"
    And User enters Last Name as "<lastname>"
    And User enters Password as "<password>" and mismtach Password as "<confirmpassword>"
    And User clicks on Register to submit registration
    Then Validate the "<errormsg>" exception message is displayed
    And close the browser

    Examples: 
      | firstname | lastname | password | confirmpassword | loginCode |  | errormsg                                                                                        |
      | Amy       | Nithin   | abc      | abc             | AmyN      |  | InvalidParameter: 1 validation error(s) found. - minimum field size of 6, SignUpInput.Password. |

  @regression
  Scenario Outline: Validate the Login with more than 50 characters long alert on unsuccessful registration-negative
    When User clicks on Register
    And User enters LoginCode "<loginCode>" with more than 50 characters
    And User enters First Name as "<firstname>"
    And User enters Last Name as "<lastname>"
    And User enters Password as "<password>" and Confirm Password as "<password>"
    And User clicks on Register to submit registration
    Then Validate the "<errormsg>" exception message is displayed
    And close the browser

    Examples: 
      | firstname | lastname | password | loginCode |  | errormsg                                     |
      | Amy       | Nithin   | abc      | AmyN      |  | Login cannot be more than 50 characters long |


