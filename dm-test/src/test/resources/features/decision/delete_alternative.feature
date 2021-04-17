Feature: Delete alternative

  Scenario: Delete alternative
    Given I create a decision with name "Phone"
    And I plan to add an alternative
    And I set the name "Samsung Note" on the add alternative input
    And I add the alternative
    And I plan to add an alternative
    And I plan to add an alternative
    And I set the name "iPhone 12" on the add alternative input
    And I add the alternative

    When I delete the alternative with name "Samsung Note"
    Then the response is 204

    Given I get the decision
    Then the decision has 1 alternative
    And I peek at the alternative with name "iPhone 12"