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
    And I set the property value to 2600 on the alternative "Philips LatteGo 5400" for the criteria "price"
    And I set the property value to 12 on the alternative "Philips LatteGo 5400" for the criteria "beverages"

    Given I plan to add an alternative
    And I set the alternative name to "DeLonghi Eletta"
    And I add the alternative
    And I set the property value to 3000 on the alternative "DeLonghi Eletta" for the criteria "price"
    And I set the property value to 7 on the alternative "DeLonghi Eletta" for the criteria "beverages"

    # request for aid
    When I request aid for the decision
    Then the response is 200
    And the decision status is "processed"

  Scenario: Aid decision without criteria
    # create decision
    Given I plan to create a decision
    And I set the decision name to "Laptop"
    And I create the decision

    # add alternative
    Given I plan to add an alternative
    And I set the alternative name to "Dell Latitude E740"
    And I add the alternative

    # request for aid
    When I request aid for the decision
    Then the response is 409

  Scenario: Aid decision without alternatives
    # create decision
    Given I plan to create a decision
    And I set the decision name to "PC"
    And I create the decision

    # add criteria
    Given I plan to add a criteria
    And I set the criteria name to "Price"
    And I set the criteria weight to 100
    And I set the criteria type to "minimum"
    And I add the criteria

    # request aid
    When I request aid for the decision
    Then the response is 409

  Scenario: Aid decision without all the properties being set
    # create decision
    Given I plan to create a decision
    And I set the decision name to "Camera"
    And I create the decision

    # add criteria
    Given I plan to add a criteria
    And I set the criteria name to "Price"
    And I set the criteria weight to 100
    And I set the criteria type to "minimum"
    And I add the criteria

    # add alternatives
    Given I plan to add an alternative
    And I set the alternative name to "Nikon XP34"
    And I add the alternative

    Given I plan to add an alternative
    And I set the alternative name to "Canon YT86"
    And I add the alternative

    # set only one property
    Given I set the property value to 3000 on the alternative "Nikon XP34" for the criteria "Price"

    # request aid
    When I request aid for the decision
    Then the response is 409

  Scenario: Aid decision with invalid total criteria weight
    # create decision
    Given I plan to create a decision
    And I set the decision name to "Keyboard"
    And I create the decision

    # add criteria
    Given I plan to add a criteria
    And I set the criteria name to "Price"
    And I set the criteria weight to 70
    And I set the criteria type to "minimum"
    And I add the criteria

    # add alternatives
    Given I plan to add an alternative
    And I set the alternative name to "Logitech GX500"
    And I add the alternative

    # set the property
    Given I set the property value to 250 on the alternative "Logitech GX500" for the criteria "Price"

    # request aid
    When I request aid for the decision
    Then the response is 409
