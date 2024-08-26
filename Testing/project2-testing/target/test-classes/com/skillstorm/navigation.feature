@Navigation

Feature: Navigation Links
    Scenario: Successfully navigate to the home page
        Given I am on any page
        When I click on the logo
        Then I should see the home page

    Scenario: Successfully navigate to Warehouse List page
        Given I am on the home page
        When I click on Warehouse List menu option
        Then I should see the Warehouse List page

    Scenario: Successfully navigate to Candy Inventory page
        Given I am on the home page
        When I click on the Candy Inventory menu option
        Then I should see the Candy Inventory page

    Scenario: Successfully navigate to Candy Categories page
        Given I am on the home page
        When I click on the Candy Categories menu option
        Then I should see the Candy Categories page

    Scenario: Successfully navigate to List of Orders page
        Given I am on the home page
        When I click on the List of Orders menu option
        Then I should see the List of Orders page

    Scenario: Successfully navigate to Order Info page
        Given I am on the home page
        When I click on the Order Info menu option
        Then I should see the Order Info page

    Scenario: Successfully navigate to About page
        Given I am on the home page
        When I click on the About menu option
        Then I should see the About page

    Scenario: Successfully navigate to Warehouse Stock page
        Given I am on the home page
        When I click on the Warehouse Stock menu option
        Then I should see the Warehouse Stock page

    Scenario: Successfully navigation with Add a Warehouse button
        Given I am on the home page
        When I click on the Add a Warehouse menu button
        Then I should see the Warehouse List page
