Feature: Create decision

  Scenario: Create decision
    Given I plan to create a decision
    And I set the decision name to "Fridge"
    And I set the decision description to "I want my meat to be cold"
    When I create the decision
    Then the response is 201

    When I get the decision
    Then the response is 200
    And the decision name is "Fridge"
    And the decision description is "I want my meat to be cold"
    And the decision status is "define"

  Scenario: Create decision with invalid name
    Given I plan to create a decision
    When I create the decision
    Then the response is 400

    Given I plan to create a decision
    And I set the decision name to ""
    And I create the decision
    Then the response is 400
