Feature: Update criteria

  Scenario: Update criteria
    Given I create a decision with name "hood"
    And I add a criteria with name "price" and weight 60
    And I add a criteria with name "capacity" and weight 40

    When I update the criteria weight to 30
    Then the response is 200
    And the criteria weight is 30

    When I get the decision
    And I peek at the criteria with name "capacity"
    Then the criteria weight is 30

  Scenario: Update criteria with invalid weight
    Given I create a decision with name "TV"
    And I add a criteria with name "screen size" and weight 40

    When I update the criteria weight to -1
    Then the response is 400
    When I update the criteria weight to 101
    Then the response is 400

  Scenario: Update criteria when decision not found
    Given I create a decision with name "TV"
    And I add a criteria with name "screen size" and weight 40

    Given I plan to update the criteria
    And I set the weight 50 on the update criteria input
    When I update the criteria by random decision
    Then the response is 404

  Scenario: Update criteria when criteria not found
    Given I create a decision with name "TV"
    And I add a criteria with name "screen size" and weight 40

    Given I plan to update the criteria
    And I set the weight 50 on the update criteria input
    When I update the criteria by random criteria
    Then the response is 404