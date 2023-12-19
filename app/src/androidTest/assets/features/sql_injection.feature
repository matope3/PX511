Feature: Deny login for SQL injection

  @smoke
  @e2e
  @sqli
  @login
  Scenario Outline: SQL injection is attempted : <attempted>
    Given I start the application
    When I click on email field
    And I enter an email <email>
    And I close the keyboard
    And I click on password field
    And I enter a password <password>
    And I close the keyboard
    And I click on sign in button
    Then I expect to deny login if SQL injection is <attempted> attempted
    Examples:
      | email                       | password                  | attempted |
      | somelongemail@mail.com      | 12345678                  | false     |
      | test@gmail.com              | somePassWithVeryLongValue | false     |
      | test@gmail.com' or 1=1 -- - | 12345678                  | true      |
      | test@gmail.com              | 12345678' or 1=1 -- -     | true      |