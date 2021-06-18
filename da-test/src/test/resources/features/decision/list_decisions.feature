Feature: List decisions

  Scenario: List decisions
    Given I use a valid user
    # create 2 decisions
    Given I plan to create a decision
    And I set the decision name to "Washing machine"
    And I create the decision

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria type to "minimum"
    And I set the criteria weight to 50
    And I set the criteria unit of measure to "euro"
    And I add the criteria

    Given I plan to add a criteria
    And I set the criteria name to "capacity"
    And I set the criteria type to "maximum"
    And I set the criteria weight to 60
    And I add the criteria

    Given I plan to add an alternative
    And I set the alternative name to "Bosch IREW85"
    And I add the alternative

    Given I plan to create a decision
    And I set the decision name to "Dryer"
    And I set the decision description to "for clothes"
    And I create the decision

    # check the decisions
    When I list the decisions
    Then the response is 200
    And the decisions count is 2

    # check the 1st decision
    Given I peek at the decision with name "Washing machine"

    Then the decision has 2 criteria
    Given I peek at the criteria with name "price"
    Then the criteria weight is 50
    And the criteria type is "minimum"
    And the criteria unit of measure is "euro"

    Then the decision has 1 alternatives
    Given I peek at the alternative with name "Bosch IREW85"

    # check the 2nd decision
    Given I peek at the decision with name "Dryer"
    Then the decision description is "for clothes"

  Scenario: List decisions when empty
    Given I use a valid user
    When I list the decisions
    Then the response is 200
    And the decisions count is 0
