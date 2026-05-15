Feature: Bookstore
  Book management for an account

  @BE
  Scenario: Books flow from backend
    When I create a new account from backend
    And I generate token for new account
    And books are added to account
    And book "9781449325862" is updated for account
    And I get account
    And book "9781449325862" is deleted from account
    And I get account
    And all books are deleted from account
    And I get account
    Then I delete account from backend
    And I get account
#
#
#  Scenario: Create account with valid data
#    Given I am on the registration page
#    When I enter valid registration details
#    And I submit the registration form
#    Then I should see a confirmation message
#
#  Scenario: Create account with missing required fields
#    Given I am on the registration page
#    When I leave required fields empty
#    And I submit the registration form
#    Then I should see error messages for the missing fields
#
#  Scenario: Create account with invalid email format
#    Given I am on the registration page
#    When I enter an invalid email address
#    And I submit the registration form
#    Then I should see an error message for the email field