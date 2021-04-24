Feature: Update criteria

  Scenario: Update criteria
    Given I plan to create a decision
    And I set the decision name to "Hood"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria type to "minimum"
    And I set the criteria weight to 60
    And I add the criteria

    Given I plan to add a criteria
    And I set the criteria name to "capacity"
    And I set the criteria type to "minimum"
    And I set the criteria weight to 40
    And I set the criteria unit of measure to "feet"
    And I add the criteria

    Given I plan to update the criteria
    And I set the criteria weight to 30
    And I set the criteria unit of measure to "mc/h"
    When I update the criteria
    Then the response is 200
    And the criteria weight is 30
    And the criteria unit of measure is "mc/h"

    Given I plan to update the criteria
    And I set the criteria name to "power"
    And I set the criteria type to "maximum"
    When I update the criteria
    Then the response is 200
    And the criteria name is "power"

    When I get the decision
    And I peek at the criteria with name "power"
    Then the criteria weight is 30
    And the criteria unit of measure is "mc/h"
    And the criteria type is "maximum"

  Scenario: Update criteria with invalid weight
    Given I plan to create a decision
    And I set the decision name to "TV"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "screen size"
    And I set the criteria weight to 40
    And I set the criteria type to "maximum"
    When I add the criteria

    Given I plan to update the criteria
    And I set the criteria weight to 0
    When I update the criteria
    Then the response is 400

    Given I plan to update the criteria
    And I set the criteria weight to 101
    Then the response is 400

  Scenario: Update criteria with invalid type
    Given I plan to create a decision
    And I set the decision name to "TV"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "screen size"
    And I set the criteria weight to 40
    And I set the criteria type to "maximum"
    And I add the criteria

    When I plan to update the criteria
    And I set the criteria type to "awesome"
    When I update the criteria
    Then the response is 400

  Scenario: Update criteria when decision not found
    Given I plan to create a decision
    And I set the decision name to "TV"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "screen size"
    And I set the criteria type to "maximum"
    And I set the criteria weight to 40
    And I add the criteria

    Given I use a nonexistent decision

    Given I plan to update the criteria
    And I set the criteria weight to 50
    When I update the criteria
    Then the response is 404

  Scenario: Update criteria when criteria not found
    Given I plan to create a decision
    And I set the decision name to "TV"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "screen size"
    And I set the criteria type to "maximum"
    And I set the criteria weight to 40

    Given I use a nonexistent criteria

    Given I plan to update the criteria
    And I set the criteria weight to 50
    When I update the criteria
    Then the response is 404