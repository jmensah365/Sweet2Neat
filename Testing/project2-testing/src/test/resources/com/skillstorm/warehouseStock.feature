@WarehouseStock
Feature: WarehouseStock CRUD

  #-------------------------- Warehouse Stock CREATE ----------------------------#
    Scenario Outline: Successful warehouse stock creation with valid information

        Given I am on the Warehouse Stock page
        When I select the "<candy>", "<location>" and input "<quantity>" fields
        And I click the Add Stock button
        Then I should see the last row with "<candy>", "<location>", and "<quantity>"
        And should see an alert message "Added stock successfully!"

        Examples:
        | candy | location | quantity |
        |  Almondy Joy   | 9877 Cedar Swamp Ave. Barberton, OH 44203 | 10   |

    Scenario Outline: Unsuccessful warehouse stock creation with invalid and valid information

        Given I am on the Warehouse Stock page
        When I don't select "<candy>", "<location>" and input "<quantity>" fields
        And I click on the Add Stock button
        Then the warehouse stock should not be added with "<candy>", "<location>", and "<quantity>"
        And should see an alert message "<errormsg>"

        Examples:
        |   candy   |   location    |   quantity    | errormsg |
        |      | 9877 Cedar Swamp Ave. Barberton, OH 44203 |   1000        |  Candy Name is required |
        | Air Heads |          |   2000        | Warehouse Location is required |
        | Air Heads | 9877 Cedar Swamp Ave. Barberton, OH 44203 |          | Quantity must be a number and greater than zero, and Quantity cannot contain letters |
        |  |  |          | Candy Name is required, and Warehouse Location is required, and Quantity must be a number and greater than zero, and Quantity cannot contain letters |


  #-------------------------- Warehouse Stock UPDATE ----------------------------#
  Scenario Outline: Successful warehouse stock update with valid information

    Given I am on the Warehouse Stock page
    When I click the edit icon
    Then I select a new "<candy>", "<location>" and input "<quantity>" fields
    And I click the Update Stock button
    Then I should see the last row with "<candy>", "<location>", and "<quantity>"

    Examples:
    | candy     | location      | quantity |
    | Turking Taffy | 9877 Cedar Swamp Ave. Barberton, OH 44203 | 40    |

  Scenario Outline: Unsuccessful warehouse stock update with invalid and valid information

    Given I am on the Warehouse Stock page
    When I click the edit icon
    And I don't select a new "<candy>", "<location>" and input "<quantity>" fields
    And I click on the Update Stock button
    Then the warehouse stock should not be updated with "<candy>", "<location>", and "<quantity>"

    Examples:
    |   candy   |   location    |   quantity    |
    |      | 456 Oak Avenue Greenfield, MA 01201 USA |   1000        |
    | Air Heads |          |   2000        |
    | Air Heads | 456 Oak Avenue Greenfield, MA 01201 USA |         |
    |  |  |          |

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
      Then I click the confirm delete button on the warehouse stock page
      Then the warehouse stock should not be visible in the table