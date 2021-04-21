Feature: Update criteria

  Scenario: Update criteria
    Given I create a decision with name "Hood"
    And I add a criteria with name "price" and weight 60
    And I add a criteria with name "capacity" and weight 40

    Given I plan to update the criteria
    And I set the weight to 30 on the update criteria input
    And I set the unit of measure to "mc/h" on the update criteria input
    When I update the criteria
    Then the response is 200
    And the criteria weight is 30
    And the criteria unit of measure is "mc/h"

    Given I plan to update the criteria
    And I set the name to "power" on the update criteria input
    When I update the criteria
    Then the response is 200
    And the criteria name is "power"

    When I get the decision
    And I peek at the criteria with name "power"
    Then the criteria weight is 30
    And the criteria unit of measure is "mc/h"

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
    And I set the weight to 50 on the update criteria input
    When I update the criteria by random decision
    Then the response is 404

  Scenario: Update criteria when criteria not found
    Given I create a decision with name "TV"
    And I add a criteria with name "screen size" and weight 40

    Given I plan to update the criteria
    And I set the weight to 50 on the update criteria input
    When I update the criteria by random criteria
    Then the response is 404