Feature: Reset decision

  Scenario: Reset decision
    # create decision
    Given I plan to create a decision
    And I set the decision name to "Laptop"
    And I create the decision

    # add criteria
    Given I plan to add a criteria
    And I set the criteria name to "Price"
    And I set the criteria type to "minimum"
    And I set the criteria weight to 100
    And I add the criteria

    # add alternative
    Given I plan to add an alternative
    And I set the alternative name to "MacBook Pro"
    And I add the alternative

    # add property
    Given I set the property on the alternative "MacBook Pro" for the criteria "Price" to the value 12000

    # request aid
    Given I request aid for the decision

    # reset
    Given I reset the decision
    Then the response is 200
