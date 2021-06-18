Feature: Delete decision

  Scenario: Delete decision
    Given I use a valid user

    Given I plan to create a decision
    And I set the decision name to "Fridge"
    And I create the decision

    When I delete the decision
    Then the response is 204
    When I get the decision
    Then the response is 404

  Scenario: Delete decision when not found
    Given I use a valid user

    Given I use a nonexistent decision
    When I delete the decision
    Then the response is 404
