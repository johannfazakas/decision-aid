Feature: List decisions

  Scenario: List decisions
    Given I create a decision with name "washing machine"
    And I add a criteria with name "price" and weight 50
    And I create a decision with name "dryer"

    When I list the decisions
    Then the response is 200
    And the decisions count is 2
    Given I peek at the decision with name "washing machine"
    Then the decision has 1 criteria
    Given I peek at the criteria with name "price"
    And the criteria weight is 50

  Scenario: List decisions when empty
    When I list the decisions
    Then the response is 200
    And the decisions count is 0
