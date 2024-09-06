@Candy
Feature: Candy CRUD Operations

    #-----------------------------CREATE-------------------------------------#
    Scenario Outline: Creating a Candy
        Given I am on the Candy Inventory page
        When I input the candy "<name>", "<type>", "<flavor>", "<price>", and, "<weight>"
        And I click the ADD CANDY button
        Then I should see the newly created candy on the list
    Examples:
        |name         |type      |flavor|price|weight|
        |Sour Skittles|Sour Candy| Sour | 4.99|1.0   |
    
    #-----------------------------CREATE with invalid input-------------------------------------#
    Scenario Outline: Creating a Candy with Invalid Data
        Given I am on the Candy Inventory page
        When I input the candy "<name>", "<type>", "<flavor>", "<price>", and, "<weight>"
        And I click on the ADD CANDY button
        Then I should see an error message "<error>"

    Examples:
        |name       |type           |flavor       |price |weight | error |
        |           |Sour Candy      |Sour         |4.99  |1.0    | Name is required | # Empty name
        |Sour Skittles|             |Sour         |4.99  |1.0    | Candy type is required | # Empty type
        |Sour Skittles|Sour Candy     |             |4.99  |1.0    | Flavor is required | # Empty flavor
        |Sour Skittles|Sour Candy     |Sour         |      |1.0    | Price must be a positive number, and Price cannot contain letters | # Empty price
        |Sour Skittles|Sour Candy     |Sour         |4.99  |       | Weight must be a positive number, and Weight cannot contain letters | # Empty weight
        |Sour Skittles|Sour Candy     |Sour         |-1.00 |1.0    | Price must be a positive number | # Negative price
        |Sour Skittles|Sour Candy     |Sour         |4.99  |-0.5   | Weight must be a positive number | # Negative weight
        |Sour Skittles|Sour Candy     |Sour         |abcd  |1.0    | Price cannot contain letters | # Non-numeric price
        |Sour Skittles|Sour Candy     |Sour         |4.99  |xyz    | Weight cannot contain letters | # Non-numeric weight
    
    #-----------------------------READ-------------------------------------#
    Scenario: Successful view of candy list
        Given I am on the Home page
        When I navigate to Candy Inventory
        Then list of all candies should be displayed


#-----------------------------UPDATE-------------------------------------#
    Scenario Outline: Updating a Candy
        Given I am on the Candy Inventory page
        When I click the edit icon for the candy I want to edit
        And I change the "<name>", "<type>", "<flavor>", "<price>", and/or "<weight>"
        And I click the UPDATE CANDY button
        Then I should see the updated candy in the candy table
    
    Examples:
        |name                         |type      |flavor     |price |weight|
        |Sour Strawberry Belt Bulk Bag|Sour Candy| Blueberry | 10.00|1.0   |
    
    # Scenario Outline: Updating a Candy with Invalid Data
    #     Given I am on the Candy Inventory page
    #     When I click the edit icon for the candy I want to edit
    #     And I change the "<name>", "<type>", "<flavor>", "<price>", and/or "<weight>"
    #     And I click on the UPDATE CANDY button
    #     Then I should see an error message "<error>"

    # Examples:
    #     |name|type|flavor|price|weight| error |
    #     |   |Sour Candy|Sour|10.00|1.0|Name is required |         # Empty name
    #     |Sour Strawberry Belt Bulk Bag| |Sour|10.00|1.0| Candy type is required | # Empty type
    #     |Sour Strawberry Belt Bulk Bag|Sour Candy| |10.00|1.0|Flavor is required | # Empty flavor
    #     |Sour Strawberry Belt Bulk Bag|Sour Candy|Sour| |1.0| Price must be a positive number, and Price cannot contain letters |     # Empty price
    #     |Sour Strawberry Belt Bulk Bag|Sour Candy|Sour|10.00| | Weight must be a positive number, and Weight cannot contain letters |   # Empty weight
    #     |Sour Strawberry Belt Bulk Bag|Sour Candy|Sour|10.00| -1.0| Weight must be a positive number | # Negative weight
    #     |Sour Strawberry Belt Bulk Bag|Sour Candy|Sour| -10.00|1.0| Price must be a positive number | # Negative price 
    #     |Sour Strawberry Belt Bulk Bag|Sour Candy|Sour|10.00|abc| Weight cannot contain letters | # Non-numeric weight 
    #     |Sour Strawberry Belt Bulk Bag|Sour Candy|Sour|abc|1.0| Price cannot contain letters | # Non-numeric price 

    #-----------------------------DELETE-------------------------------------#
    Scenario: Deleting a Candy
        Given I am on the Candy Inventory page
        When I click the delete icon
        Then the candy should be deleted from the list and no longer visible in the table


