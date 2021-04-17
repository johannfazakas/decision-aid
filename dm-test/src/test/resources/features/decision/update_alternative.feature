Feature: Update alternative

  Scenario: Update alternative
    Given I create a decision with name "Espressor"
    And I add an alternative with name "DeLonghi Eletta"
    And I add an alternative with name "Philips LatteGo 4300"

    Given I plan to update the alternative
    And I set the name "Philips LatteGo 5400" on the update alternative input
    When I update the alternative
    Then the response is 200
    And the alternative name is "Philips LatteGo 5400"

    When I get the decision
    Then the decision has 2 alternative
    And I peek at the alternative with name "Philips LatteGo 5400"

  Scenario: Update alternative with invalid name
    Given I create a decision with name "Dryer"
    And I add an alternative with name "Bosch RE34"

    Given I plan to update the alternative
    And I set the name "" on the update alternative input
    When I update the alternative
    Then the response is 400