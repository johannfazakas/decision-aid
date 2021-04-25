Feature: Set property

  Scenario: Set property
    # create decision
    Given I plan to create a decision
    And I set the decision name to "Espressor"
    And I create the decision

    # add 3 criteria
    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria type to "minimum"
    And I set the criteria weight to 40
    And I set the criteria unit of measure to "$"
    And I add the criteria

    Given I plan to add a criteria
    And I set the criteria name to "beverages"
    And I set the criteria type to "maximum"
    And I set the criteria weight to 30
    And I add the criteria

    Given I plan to add a criteria
    And I set the criteria name to "water capacity"
    And I set the criteria type to "maximum"
    And I set the criteria weight to 40
    And I set the criteria unit of measure to "l"
    And I add the criteria

    # add 1 alternative
    Given I plan to add an alternative
    And I set the alternative name to "Philips LatteGo"
    And I add the alternative

    # set the property
    When I set the property value to 2600 on the alternative "Philips LatteGo" for the criteria "price"
    Then the response is 200
    And the property value is 2600
    When I set the property value to 2800 on the alternative "Philips LatteGo" for the criteria "price"
    Then the response is 200
    When I set the property value to 12 on the alternative "Philips LatteGo" for the criteria "beverages"

    # check decision response
    When I get the decision
    Then the decision has 2 properties
    Then the property value for the alternative "Philips LatteGo" for the criteria "price" is 2800
    Then the property value for the alternative "Philips LatteGo" for the criteria "beverages" is 12
    Then the property value for the alternative "Philips LatteGo" for the criteria "water capacity" is not set
