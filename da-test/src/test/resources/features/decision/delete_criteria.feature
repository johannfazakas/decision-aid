Feature: Delete criteria

  Scenario: Delete criteria
    Given I plan to create a decision
    And I set the decision name to "Washing machine"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 60
    And I set the criteria type to "minimum"
    And I add the criteria

    Given I plan to add a criteria
    And I set the criteria name to "capacity"
    And I set the criteria weight to 40
    And I set the criteria type to "minimum"
    And I add the criteria

    Given I get the decision
    Then the decision has 2 criteria

    When I delete the criteria with name "capacity"
    Then the response is 204

    When I get the decision
    Then the decision has 1 criteria
    And I peek at the criteria with name "price"
