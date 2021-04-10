Feature: Delete decision

  Scenario: Delete decision
    When I create a decision with name "fridge"
    Then the response is 201
    When I delete the decision
    Then the response is 204
    When I get the decision
    Then the response is 404

  Scenario: Delete decision when not found
    When I delete a decision with random id
    Then the response is 404
