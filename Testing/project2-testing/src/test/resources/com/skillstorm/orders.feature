@Orders
Feature: Order CRUD operations
    Scenario Outline: Creating an order
        Given I am on the order list page
        When I fill in "<Customer Name>", "<Order date>", "<Status>", and "<Customer Address>"
        Then I click the Add order button
        Then new order information should be in the table
    Examples:
        |Customer Name| Order date| Status| Customer Address|
        |Jeremiah Mensah| 09/19/2023| Pending| 1110 Fake Address Dr, Greensboro NC, 20675|
    
    Scenario Outline: Creating an order with invalid input
        Given I am on the order list page
        When I fill in "<Customer Name>", "<Order date>", "<Status>", and "<Customer Address>"
        Then I click the Add order button
        Then I should see an error message
    Examples:
        |Customer Name| Order date| Status| Customer Address|
        | | 09/19/2023| Pending| 1110 Fake Address Dr, Greensboro NC, 20675|
        |Jeremiah Mensah| | Pending| 1110 Fake Address Dr, Greensboro NC, 20675|
        |Jeremiah Mensah| 09/19/2023| | 1110 Fake Address Dr, Greensboro NC, 20675|
        |Jeremiah Mensah| 09/19/2023| Pending| |
    

    Scenario: View the order list table
        Given I am on the base page
        When I navigate to the order list page
        Then I should see the list of orders
    
    Scenario Outline: Update an order
        Given I am on the order list page
        When I click the edit button
        And I modify "<Customer Name>", "<Order date>", "<Status>", and/or "<Customer Address>"
        And I click the update order button
        Then I should see the updated order in the list of orders
    Examples:
        |Customer Name| Order date| Status| Customer Address|
        |DJ Kim| 06/17/2021| Completed| 1011 Super Address Dr, Ballston VA, 10234|

    Scenario Outline: Update an order with invalid input
        Given I am on the order list page
        When I click the edit button
        And I modify "<Customer Name>", "<Order date>", "<Status>", and/or "<Customer Address>"
        And I click the update order button
        Then I should see an error message

    Examples:
        |Customer Name| Order date| Status| Customer Address|
        || 06/17/2021| Completed| 1011 Super Address Dr, Ballston VA, 10234|
        |DJ Kim| | Completed| 1011 Super Address Dr, Ballston VA, 10234|
        |DJ Kim| 06/17/2021| | 1011 Super Address Dr, Ballston VA, 10234|
        |DJ Kim| 06/17/2021| Completed| |
    
    Scenario: Delete an order
        Given I am on the order list page
		When I click the delete button
		Then The order should not be visible in the order list page


