@WarehouseStock
Feature: WarehouseStock CRUD

  #-------------------------- Warehouse Stock CREATE ----------------------------#
    Scenario Outline: Successful warehouse stock creation with valid information

        Given I am on the Warehouse Stock page
        When I select the "<candy>", "<location>" and input "<quantity>" fields
        And I click the Add Stock button
        Then I should see the last row with "<candy>", "<location>", and "<quantity>"

        Examples:
        | candy | location | quantity |
        |  Air Heads   | Test location | 10000   |

    Scenario Outline: Unsuccessful warehouse stock creation with invalid and valid information

        Given I am on the Warehouse Stock page
        When I don't select "<candy>", "<location>" and input "<quantity>" fields
        And I click the Add Stock button
        Then the warehouse stock should not be added with "<candy>", "<location>", and "<quantity>"

        Examples:
        |   candy   |   location    |   quantity    |
        | empty     | Test location |   1000        |
        | Air Heads |   empty       |   2000        |
        | Air Heads | Test location | empty         |
        | empty | empty | empty         |



  #-------------------------- Warehouse Stock UPDATE ----------------------------#
  Scenario Outline: Successful warehouse stock update with valid information

    Given I am on the Warehouse Stock page
    When I click the edit icon
    Then I select a new "<candy>", "<location>" and input "<quantity>" fields
    And I click the Update Stock button
    Then I should see the last row with "<candy>", "<location>", and "<quantity>"

    Examples:
    | candy     | location      | quantity |
    | Ring Pops | Test location | 40000    |

  Scenario Outline: Unsuccessful warehouse stock update with invalid and valid information

    Given I am on the Warehouse Stock page
    When I click the edit icon
    And I don't select a new "<candy>", "<location>" and input "<quantity>" fields
    And I click the Update Stock button
    Then the warehouse stock should not be updated with "<candy>", "<location>", and "<quantity>"

    Examples:
    |   candy   |   location    |   quantity    |
    | empty     | Test location |   1000        |
    | Air Heads |   empty       |   2000        |
    | Air Heads | Test location | empty         |
    | empty     | empty         | empty         |

  #-------------------------- Warehouse Stock READ ----------------------------#
  Scenario: Successful view of warehouse stocks

    Given I am starting on the home page
    When I click on the Warehouse Stock option
    Then I should be navigated to the Warehouse Stock page
    And I should see a list of all warehouse stocks available

  #-------------------------- Warehouse Stock DELETE ----------------------------#
    Scenario: Delete a warehouse stock
      Given I am on the Warehouse Stock page
      When I click on the delete icon for the Warehouse Stock I want to delete
      Then the warehouse stock should not be visible in the table