Feature: Delete criteria

  Scenario: Delete criteria
    Given I create a decision with name "Washing machine"

    Given I plan to add a criteria
    And I set the name to "price" on the add criteria input
    And I set the weight to 60 on the add criteria input
    And I set the type to "minimum" on the add criteria input
    And I add the criteria

    Given I plan to add a criteria
    And I set the name to "capacity" on the add criteria input
    And I set the weight to 40 on the add criteria input
    And I set the type to "minimum" on the add criteria input
    And I add the criteria

    Given I get the decision
    Then the decision has 2 criteria

    When I delete the criteria with name "capacity"
    Then the response is 204

    When I get the decision
    Then the decision has 1 criteria
    And I peek at the criteria with name "price"
