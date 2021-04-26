Feature: Add criteria

  Scenario: Add criteria to decision
    Given I plan to create a decision
    And I set the decision name to "Phone"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 100
    And I set the criteria unit of measure to "euro"
    And I set the criteria type to "minimum"
    When I add the criteria
    Then the response is 201
    And the criteria weight is 100
    And the criteria name is "price"
    And the criteria unit of measure is "euro"
    And the criteria type is "minimum"

    When I get the decision
    Then the response is 200
    And the decision has 1 criteria
    Given I peek at the criteria with name "price"
    Then the criteria weight is 100

  Scenario: Add criteria with invalid name
    Given I plan to create a decision
    And I set the decision name to "Keyboard"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria weight to 80
    And I set the criteria type to "minimum"
    When I add the criteria
    Then the response is 400

    Given I plan to add a criteria
    And I set the criteria name to ""
    And I set the criteria type to "maximum"
    And I set the criteria weight to 50
    When I add the criteria
    Then the response is 400

  Scenario: Add criteria with invalid price
    Given I plan to create a decision
    And I set the decision name to "Mouse"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 0
    And I set the criteria type to "minimum"
    When I add the criteria
    Then the response is 400

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 101
    And I set the criteria type to "minimum"
    When I add the criteria
    Then the response is 400

  Scenario: Add criteria without type
    Given I plan to create a decision
    And I set the decision name to "Headphones"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 50
    And I set the criteria unit of measure to "RON"
    When I add the criteria
    Then the response is 400

  Scenario: Add criteria with invalid type
    Given I plan to create a decision
    And I set the decision name to "Mouse pad"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 50
    And I set the criteria type to "awesome"
    When I add the criteria
    Then the response is 400

  Scenario: Add criteria on not found decision
    Given I use a nonexistent decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 50
    And I set the criteria type to "minimum"
    When I add the criteria
    Then the response is 404

  Scenario: Add criteria when decision was processed
        # create decision
    Given I plan to create a decision
    And I set the decision name to "Light bulb"
    And I create the decision

    # add criteria
    Given I plan to add a criteria
    And I set the criteria name to "Price"
    And I set the criteria weight to 100
    And I set the criteria type to "minimum"
    And I add the criteria

    # add alternatives
    Given I plan to add an alternative
    And I set the alternative name to "LED 6W"
    And I add the alternative

    # set the property
    Given I set the property value to 10 on the alternative "LED 6W" for the criteria "Price"

    # request aid
    Given I request aid for the decision

    # add another alternative
    Given I plan to add a criteria
    And I set the criteria name to "Power"
    And I set the criteria weight to 20
    And I set the criteria type to "maximum"
    And I set the criteria unit of measure to "W"
    When I add the criteria
    Then the response is 409