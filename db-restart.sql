TRUNCATE TABLE orders CASCADE;
TRUNCATE TABLE candy CASCADE;
TRUNCATE TABLE warehouse CASCADE;
TRUNCATE TABLE stock CASCADE;
TRUNCATE TABLE orderitem CASCADE;

INSERT INTO candy (id, name, type, flavor, price, weight) VALUES
(1, '3 Musketeers', 'Chocolate Bar', 'Chocolate', 1.75, 1.92),
(2, 'Air Heads', 'Taffy Bar', 'Cherry', 0.75, 0.55),
(3, 'Air Heads', 'Taffy Bar', 'Watermelon', 0.75, 0.55),
(4, 'Almondy Joy', 'Chocolate Bar', 'Coconut', 1.75, 1.70),
(6, 'Turking Taffy', 'Taffy Bar', 'Strawberry', 1.75, 1.50),
(8, 'Tootsie Pop', 'Lollipops', 'Grape', 18.00, 32.00),
(9, 'Sour Blast Candy Spray', 'Sour Candy', 'Sour', 2.50, 5.00),
(10, 'Sour DOTS Gumdrops', 'Sour Candy', 'Sour', 2.50, 6.00),
(11, '4D Gummy Apples Bulk', 'Gummy Candy', 'Apple', 18.00, 35.20),
(7, 'Haribo''s', 'Gummy Candy', 'Multi-flavored', 2.99, 2.00);

INSERT INTO orders (id, customer_name, order_date, status, customer_address) VALUES
(7, 'Terrance Mensah', '2024-06-07', 'Pending', '123 Main St Stocking, FL 20210'),
(1, 'Sally Mae', '2024-07-31', 'Pending', '243 Green St. Westland, MI 48185'),
(2, 'Victoria Ombuna', '2022-05-08', 'Completed', '453 Maple Drive, Springfield, IL 62704, USA'),
(4, 'Jeremiah Mensah', '2024-11-22', 'Ordered', '33 Big Rock Cove Street Queensbury, NY 12804'),
(8, 'DJ Kim', '2024-12-06', 'Ordered', '982 Oak Lane, Greenfield, WI 53228, USA');

INSERT INTO orderitem (id, orders_id, candy_id, price, quantity) VALUES
(11, 2, 1, 30.00, 20),
(17, 1, 3, 2.99, 1),
(16, 7, 1, 400.00, 200);

INSERT INTO warehouse (id, location, capacity, current_stock) VALUES
(19, '789 Pine Road Lakeside, MI 48601 USA', 100000, 200),
(20, '456 Oak Avenue Greenfield, MA 01201 USA', 100000, 350),
(21, '9877 Cedar Swamp Ave. Barberton, OH 44203', 100000, 0),
(8, '136 Mayfet Street Springfield, NY 84729', 100000, 450);

INSERT INTO stock (id, candy_id, warehouse_id, quantity) VALUES
(20, 1, 19, 200),
(21, 8, 20, 350),
(6, 10, 8, 450);