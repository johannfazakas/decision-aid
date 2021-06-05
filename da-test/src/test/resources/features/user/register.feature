Feature: Register user

  Scenario: Register user
    Given I plan to register a user
    And I set the user email to "me@mail.com"
    And I set the user password to "secret"
    And I set the user confirm password to "secret"

    When I register the user
    Then the response is 201
    And the user email is "me@mail.com"

  Scenario: Register user with passwords mismatch
    Given I plan to register a user
    And I set the user email to "me@mail.com"
    And I set the user password to "secret"
    And I set the user confirm password to "invalid"

    When I register the user
    Then the response is 400

  Scenario: Register user with invalid email
    Given I plan to register a user
    And I set the user email to "me"
    And I set the user password to "secret"
    And I set the user confirm password to "secret"

    When I register the user
    Then the response is 400

  Scenario: Register user with already existing email
    Given I plan to register a user
    And I set the user email to "me@mail.com"
    And I set the user password to "secret"
    And I set the user confirm password to "secret"
    And I register the user

    Given I plan to register a user
    And I set the user email to "me@mail.com"
    And I set the user password to "secret2"
    And I set the user confirm password to "secret2"

    When I register the user
    Then the response is 422
