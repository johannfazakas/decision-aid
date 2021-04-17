Feature: Delete criteria

  Scenario: Delete criteria
    Given I create a decision with name "Washing machine"
    And I add a criteria with name "price" and weight 60
    And I add a criteria with name "capacity" and weight 40

    Given I get the decision
    Then the decision has 2 criteria

    When I delete the criteria with name "capacity"
    Then the response is 204

    When I get the decision
    Then the decision has 1 criteria
    And I peek at the criteria with name "price"
