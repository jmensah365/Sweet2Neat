package com.skillstorm.project_one;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Models.Warehouse;

class StockTest {

    private Stock stock;
    private Candy candy;
    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        stock = new Stock();
        candy = new Candy(); // You need to create instances of Candy and Warehouse
        warehouse = new Warehouse();

        candy.setId(100); // Set ID for testing
        warehouse.setId(200); // Set ID for testing
    }

    @Test
    void testGettersAndSetters() {
        stock.setId(1);
        stock.setQuantity(10);
        stock.setCandy(candy);
        stock.setWarehouse(warehouse);

        assertEquals(1, stock.getId());
        assertEquals(10, stock.getQuantity());
        assertEquals(candy, stock.getCandy());
        assertEquals(warehouse, stock.getWarehouse());

        assertEquals(100, stock.getCandyId());
        assertEquals(200, stock.getWarehouseId());
    }

    @Test
    void testToString() {
        stock.setId(1);
        stock.setQuantity(10);
        stock.setCandy(candy);
        stock.setWarehouse(warehouse);

        String expectedString = "Stock [id=1, warehouse=" + warehouse + ", candy=" + candy + ", quantity=10]";
        assertEquals(expectedString, stock.toString());
    }
}
