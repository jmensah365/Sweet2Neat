This Candy Inventory Management is a web application designed to efficiently manage candy inventory across multiple warehouses. This application provides CRUD (Create, Read, Update, and Delete) functionality for managing warehouses, candy inventory and types, and stock levels.

1. Features
    - Warehouse management
    - Candy inventory management
    - Stock management
    - Real-time tracking of stock levels and total warehouses

2. Tech Stack
    - Frontend:
        - ReactJS
        - HTML
        - CSS
        - Material-UI
    - Backend:
        - SpringBoot
        - PostgreSQL
        - Spring Data JPA



3. The backend has 5 entities:
    - Candy
        - Keeps track of candy information
    - Warehouse
        - Manages the storage of candy
    - Stock
        - keeps track of how much stock of each candy is in each warehouse
    - Orders
        - keeps track of all orders
    - OrderItem
        - keeps track of all order information (i.e candy ordered, quantity, etc)

4. Relationships between tables:
    - Many-to-Many:
        - Candy and Warehouse facilitated through stock (Candy <-> Stock <-> Warehouse)
    - One-to-Many:
        - Order to OrderItem:
            - A single Order can be in multiple OrderItem records
        Candy to OrderItem:
            - Each OrderItem refers to a single Candy but a single Candy can be in multiple OrderItem records
