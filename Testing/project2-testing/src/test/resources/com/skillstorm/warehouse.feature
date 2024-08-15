@Warehouse
Feature: Warehouse CRUD

  Scenario Outline: Updating a warehouse

    Given I am on the “Warehouse List” page,

    When I click on the edit icon for the Warehouse I want to edit,

    And I change the "<location>", "<capacity>", and/or "<stock>" with valid information,

    And I click on the “UPDATE WAREHOUSE” button,

    Then I should see the updated details for the Warehouse I edited in the list of Warehouses.

  Examples:
  | location | capacity | stock |
  | Test      | 200     |  0    |
  | Test1     | 500     |  10    |
  | Test2      | 1000     |  50   |

  Scenario Outline: Successful warehouse creation with valid information

    Given I am on the "Warehouse List" page
    When I fill in the "<location>", "<capacity>", and "<currentStock>" fields with valid information
    And I click the "ADD WAREHOUSE" button
    Then a new Warehouse should be created
    And I should see the newly created Warehouse in the list of Warehouses

  Examples:
  | location                    | capacity | currentStock |
  | 3212 Spur Ln, Austin, Texas | 5000     | 400          |
