Feature: Get decision

  Scenario: Get decision
    Given I plan to create a decision
    And I set the name to "Phone" on the create decision input
    And I set the description to "I would like it to take good pictures" on the create decision input
    And I create the decision

    Given I plan to add a criteria
    And I set the name to "price" on the add criteria input
    And I set the weight to 60 on the add criteria input
    And I set the unit of measure to "$" on the add criteria input
    And I add the criteria
    And I add a criteria with name "screen size" and weight 30

    Given I add an alternative with name "Samsung Note 10"
    And I add an alternative with name "iPhone 12"

    Given I get the decision
    And I set the property on the alternative "Samsung Note 10" for the criteria "screen size" to the value 7.0
    And I set the property on the alternative "iPhone 12" for the criteria "price" to the value 1500

    When I get the decision
    Then the response is 200
    And the decision name is "Phone"
    And the decision description is "I would like it to take good pictures"

    Then the decision has 2 criteria
    Given I peek at the criteria with name "price"
    Then the criteria weight is 60
    And the criteria unit of measure is "$"

    Then the decision has 2 alternatives
    And the decision has 2 properties

    Then the property value for the alternative "Samsung Note 10" for the criteria "screen size" is 7.0
    Then the property value for the alternative "iPhone 12" for the criteria "price" is 1500
    Then the property value for the alternative "Samsung Note 10" for the criteria "price" is not set

  Scenario: Get decision when not found
    When I get a decision by random id
    Then the response is 404
