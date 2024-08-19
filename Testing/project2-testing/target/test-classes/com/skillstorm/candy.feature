@Candy
Feature: Candy CRUD Operations

    Scenario: Creating a Candy
        Given I am on the Candy Inventory page
        When I input the candy name, type, flavor, price, and, weight
        And I click the ADD CANDY button
        Then I should see the newly created candy on the list
    
    Scenario: Successful of candy list
        Given I am on the Home page
        When I navigate to Candy Inventory
        Then list of all candies should be displayed

    Scenario: Updating a Candy
        Given I am on the Candy Inventory page
        When I click the edit icon for the candy I want to edit
        And I change the name, type, flavor, price, and/or weight
        And I click the UPDATE CANDY
        Then I should see the updated candy in the candy table

    Scenario: Deleting a Candy
        Given I am on the Candy Inventory page
        When I click the delete icon
        Then the candy should be deleted from the list and no longer visible in the table



