Feature: List decisions

  Scenario: List decisions
    Given I create a decision with name "Washing machine"
    And I add a criteria with name "price" and weight 50

    Given I add a criteria with name "capacity" and weight 60

    Given I plan to add an alternative
    And I set the name "Bosch IREW85" on the add alternative input
    And I add the alternative

    Given I plan to create a decision
    And I set the name "Dryer" on the create decision input
    And I set the description "for clothes" on the create decision input
    And I create the decision

    When I list the decisions
    Then the response is 200
    And the decisions count is 2

    Given I peek at the decision with name "Washing machine"

    Then the decision has 2 criteria
    Given I peek at the criteria with name "price"
    Then the criteria weight is 50

    Then the decision has 1 alternatives
    Given I peek at the alternative with name "Bosch IREW85"

    Given I peek at the decision with name "Dryer"
    Then the decision description is "for clothes"

  Scenario: List decisions when empty
    When I list the decisions
    Then the response is 200
    And the decisions count is 0
