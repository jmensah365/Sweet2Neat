package com.skillstorm.project_one;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skillstorm.project_one.Models.Candy;
import com.skillstorm.project_one.Models.Stock;
import com.skillstorm.project_one.Models.Warehouse;

class StockTest {

    private Stock stock;
    private Candy candy;
    private Warehouse warehouse;

    @BeforeMethod
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

        Assert.assertEquals(1, stock.getId());
        Assert.assertEquals(10, stock.getQuantity());
        Assert.assertEquals(candy, stock.getCandy());
        Assert.assertEquals(warehouse, stock.getWarehouse());

        Assert.assertEquals(100, stock.getCandyId());
        Assert.assertEquals(200, stock.getWarehouseId());
    }

    @Test
    void testToString() {
        stock.setId(1);
        stock.setQuantity(10);
        stock.setCandy(candy);
        stock.setWarehouse(warehouse);

        String expectedString = "Stock [id=1, warehouse=" + warehouse + ", candy=" + candy + ", quantity=10]";
        Assert.assertEquals(expectedString, stock.toString());
    }
}
