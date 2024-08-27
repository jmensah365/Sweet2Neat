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


  #-------------------------- Warehouse Stock UPDATE ----------------------------#
  Scenario Outline: Successful warehouse stock creation with valid information

    Given I am on the Warehouse Stock page
    When I select the "<candy>", "<location>" and input "<quantity>" fields
    And I click the Add Stock button
    Then I should see the last row with "<candy>", "<location>", and "<quantity>"

    Examples:
    | candy | location | quantity |
    |  Sour Skittles   | 3212 Spur Ln, Austin, Texas | 10000   |