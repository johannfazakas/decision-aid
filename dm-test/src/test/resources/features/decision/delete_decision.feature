Feature: Delete decision

  Scenario: Delete decision
    When I create a decision with name "fridge"

    When I delete the decision
    Then the response is 204
    When I get the decision
    Then the response is 404

  Scenario: Delete decision when not found
    When I delete a decision by random id
    Then the response is 404
