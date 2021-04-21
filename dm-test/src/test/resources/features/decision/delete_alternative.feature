Feature: Delete alternative

  Scenario: Delete alternative
    Given I create a decision with name "Phone"
    And I plan to add an alternative
    And I set the name to "Samsung Note" on the add alternative input
    And I add the alternative
    And I plan to add an alternative
    And I plan to add an alternative
    And I set the name to "iPhone 12" on the add alternative input
    And I add the alternative
    And I get the decision

    When I delete the alternative with name "Samsung Note"
    Then the response is 204

    Given I get the decision
    Then the decision has 1 alternative
    And I peek at the alternative with name "iPhone 12"

  Scenario: Delete alternative should delete associated properties
    Given I create a decision with name "Espressor"

    And I add an alternative with name "Philips LatteGo 5400"
    And I add an alternative with name "DeLonghi Eletta"

    And I add a criteria with name "price" and weight 50
    And I add a criteria with name "beverages" and weight 30

    Given I get the decision
    And I set the property on the alternative "Philips LatteGo 5400" for the criteria "price" to the value 2600
    And I set the property on the alternative "DeLonghi Eletta" for the criteria "price" to the value 3000

    When I delete the alternative with name "DeLonghi Eletta"
    Then the response is 204

    Given I get the decision
    Then the decision has 1 properties
    And the property value for the alternative "Philips LatteGo 5400" for the criteria "price" is 2600
