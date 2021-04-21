Feature: Create decision

  Scenario: Create decision
    Given I plan to create a decision
    And I set the name to "Fridge" on the create decision input
    And I set the description to "I want my meat to be cold" on the create decision input
    When I create the decision
    Then the response is 201

    When I get the decision
    Then the response is 200
    And the decision name is "Fridge"
    And the decision description is "I want my meat to be cold"

  Scenario: Create decision with invalid name
    Given I plan to create a decision
    When I create the decision
    Then the response is 400

    When I create a decision with name ""
    Then the response is 400
