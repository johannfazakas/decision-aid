Feature: Get decision

  Scenario: Get decision
    # create decision.
    Given I plan to create a decision
    And I set the decision name to "Phone"
    And I set the decision description to "I would like it to take good pictures"
    And I create the decision

    # add 2 criteria
    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 60
    And I set the criteria unit of measure to "$"
    And I set the criteria type to "minimum"
    And I add the criteria

    Given I plan to add a criteria
    And I set the criteria name to "screen size"
    And I set the criteria weight to 30
    And I set the criteria unit of measure to "inch"
    And I set the criteria type to "maximum"
    And I add the criteria

    # add 2 alternatives
    Given I plan to add an alternative
    And I set the alternative name to "Samsung Note 10"
    And I add the alternative

    Given I plan to add an alternative
    And I set the alternative name to "iPhone 12"
    And I add the alternative

    # get decision
    Given I get the decision
    And I set the property on the alternative "Samsung Note 10" for the criteria "screen size" to the value 7.0
    And I set the property on the alternative "iPhone 12" for the criteria "price" to the value 1500

    When I get the decision
    Then the response is 200
    And the decision name is "Phone"
    And the decision status is "design"
    And the decision description is "I would like it to take good pictures"

    Then the decision has 2 criteria

    Given I peek at the criteria with name "price"
    Then the criteria weight is 60
    And the criteria unit of measure is "$"
    And the criteria type is "minimum"

    Then the decision has 2 alternatives
    And the decision has 2 properties

    Then the property value for the alternative "Samsung Note 10" for the criteria "screen size" is 7.0
    Then the property value for the alternative "iPhone 12" for the criteria "price" is 1500
    Then the property value for the alternative "Samsung Note 10" for the criteria "price" is not set

  Scenario: Get decision when not found
    Given I use a nonexistent decision
    When I get the decision
    Then the response is 404
