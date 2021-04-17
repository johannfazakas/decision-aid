Feature: Set property

  Scenario: Set property
    Given I create a decision with name "Espressor"
    And I add a criteria with name "price" and weight 40
    And I add a criteria with name "beverages" and weight 30
    And I add a criteria with name "water capacity" and weight 20
    And I add an alternative with name "Philips LatteGo"
    And I get the decision

    When I set the property on the alternative "Philips LatteGo" for the criteria "price" to the value 2600
    Then the response is 204
    When I set the property on the alternative "Philips LatteGo" for the criteria "price" to the value 2800
    Then the response is 204
    When I set the property on the alternative "Philips LatteGo" for the criteria "beverages" to the value 12

    When I get the decision
    And I peek at the alternative with name "Philips LatteGo"
    Then the alternative has 2 properties
    Then the property value for the alternative "Philips LatteGo" for the criteria "price" is 2800
    Then the property value for the alternative "Philips LatteGo" for the criteria "beverages" is 12
    Then the property value for the alternative "Philips LatteGo" for the criteria "water capacity" is not set
