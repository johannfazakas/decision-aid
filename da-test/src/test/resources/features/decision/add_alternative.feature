Feature: Add alternative

  Scenario: Add alternative to decision
    Given I plan to create a decision
    And I set the decision name to "Oven"
    And I create the decision

    Given I plan to add an alternative
    And I set the alternative name to "Bosch MFK89"
    When I add the alternative
    Then the response is 201

    Given I plan to add an alternative
    And I set the alternative name to "Electrolux UXI87"
    When I add the alternative
    Then the response is 201

    When I get the decision
    Then the response is 200
    And the decision has 2 alternatives
    Given I peek at the alternative with name "Bosch MFK89"

  Scenario: Add alternative with invalid name
    Given I plan to create a decision
    And I set the decision name to "Hood"
    And I create the decision

    Given I plan to add an alternative
    When I add the alternative
    Then the response is 400

    Given I plan to add an alternative
    And I set the alternative name to ""
    When I add the alternative
    Then the response is 400

  Scenario: Add alternative on not found decision
    And I use a nonexistent decision
    Given I plan to add an alternative
    And I set the alternative name to "Logitech TR25"
    When I add the alternative
    Then the response is 404

  Scenario: Add alternative when decision was processed
    # create decision
    Given I plan to create a decision
    And I set the decision name to "Headphones"
    And I create the decision

    # add criteria
    Given I plan to add a criteria
    And I set the criteria name to "Price"
    And I set the criteria weight to 100
    And I set the criteria type to "minimum"
    And I add the criteria

    # add alternatives
    Given I plan to add an alternative
    And I set the alternative name to "AudioTechnica MX50"
    And I add the alternative

    # set the property
    Given I set the property value to 600 on the alternative "AudioTechnica MX50" for the criteria "Price"

    # request aid
    Given I request aid for the decision

    # add another alternative
    Given I plan to add an alternative
    And I set the alternative name to "AudioTechnica MX40"
    When I add the alternative
    Then the response is 409
