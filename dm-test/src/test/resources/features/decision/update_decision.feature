Feature: Update decision

  Scenario: Update decision
    Given I plan to create a decision
    And I set the name "Keyboard" on the create decision input
    And I set the description "I want it to be clicky" on the create decision input
    And I create the decision

    Given I plan to update the decision
    And I set the name "Keyboard updated" on the update decision input
    When I update the decision
    Then the response is 200
    And the decision name is "Keyboard updated"
    And the decision description is "I want it to be clicky"

    Given I plan to update the decision
    And I set the description "description updated" on the update decision input
    When I update the decision
    Then the response is 200
    And the decision name is "Keyboard updated"
    And the decision description is "description updated"

    When I get the decision
    Then the response is 200
    And the decision name is "Keyboard updated"
    And the decision description is "description updated"

  Scenario: Update decision with invalid name
    Given I create a decision with name "Mouse"
    Given I plan to update the decision
    And I set the name "" on the update decision input
    When I update the decision
    Then the response is 400

  Scenario: Update decision when decision not found
    Given I plan to update the decision
    And I set the name "TV" on the update decision input
    When I update a decision by random id
    Then the response is 404
