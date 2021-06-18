Feature: Update decision

  Scenario: Update decision
    Given I use a valid user

    Given I plan to create a decision
    And I set the decision name to "Keyboard"
    And I set the decision description to "I want it to be clicky"
    And I create the decision

    Given I plan to update the decision
    And I set the decision name to "Keyboard updated"
    When I update the decision
    Then the response is 200
    And the decision name is "Keyboard updated"
    And the decision description is "I want it to be clicky"

    Given I plan to update the decision
    And I set the decision description to "description updated"
    When I update the decision
    Then the response is 200
    And the decision name is "Keyboard updated"
    And the decision description is "description updated"

    When I get the decision
    Then the response is 200
    And the decision name is "Keyboard updated"
    And the decision description is "description updated"

  Scenario: Update decision with invalid name
    Given I use a valid user

    Given I plan to create a decision
    And I set the decision name to "Mouse"
    And I create the decision
    Given I plan to update the decision
    And I set the decision name to ""
    When I update the decision
    Then the response is 400

  Scenario: Update decision when decision not found
    Given I use a valid user

    Given I use a nonexistent decision
    Given I plan to update the decision
    And I set the decision name to "TV"
    When I update the decision
    Then the response is 404
