@Warehouse
Feature: Warehouse CRUD

  # #-------------------------- Warehouse CREATE ----------------------------#
  # Scenario Outline: Successful warehouse creation

  #   Given I am on the Warehouse List page
  #   When I fill in the "<location>" and "<capacity>" fields 
  #   And I click the ADD WAREHOUSE button
  #   Then I should see the newly created Warehouse in the list of Warehouses
  #   And I should see an alert message "<msg>"

  #   Examples:
  #   | location                    | capacity | msg |
  #   | 3212 Spur Ln, Austin, Texas | 5000     | Warehouse added successfully! |
  #   | Test location | 10000     | Warehouse added successfully! |

  # Scenario Outline: Unsuccessful warehouse creation

  #   Given I am on the Warehouse List page
  #   When I fill in the "<location>" and "<capacity>" fields 
  #   And I click ADD WAREHOUSE button
  #   Then I should see an alert message "<errormsg>"

  #   Examples:
  #   | location      | capacity | errormsg |
  #   |          | 5000     | Location is required |
  #   | Test location |     | Capacity must be a positive number |
  #   |          |     | Location is required, and Capacity must be a positive number |

  # #-------------------------- Warehouse UPDATE ----------------------------#
  # Scenario Outline: Successful updating a warehouse

  #   Given I am on the Warehouse List page
  #   When I click on the edit icon for the Warehouse I want to edit
  #   And I change the "<location>" and/or "<capacity>" 
  #   And I click on the UPDATE WAREHOUSE button
  #   Then I should see an alert message "<msg>"
  #   And I should see the updated details for the Warehouse I edited in the list of Warehouses

  # Examples:
  # | location | capacity | msg |
  # | Apt. 862 652 Shad Neck, South Hershelborough, IN 14539    | 2000    | Warehouse updated successfully! |

  Scenario Outline: Unsuccessful updating a warehouse

    Given I am on the Warehouse List page
    When I click on the edit icon for the Warehouse I want to edit
    And I change the "<location>" and/or "<capacity>" to invalid fields
    And I click the UPDATE WAREHOUSE button
    Then I should see an alert message "<errormsg>"

  Examples:
  | location | capacity | errormsg |
  | Apt. 862 652 Shad Neck, South Hershelborough, IN 14539    |    | Capacity must be a positive number |
  |    | 2000    | Location is required |
  |     |     | Location is required, and Capacity must be a positive number |

# #-------------------------- Warehouse READ ----------------------------#
# Scenario: Successful view of warehouse list

#   Given I am on the home page
#   When I click on the Warehouse List option
#   Then I should be navigated to the Warehouse List page
#   And I should see a list of all warehouses available

#-------------------------- Warehouse DELETE ----------------------------#
  Scenario: Delete a warehouse
    Given I am on the Warehouse List page
    When I click on the delete icon for the Warehouse I want to delete
    Then I should see an alert message "Warehouse deleted successfully!"
    And the warehouse should not be visible in the table