@Orders
Feature: Order CRUD operations
    Scenario Outline: Creating an order
        Given I am on the order list page
        When I fill in "<Customer Name>", "<Status>", and "<Customer Address>"
        Then I click the Add order button
        Then new order information should be in the table
    Examples:
        |Customer Name| Status | Customer Address|
        |Jordan Ellis | Pending| 1110 Fake Address Dr, Greensboro NC, 20675|
    
    Scenario Outline: Creating an order with invalid input
        Given I am on the order list page
        When I fill in "<Customer Name>", "<Status>", and "<Customer Address>"
        Then I click on the Add order button
        Then I should see the error message "<error>"
    Examples:
        |Customer Name| Status| Customer Address| error | 
        | | Pending| 1110 Fake Address Dr, Greensboro NC, 20675| Customer Name is required |
        |Jordan Ellis| | 1110 Fake Address Dr, Greensboro NC, 20675| Status is required |
        |Jordan Ellis| Pending| | Customer Address is required |
        | | | | Customer Name is required, and Status is required, and Customer Address is required |

    Scenario: View the order list table
        Given I am on the base page
        When I navigate to the order list page
        Then I should see the list of orders
    
    Scenario Outline: Update an order
        Given I am on the order list page
        When I click the edit button
        And I modify "<Customer Name>", "<Status>", and/or "<Customer Address>"
        And I click the update order button
        Then I should see the updated order in the list of orders
    Examples:
        |Customer Name| Status| Customer Address|
        |DJ Kim| Completed| 1011 Super Address Dr, Ballston VA, 10234|

    Scenario Outline: Update an order with invalid input
        Given I am on the order list page
        When I click the edit button
        And I modify "<Customer Name>", "<Status>", and/or "<Customer Address>"
        And I click on the update order button
        Then I should see the error message "<error>"

    Examples:
        |Customer Name| Status| Customer Address| error | 
        || Completed| 1011 Super Address Dr, Ballston VA, 10234| Customer Name is required | 
        |DJ Kim| | 1011 Super Address Dr, Ballston VA, 10234| Status is required |
        |DJ Kim| Completed| | Customer Address is required | 
        | | | | Customer Name is required, and Status is required, and Customer Address is required |
    
    Scenario: Delete an order
        Given I am on the order list page
		When I click the delete button
		Then The order should not be visible in the order list page


