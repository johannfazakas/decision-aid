Feature: Get decision

  Scenario: Get decision
    # create decision.
    Given I plan to create a decision
    And I set the decision name to "Phone"
    And I set the decision description to "I would like it to take good pictures"
    And I create the decision

    # add 2 criteria
    Given I plan to add a criteria
    And I set the criteria name to "price"
    And I set the criteria weight to 60
    And I set the criteria unit of measure to "$"
    And I set the criteria type to "minimum"
    And I add the criteria

    Given I plan to add a criteria
    And I set the criteria name to "screen size"
    And I set the criteria weight to 30
    And I set the criteria unit of measure to "inch"
    And I set the criteria type to "maximum"
    And I add the criteria

    # add 2 alternatives
    Given I plan to add an alternative
    And I set the alternative name to "Samsung Note 10"
    And I add the alternative

    Given I plan to add an alternative
    And I set the alternative name to "iPhone 12"
    And I add the alternative

    # set properties
    And I set the property value to 7.0 on the alternative "Samsung Note 10" for the criteria "screen size"
    And I set the property value to 1500 on the alternative "iPhone 12" for the criteria "price"

    When I get the decision
    Then the response is 200
    And the decision name is "Phone"
    And the decision status is "design"
    And the decision description is "I would like it to take good pictures"

    Then the decision has 2 criteria

    Given I peek at the criteria with name "price"
    Then the criteria weight is 60
    And the criteria unit of measure is "$"
    And the criteria type is "minimum"

    Then the decision has 2 alternatives

    Given I peek at the alternative with name "Samsung Note 10"
    Then the alternative utility is not set
    And the alternative rank is not set

    And the decision has 2 properties

    Given I peek at the property on the alternative "Samsung Note 10" for the criteria "screen size"
    Then the property value is 7.0
    And the property rank is not set
    And the property utility is not set

    Given I peek at the property on the alternative "iPhone 12" for the criteria "price"
    Then the property value is 1500
    And the property rank is not set
    And the property utility is not set

  Scenario: Aid decision
    # create decision
    Given I plan to create a decision
    And I set the decision name to "Espressor"
    And I create the decision

    # add criteria
    Given I plan to add a criteria
    And I set the criteria name to "Price"
    And I set the criteria weight to 60
    And I set the criteria type to "minimum"
    And I set the criteria unit of measure to "ron"
    And I add the criteria
    Given I plan to add a criteria
    And I set the criteria name to "Beverages"
    And I set the criteria weight to 40
    And I set the criteria type to "maximum"
    And I add the criteria

    # add alternatives
    Given I plan to add an alternative
    And I set the alternative name to "Philips LatteGo 5400"
    And I add the alternative
    Given I plan to add an alternative
    And I set the alternative name to "De'Longhi Eletta"
    And I add the alternative
    Given I plan to add an alternative
    And I set the alternative name to "Krups Intuition Preference"
    And I add the alternative

    # define properties
    Given I set the property value to 2600 on the alternative "Philips LatteGo 5400" for the criteria "Price"
    Given I set the property value to 12 on the alternative "Philips LatteGo 5400" for the criteria "Beverages"
    Given I set the property value to 2950 on the alternative "De'Longhi Eletta" for the criteria "Price"
    Given I set the property value to 6 on the alternative "De'Longhi Eletta" for the criteria "Beverages"
    Given I set the property value to 3150 on the alternative "Krups Intuition Preference" for the criteria "Price"
    Given I set the property value to 16 on the alternative "Krups Intuition Preference" for the criteria "Beverages"

    # aid
    When I get the decision with aid
    Then the response is 200
    And the decision aid status is "processed"

    Given I peek at the property on the alternative "Philips LatteGo 5400" for the criteria "Price"
    Then the property rank is 1
    And the property utility is 0.6
    Given I peek at the property on the alternative "Philips LatteGo 5400" for the criteria "Beverages"
    Then the property rank is 2
    And the property utility is 0.24

    Given I peek at the alternative with name "Philips LatteGo 5400"
    Then the alternative rank is 1
    And the alternative utility is 0.84

    Given I peek at the alternative with name "Krups Intuition Preference"
    Then the alternative rank is 2

    Given I peek at the alternative with name "De'Longhi Eletta"
    Then the alternative rank is 3

  Scenario: Aid decision without criteria
    # create decision
    Given I plan to create a decision
    And I set the decision name to "Laptop"
    And I create the decision

    # add alternative
    Given I plan to add an alternative
    And I set the alternative name to "Dell Latitude E740"
    And I add the alternative

    # get decision with aid
    When I get the decision with aid
    Then the response is 200
    And the decision aid status is "skipped"
    And the decision aid failure reason is "No criteria defined."

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
    When I get the decision with aid
    Then the response is 200
    And the decision aid status is "skipped"
    And the decision aid failure reason is "No alternatives defined."

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
    When I get the decision with aid
    Then the response is 200
    And the decision aid status is "skipped"
    And the decision aid failure reason is "Missing properties."

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
    When I get the decision with aid
    Then the response is 200
    And the decision aid status is "skipped"
    And the decision aid failure reason is "Total criteria weight is not 100."

  Scenario: Get decision when not found
    Given I use a nonexistent decision
    When I get the decision
    Then the response is 404
