Feature: Get decision

  Scenario: Get decision
    Given I plan to create a decision
    And I set the name "Phone" on the create decision input
    And I set the description "I would like it to take good pictures" on the create decision input
    And I create the decision

    Given I add a criteria with name "price" and weight 60
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

    Then the decision has 2 alternatives
    Given I peek at the alternative with name "Samsung Note 10"
    Then the alternative has 1 property

    Then the property value for the alternative "Samsung Note 10" for the criteria "screen size" is 7.0
    Then the property value for the alternative "Samsung Note 10" for the criteria "price" is not set

  Scenario: Get decision when not found
    When I get a decision by random id
    Then the response is 404
