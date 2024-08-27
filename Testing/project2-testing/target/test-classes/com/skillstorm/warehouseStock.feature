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
        |  Sour Skittles   | 3212 Spur Ln, Austin, Texas | 10000   |

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
    Then I should see the updated warehouse stock

    Examples:
    | candy     | location      | quantity |
    | Ring Pops | Test location | 40000    |