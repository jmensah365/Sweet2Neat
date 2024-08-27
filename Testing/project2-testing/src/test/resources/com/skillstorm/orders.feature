@Orders
Feature: Order CRUD operations
    Scenario Outline: Creating an order
        Given I am on the order list page
        When I fill in "<Customer Name>", "<Order date>", "<Status>", and "<Customer Address>"
        Then I click the Add order button
        Then new order information should be in the table
    Examples:
        |Customer Name| Order Date| Status| Customer Address|
        |Jeremiah Mensah| 09-19-2023| Pending| 1010 Fake Address Dr, Greensboro NC, 20675|
    

    # Scenario: View the order list table
    #     Given I am on the base page
    #     When I navigate to the order list page
    #     Then I should see the list of orders
    
    # Scenario Outline: Update an order
    #     Given I am on the order list page
    #     When I click the edit button
    #     And I modify  “<Customer Name>”, “<Order date>”, “<Status>”, and/or “<Customer Address>”
    #     And I click the update order button
    #     Then I should see the updated order in the list of orders
    # Examples:
    #     |Customer Name| Order Date| Status| Customer Address|
    #     |DJ Kim| 2021-06-17| completed| 1010 Fake Address Dr, Ballston VA, 10234|
    
    # Scenario: Delete an order
    #     Given I am on the order list page
	# 	When I click the delete button
	# 	Then The order should not be visible in the order list page



