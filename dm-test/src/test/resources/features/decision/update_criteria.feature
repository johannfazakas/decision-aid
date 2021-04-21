Feature: Update criteria

  Scenario: Update criteria
    Given I create a decision with name "Hood"

    Given I plan to add a criteria
    And I set the name to "price" on the add criteria input
    And I set the type to "minimum" on the add criteria input
    And I set the weight to 60 on the add criteria input
    And I add the criteria

    Given I plan to add a criteria
    And I set the name to "capacity" on the add criteria input
    And I set the type to "minimum" on the add criteria input
    And I set the weight to 40 on the add criteria input
    And I set the unit of measure to "feet" on the add criteria input
    And I add the criteria

    Given I plan to update the criteria
    And I set the weight to 30 on the update criteria input
    And I set the unit of measure to "mc/h" on the update criteria input
    When I update the criteria
    Then the response is 200
    And the criteria weight is 30
    And the criteria unit of measure is "mc/h"

    Given I plan to update the criteria
    And I set the name to "power" on the update criteria input
    And I set the type to "maximum" on the update criteria input
    When I update the criteria
    Then the response is 200
    And the criteria name is "power"

    When I get the decision
    And I peek at the criteria with name "power"
    Then the criteria weight is 30
    And the criteria unit of measure is "mc/h"
    And the criteria type is "maximum"

  Scenario: Update criteria with invalid weight
    Given I create a decision with name "TV"

    Given I plan to add a criteria
    And I set the name to "screen size" on the add criteria input
    And I set the weight to 40 on the add criteria input
    And I set the type to "maximum" on the add criteria input
    When I add the criteria

    Given I plan to update the criteria
    And I set the weight to 0 on the update criteria input
    When I update the criteria
    Then the response is 400

    Given I plan to update the criteria
    And I set the weight to 101 on the update criteria input
    Then the response is 400

  Scenario: Update criteria with invalid type
    Given I create a decision with name "TV"

    Given I plan to add a criteria
    And I set the name to "screen size" on the add criteria input
    And I set the weight to 40 on the add criteria input
    And I set the type to "maximum" on the add criteria input
    And I add the criteria

    When I plan to update the criteria
    And I set the type to "awesome" on the update criteria input
    When I update the criteria
    Then the response is 400

  Scenario: Update criteria when decision not found
    Given I create a decision with name "TV"

    Given I plan to add a criteria
    And I set the name to "screen size" on the add criteria input
    And I set the type to "maximum" on the add criteria input
    And I set the weight to 40 on the add criteria input
    And I add the criteria

    Given I plan to update the criteria
    And I set the weight to 50 on the update criteria input
    When I update the criteria by random decision
    Then the response is 404

  Scenario: Update criteria when criteria not found
    Given I create a decision with name "TV"

    Given I plan to add a criteria
    And I set the name to "screen size" on the add criteria input
    And I set the type to "maximum" on the add criteria input
    And I set the weight to 40 on the add criteria input

    Given I plan to update the criteria
    And I set the weight to 50 on the update criteria input
    When I update the criteria by random criteria
    Then the response is 404