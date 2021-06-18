Feature: Delete alternative

  Scenario: Delete alternative
    Given I use a valid user

    Given I plan to create a decision
    And I set the decision name to "Phone"
    And I create the decision
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
    Given I use a valid user

    Given I plan to create a decision
    And I set the decision name to "Espressor"
    And I create the decision

    Given I plan to add an alternative
    And I set the alternative name to "Philips LatteGo 5400"
    And I add the alternative

    Given I plan to add an alternative
    And I set the alternative name to "DeLonghi Eletta"
    And I add the alternative

    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 50
    And I set the criteria type to "minimum"
    And I add the criteria

    Given I plan to add a criteria
    And I set the criteria name to "beverages"
    And I set the criteria weight to 30
    And I set the criteria type to "maximum"

    Given I get the decision
    And I set the property value to 2600 on the alternative "Philips LatteGo 5400" for the criteria "price"
    And I set the property value to 3000 on the alternative "DeLonghi Eletta" for the criteria "price"

    Given I peek at the alternative with name "DeLonghi Eletta"
    When I delete the alternative
    Then the response is 204

    Given I get the decision
    Then the decision has 1 properties
    And the property value for the alternative "Philips LatteGo 5400" for the criteria "price" is 2600
