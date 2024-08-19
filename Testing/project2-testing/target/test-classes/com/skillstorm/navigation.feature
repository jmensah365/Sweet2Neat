@navigation

Feature: Navigation Links
    Scenario: Successfully navigate to the home page
        Given I am on any page
        When I click on the logo
        Then I should see the home page