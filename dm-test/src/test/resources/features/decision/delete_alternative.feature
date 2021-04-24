Feature: Delete alternative

  Scenario: Delete alternative
    Given I create a decision with name "Phone"
    And I plan to add an alternative
    And I set the alternative name to "Samsung Note"
    And I add the alternative
    And I plan to add an alternative
    And I plan to add an alternative
    And I set the alternative name to "iPhone 12"
    And I add the alternative
    And I get the decision

    Given I peek at the alternative with name "Samsung Note"
    When I delete the alternative
    Then the response is 204

    Given I get the decision
    Then the decision has 1 alternative
    And I peek at the alternative with name "iPhone 12"

  Scenario: Delete alternative should delete associated properties
    Given I create a decision with name "Espressor"

    Given I plan to add an alternative
    And I set the alternative name to "Philips LatteGo 5400"
    And I add the alternative

    Given I plan to add an alternative
    And I set the alternative name to "DeLonghi Eletta"
    And I add the alternative

    Given I plan to add a criteria
    And I set the name to "price" on the add criteria input
    And I set the weight to 50 on the add criteria input
    And I set the type to "minimum" on the add criteria input
    And I add the criteria

    Given I plan to add a criteria
    And I set the name to "beverages" on the add criteria input
    And I set the weight to 30 on the add criteria input
    And I set the type to "maximum" on the add criteria input

    Given I get the decision
    And I set the property on the alternative "Philips LatteGo 5400" for the criteria "price" to the value 2600
    And I set the property on the alternative "DeLonghi Eletta" for the criteria "price" to the value 3000

    Given I peek at the alternative with name "DeLonghi Eletta"
    When I delete the alternative
    Then the response is 204

    Given I get the decision
    Then the decision has 1 properties
    And the property value for the alternative "Philips LatteGo 5400" for the criteria "price" is 2600
