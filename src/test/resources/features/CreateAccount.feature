Feature: Create account
  In order to make actions I need to create an account and authenticate it

  @UI
  Scenario: Create account from backend
    When I create a new account from backend
    And I generate token for new account
    And I login into application
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