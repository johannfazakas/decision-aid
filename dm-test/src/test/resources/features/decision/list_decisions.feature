Feature: List decisions

  Scenario: List decisions
    # create 2 decisions
    Given I create a decision with name "Washing machine"

    Given I plan to add a criteria
    And I set the name to "price" on the add criteria input
    And I set the type to "minimum" on the add criteria input
    And I set the weight to 50 on the add criteria input
    And I set the unit of measure to "euro" on the add criteria input
    And I add the criteria

    Given I plan to add a criteria
    And I set the name to "capacity" on the add criteria input
    And I set the type to "maximum" on the add criteria input
    And I set the weight to 60 on the add criteria input
    And I add the criteria

    Given I plan to add an alternative
    And I set the alternative name to "Bosch IREW85"
    And I add the alternative

    Given I plan to create a decision
    And I set the name to "Dryer" on the create decision input
    And I set the description to "for clothes" on the create decision input
    And I create the decision

    # check the decisions
    When I list the decisions
    Then the response is 200
    And the decisions count is 2

    # check the 1st decision
    Given I peek at the decision with name "Washing machine"
    Then the decision status is "define"

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
    And the decision status is "define"

  Scenario: List decisions when empty
    When I list the decisions
    Then the response is 200
    And the decisions count is 0
