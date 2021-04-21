Feature: Add alternative

  Scenario: Add alternative to decision
    Given I create a decision with name "Oven"

    Given I plan to add an alternative
    And I set the name to "Bosch MFK89" on the add alternative input
    When I add the alternative
    Then the response is 201

    Given I plan to add an alternative
    And I set the name to "Electrolux UXI87" on the add alternative input
    When I add the alternative
    Then the response is 201

    When I get the decision
    Then the response is 200
    And the decision has 2 alternatives
    Given I peek at the alternative with name "Bosch MFK89"

  Scenario: Add alternative with invalid name
    Given I create a decision with name "Hood"

    Given I plan to add an alternative
    When I add the alternative
    Then the response is 400

    Given I plan to add an alternative
    And I set the name to "" on the add alternative input
    When I add the alternative
    Then the response is 400

  Scenario: Add alternative on not found decision
    Given I plan to add an alternative
    And I set the name to "Logitech MX50" on the add alternative input
    When I add the alternative on a random decision
    Then the response is 404

