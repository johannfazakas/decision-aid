Feature: Get decision

  Scenario: Get decision
    Given I create a decision with name "phone"
    When I add a criteria with name "price" and weight 60

    When I get the decision
    Then the response is 200
    And the decision name is "phone"
    And the decision has 1 criteria
    Given I peek at the criteria with name "price"
    Then the criteria weight is 60

  Scenario: Get decision when not found
    When I get a decision by random id
    Then the response is 404
