Feature: List decisions

  Scenario: List decisions
    When I create a decision with name "washing machine"
    Then the response is 201
    When I create a decision with name "dryer"
    Then the response is 201
    When I list the decisions
    Then the response is 200
    And the decisions count is 2
    Given I peek at the decision with name "washing machine"
    Given I peek at the decision with name "dryer"

  Scenario: List decisions when empty
    When I list the decisions
    Then the response is 200
    And the decisions count is 0
