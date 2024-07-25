This Inventory Management project was built using PostgreSQL, SpringBoot, and React

1. Relationships between tables:
    - Many-to-Many:
        - Candy and Warehouse facilitated through stock (Candy <-> Stock <-> Warehouse)
    - One-to-Many:
        - Order to OrderItem:
            - A single Order can be in multiple OrderItem records
        Candy to OrderItem:
            - Each OrderItem refers to a single Candy but a single Candy can be in multiple OrderItem records
