@Warehouse
Feature: Warehouse CRUD

  #-------------------------- Warehouse CREATE ----------------------------#
  Scenario Outline: Successful warehouse creation with valid information

    Given I am on the Warehouse List page
    When I fill in the "<location>", "<capacity>", and "<currentStock>" fields with valid information
    And I click the ADD WAREHOUSE button
    Then I should see the newly created Warehouse in the list of Warehouses

    Examples:
    | location                    | capacity | currentStock |
    | 3212 Spur Ln, Austin, Texas | 5000     | 400          |

  #-------------------------- Warehouse UPDATE ----------------------------#
  Scenario Outline: Updating a warehouse

    Given I am on the Warehouse List page

    When I click on the edit icon for the Warehouse I want to edit

    And I change the "<location>" and/or "<capacity>" with valid information

    And I click on the UPDATE WAREHOUSE button

    Then I should see the updated details for the Warehouse I edited in the list of Warehouses

  Examples:
  | location | capacity |
  | Apt. 862 652 Shad Neck, South Hershelborough, IN 14539    | 2000    |

#-------------------------- Warehouse DELETE ----------------------------#
  Scenario: Delete a warehouse
    Given I am on the Warehouse List page

    When I click on the delete icon for the Warehouse I want to delete

    Then the warehouse should be removed from the list


  #-------------------------- Warehouse READ ----------------------------#
  Scenario: Successful view of warehouse list

    Given I am on the home page
    When I click on the Warehouse List option
    Then I should be navigated to the Warehouse List page
    And I should see a list of all warehouses available

  
