@OrderItems
Feature: OrderItem CRUD operations
    Scenario Outline: Successful creating an order item
        Given I am on the order information page
        When I select "<customername>" and "<candyname>"
        And input "<price>" and "<quantity>" fields
        And I click the Add Order Item button
        Then I should see the order added in the list
    Examples:
        |   customername   |   candyname   |   price   |   quantity    |
        |  Jeremiah Mensah   | Air Heads |   2.99    |       200     |

    Scenario Outline: Unsuccessful creating an order item
        Given I am on the order information page
        When I select "<customername>" and "<candyname>"
        And input "<price>" and "<quantity>" fields
        And I click on the Add Order Item button
        Then I should not see the order added in the list
        And I should see an error msg "<error>"
    Examples:
        |   customername   |   candyname   |   price   |   quantity    | error |
        |      | Air Heads |   2.99    |       500     | Customer Name is required |
        | Jeremiah Mensah |  | 2.99 | 500 | Candy Name is required |
        | Jeremiah Mensah | Air Heads |  | 500 | Price must be a positive number, and Price cannot contain letters | 
        | Jeremiah Mensah | Air Heads |   2.99    |            | Quantity must be a number and greater than zero, and Quantity cannot contain letters |
        | | | | | Customer Name is required, and Candy Name is required, and Price must be a positive number, and Price cannot contain letters, and Quantity must be a number and greater than zero, and Quantity cannot contain letters |

    Scenario Outline: Successful updating an order item
        Given I am on the order information page
        When I click on edit icon
        And I select "<customername>" and "<candyname>"
        And input "<price>" and "<quantity>" fields
        And I click the Update Order Item button
        Then I should see the order added in the list
    Examples:
        |   customername   |   candyname   |   price   |   quantity    |
        |  DJ Kim   | Tootsie Pop |   7.99    |       300     |
    
    Scenario Outline: Unsuccessful updating an order item
        Given I am on the order information page
        When I click on edit icon
        And I select "<customername>" and "<candyname>"
        And input "<price>" and "<quantity>" fields
        And I click the Update Order Item button
        Then I should not see the order added in the list
    Examples:
        |   customername   |   candyname   |   price   |   quantity    | error |
        |      | Tootsie Pop |   7.99    |       300     | Customer Name is required |
        | DJ Kim |  | 7.99 | 300 | Candy Name is required |
        | DJ Kim | Tootsie Pop |  | 300 | Price must be a positive number, and Price cannot contain letters | 
        | DJ Kim  | Tootsie Pop |   7.99    |            | Quantity must be a number and greater than zero, and Quantity cannot contain letters |
        | | | | | Customer Name is required, and Candy Name is required, and Price must be a positive number, and Price cannot contain letters, and Quantity must be a number and greater than zero, and Quantity cannot contain letters |

    Scenario: Deleting an order item
        Given I am on the order information page
        When I click on delete icon
        Then the order item should not be visible in the table