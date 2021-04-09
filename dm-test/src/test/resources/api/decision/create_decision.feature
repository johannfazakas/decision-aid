Feature: Create decision
  Testing create decision operation

  Scenario: Create decision
    When I create a decision with name "fridge decision"
    Then the response is 201
    Given I store the decision as "fridge"

    # TODO delete scenario
  Scenario: Check storage is not persisted across scenarios
    Then check storage is empty