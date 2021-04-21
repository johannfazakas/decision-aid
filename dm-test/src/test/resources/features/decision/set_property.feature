Feature: Set property

  Scenario: Set property
    Given I create a decision with name "Espressor"

    Given I plan to add a criteria
    And I set the name to "price" on the add criteria input
    And I set the type to "minimum" on the add criteria input
    And I set the weight to 40 on the add criteria input
    And I set the unit of measure to "$" on the add criteria input
    And I add the criteria

    Given I plan to add a criteria
    And I set the name to "beverages" on the add criteria input
    And I set the type to "maximum" on the add criteria input
    And I set the weight to 30 on the add criteria input
    And I add the criteria

    Given I plan to add a criteria
    And I set the name to "water capacity" on the add criteria input
    And I set the type to "maximum" on the add criteria input
    And I set the weight to 40 on the add criteria input
    And I set the unit of measure to "l" on the add criteria input
    And I add the criteria

    Given I add an alternative with name "Philips LatteGo"
    And I get the decision

    When I set the property on the alternative "Philips LatteGo" for the criteria "price" to the value 2600
    Then the response is 200
    And the property value is 2600
    When I set the property on the alternative "Philips LatteGo" for the criteria "price" to the value 2800
    Then the response is 200
    When I set the property on the alternative "Philips LatteGo" for the criteria "beverages" to the value 12

    When I get the decision
    Then the decision has 2 properties
    Then the property value for the alternative "Philips LatteGo" for the criteria "price" is 2800
    Then the property value for the alternative "Philips LatteGo" for the criteria "beverages" is 12
    Then the property value for the alternative "Philips LatteGo" for the criteria "water capacity" is not set
