@Navigation

Feature: Navigation Links
    # Scenario: Successfully navigate to the home page
    #     Given I am on any page
    #     When I click on the logo
    #     Then I should see the home page

    Scenario: Successfully navigate to Warehouse List page
        Given I am on the home page
        When I click on Warehouse List menu option
        Then I should see the Warehouse List page
