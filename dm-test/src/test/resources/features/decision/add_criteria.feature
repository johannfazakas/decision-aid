Feature: Add criteria

  Scenario: Add criteria to decision
    Given I plan to create a decision
    And I set the decision name to "Phone"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 100
    And I set the criteria unit of measure to "euro"
    And I set the criteria type to "minimum"
    When I add the criteria
    Then the response is 201
    And the criteria weight is 100
    And the criteria name is "price"
    And the criteria unit of measure is "euro"
    And the criteria type is "minimum"

    When I get the decision
    Then the response is 200
    And the decision has 1 criteria
    Given I peek at the criteria with name "price"
    Then the criteria weight is 100

  Scenario: Add criteria with invalid name
    Given I plan to create a decision
    And I set the decision name to "Keyboard"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria weight to 80
    And I set the criteria type to "minimum"
    When I add the criteria
    Then the response is 400

    Given I plan to add a criteria
    And I set the criteria name to ""
    And I set the criteria type to "maximum"
    And I set the criteria weight to 50
    When I add the criteria
    Then the response is 400

  Scenario: Add criteria with invalid price
    Given I plan to create a decision
    And I set the decision name to "Mouse"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 0
    And I set the criteria type to "minimum"
    When I add the criteria
    Then the response is 400

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 101
    And I set the criteria type to "minimum"
    When I add the criteria
    Then the response is 400

  Scenario: Add criteria without type
    Given I plan to create a decision
    And I set the decision name to "Headphones"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 50
    And I set the criteria unit of measure to "RON"
    When I add the criteria
    Then the response is 400

  Scenario: Add criteria with invalid type
    Given I plan to create a decision
    And I set the decision name to "Mouse pad"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 50
    And I set the criteria type to "awesome"
    When I add the criteria
    Then the response is 400

  Scenario: Add criteria on not found decision
    Given I use a nonexistent decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 50
    And I set the criteria type to "minimum"
    When I add the criteria
    Then the response is 404