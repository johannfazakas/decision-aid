Feature: Get decision

  Scenario: Get decision
    When I create a decision with name "phone"
    Then the response is 201
    When I get the decision
    Then the response is 200
    And the decision name is "phone"

  Scenario: Get decision when not found
    When I get a decision by random id
    Then the response is 404
