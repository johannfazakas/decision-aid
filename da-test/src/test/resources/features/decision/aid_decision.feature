Feature: Aid decision

  Scenario: Aid decision
    # create decision
    Given I plan to create a decision
    And I set the decision name to "Espressor"
    And I create the decision

    # add 2 criteria
    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 60
    And I set the criteria type to "minimum"
    And I add the criteria

    Given I plan to add a criteria
    And I set the criteria name to "beverages"
    And I set the criteria weight to 40
    And I set the criteria type to "maximum"
    And I add the criteria

    # add 2 alternatives
    Given I plan to add an alternative
    And I set the alternative name to "Philips LatteGo 5400"
    And I add the alternative
    And I set the property on the alternative "Philips LatteGo 5400" for the criteria "price" to the value 2600
    And I set the property on the alternative "Philips LatteGo 5400" for the criteria "beverages" to the value 12

    Given I plan to add an alternative
    And I set the alternative name to "DeLonghi Eletta"
    And I add the alternative
    And I set the property on the alternative "DeLonghi Eletta" for the criteria "price" to the value 3000
    And I set the property on the alternative "DeLonghi Eletta" for the criteria "beverages" to the value 7

    # request for aid
    When I request aid for the decision
    Then the response is 200
    And the decision status is "aid"

  Scenario: Aid decision without criteria
    # TODO

  Scenario: Aid decision without alternatives
    # TODO

  Scenario: Aid decision without all the properties being set
    # TODO

  Scenario: Aid decision with invalid total criteria weight
    # TODO