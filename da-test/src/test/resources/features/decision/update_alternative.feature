Feature: Update alternative

  Scenario: Update alternative
    Given I use a valid user
    # create decision
    Given I plan to create a decision
    And I set the decision name to "Espressor"
    And I create the decision

    # add 2 alternatives
    Given I plan to add an alternative
    And I set the alternative name to "DeLonghi Eletta"
    And I add the alternative

    Given I plan to add an alternative
    And I set the alternative name to "Philips LatteGo 4300"
    And I add the alternative

    # update the alternative
    Given I plan to update the alternative
    And I set the alternative name to "Philips LatteGo 5400"
    When I update the alternative
    Then the response is 200
    And the alternative name is "Philips LatteGo 5400"

    # check the decision
    When I get the decision
    Then the decision has 2 alternative
    And I peek at the alternative with name "Philips LatteGo 5400"

  Scenario: Update alternative with invalid name
    Given I use a valid user

    Given I plan to create a decision
    And I set the decision name to "Dryer"
    And I create the decision

    Given I plan to add an alternative
    And I set the alternative name to "Bosch RE34"
    And I add the alternative

    Given I plan to update the alternative
    And I set the alternative name to ""
    When I update the alternative
    Then the response is 400
