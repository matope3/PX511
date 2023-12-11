Feature: Enter login details SQLi

  @smoke
  @e2e
  Scenario Outline: Normal Login
    Given I start the application
    When I click email field
    And I enter email <email>
    And I close the keyboard
    And I click password field
    And I enter password <password>
    And I close the keyboard
    And I click sign in button
    Then I expect to see <successfull> login message
    Examples:
      | email           | password | successfull |
      | admin@admin.com | admin    | true        |
      | user@user.com   | user     | true        |
      | sdjgsjklbskjbs  | 12345678 | false       |