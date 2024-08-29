@OrderItems
Feature: OrderItem CRUD operations
    Scenario Outline: Creating an order
        Given I am on the order information page
        When I select "<customername>" and "<candyname>"
        And input "<price>" and "<quantity>" fields
        And I click the Add Order Item button
        Then I should see the order item with "<customername>", "<candyname>", "<price>", and "<quantity>" in the list
    Examples:
        |   customername   |   candyname   |   price   |   quantity    |
        |  Micheal Phelps   | Sour Skittles |   3.99    |       200     |