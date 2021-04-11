Feature: Add criteria

  Scenario: Add criteria to decision
    Given I create a decision with name "phone"

    When I add a criteria with name "price" and weight 100
    Then the response is 201
    And the criteria weight is 100

    When I get the decision
    Then the response is 200
    And the decision has 1 criteria
    Given I peek at the criteria with name "price"
    Then the criteria weight is 100

  Scenario: Add criteria with invalid name
    Given I create a decision with name "keyboard"

    Given I plan to add a criteria
    And I set the weight 80 on the add criteria input
    When I add the criteria
    Then the response is 400

    Given I plan to add a criteria
    And I set the name "" on the add criteria input
    And I set the weight 50 on the add criteria input
    When I add the criteria
    Then the response is 400

  Scenario: Add criteria with invalid price
    Given I create a decision with name "mouse"

    Given I plan to add a criteria
    And I set the name "price" on the add criteria input
    And I set the weight -1 on the add criteria input
    When I add the criteria
    Then the response is 400

    Given I plan to add a criteria
    And I set the name "price" on the add criteria input
    And I set the weight 101 on the add criteria input
    When I add the criteria
    Then the response is 400

  Scenario: Add criteria on not found decision
    Given I plan to add a criteria
    And I set the name "price" on the add criteria input
    And I set the weight 50 on the add criteria input
    When I add the criteria on a random decision
    Then the response is 404