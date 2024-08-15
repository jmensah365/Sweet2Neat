@Warehouse
Feature: Warehouse CRUD

  Scenario Outline: Updating a warehouse

    Given I am on the Warehouse List page

    When I click on the edit icon for the Warehouse I want to edit

    And I change the "<location>", "<capacity>", and/or "<stock>" with valid information

    And I click on the UPDATE WAREHOUSE button

    Then I should see the updated details for the Warehouse I edited in the list of Warehouses

  Examples:
  | location | capacity | stock |
  | Test      | 200     |  0    |

  