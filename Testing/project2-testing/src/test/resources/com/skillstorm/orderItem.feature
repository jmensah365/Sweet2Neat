@OrderItems
Feature: OrderItem CRUD operations
    Scenario Outline: Creating an order item
        Given I am on the order information page
        When I select "<customername>" and "<candyname>"
        And input "<price>" and "<quantity>" fields
        And I click the Add Order Item button
        Then I should see the order item with "<customername>", "<candyname>", "<price>", and "<quantity>" in the list
    Examples:
        |   customername   |   candyname   |   price   |   quantity    |
        |  Micheal Phelps   | Sour Skittles |   3.99    |       200     |

    Scenario Outline: Updating an order item
        Given I am on the order information page
        When I click on edit icon
        And I select "<customername>" and "<candyname>"
        And input "<price>" and "<quantity>" fields
        And I click the Update Order Item button
        Then I should see the order item with "<customername>", "<candyname>", "<price>", and "<quantity>" in the list
    Examples:
        |   customername   |   candyname   |   price   |   quantity    |
        |  DJ Kim   | Ring Pops |   7.99    |       365     |
    
    Scenario: Deleting an order item
        Given I am on the order information page
        When I click on delete icon
        Then the order item should not be visible in the table