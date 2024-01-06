Feature: Enter Brute Force Test on Login App Parameters

  @smoke
  @e2e
  Scenario Outline: Malicious Brute Force Login
    Given I start the application
    When I try to logging <number> times
    Then I'm waiting for the login button to be blocked
    Examples:
      | number |
      | 5      |
      | 10     |
      | 15     |
      | 20     |