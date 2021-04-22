Feature: Aid decision

  Scenario: Aid decision
    # create decision
    Given I create a decision with name "Espressor"

    # add 2 criteria
    Given I plan to add a criteria
    And I set the name to "price" on the add criteria input
    And I set the weight to 60 on the add criteria input
    And I set the type to "minimum" on the add criteria input
    And I add the criteria

    Given I plan to add a criteria
    And I set the name to "beverages" on the add criteria input
    And I set the weight to 40 on the add criteria input
    And I set the type to "maximum" on the add criteria input
    And I add the criteria

    # add 2 alternatives
    Given I add an alternative with name "Philips LatteGo 5400"
    And I set the property on the alternative "Philips LatteGo 5400" for the criteria "price" to the value 2600
    And I set the property on the alternative "Philips LatteGo 5400" for the criteria "beverages" to the value 12

    Given I add an alternative with name "DeLonghi Eletta"
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