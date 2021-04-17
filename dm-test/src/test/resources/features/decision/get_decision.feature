Feature: Get decision

  Scenario: Get decision
    Given I plan to create a decision
    And I set the name "Phone" on the create decision input
    And I set the description "I would like it to take good pictures" on the create decision input
    And I create the decision

    Given I add a criteria with name "price" and weight 60

    Given I plan to add an alternative
    And I set the name "Samsung Note 9" on the add alternative input
    And I add the alternative

    When I get the decision
    Then the response is 200
    And the decision name is "Phone"
    And the decision description is "I would like it to take good pictures"
    And the decision has 1 criteria
    Given I peek at the criteria with name "price"
    Then the criteria weight is 60
    And the decision has 1 alternative
    Given I peek at the alternative with name "Samsung Note 9"

  Scenario: Get decision when not found
    When I get a decision by random id
    Then the response is 404
