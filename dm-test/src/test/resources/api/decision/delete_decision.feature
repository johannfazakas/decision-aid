Feature: Create decision
  Testing create decision operation

  Scenario:
    When I create a decision with name "fridge"
    Then the response is 201
    Given I store the decision as "fridge-decision"
    When I delete the decision "fridge-decision"
    Then the response is 204